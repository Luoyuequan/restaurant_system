package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.common.*;
import com.system.backgroundmanagement.config.ProductFileUploadConfig;
import com.system.backgroundmanagement.dao.ProductionInfoDao;
import com.system.backgroundmanagement.entity.Column;
import com.system.backgroundmanagement.entity.ProductionInfo;
import com.system.backgroundmanagement.service.IColumnService;
import com.system.backgroundmanagement.service.IProductionInfoService;
import com.system.backgroundmanagement.service.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * 产品信息 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
@Slf4j
public class ProductionInfoServiceImpl extends ServiceImpl<ProductionInfoDao, ProductionInfo>
        implements IProductionInfoService {
    @Autowired
    IColumnService columnService;

    @Autowired
    private ProductFileUploadConfig productFileUploadConfig;

    @Override
    public ResponseVO listProInfo(PageVO pageVO, RequestVO requestVo) {
        try {
            QueryWrapper<ProductionInfo> infoQuery = new QueryWrapper<>();
            Map<String, Object> voMap = MapUtils.convertMap(requestVo);
            //非null的作为查询参数
            infoQuery.allEq(voMap, false);
            IPage<ProductionInfo> infoPage = new Page<>();
            infoPage.setCurrent(pageVO.getPage()).setSize(pageVO.getSize());
            //条件查询+分页
            IPage<ProductionInfo> productionInfoList = page(infoPage, infoQuery);
            List<ProductionInfo> infoList = productionInfoList.getRecords();
            //无数据
            if (CollectionUtils.isEmpty(infoList)) {
                return ResponseVO.success(MessageEnum.DATA_NO);
            }
            //获取产品所属栏目相关信息,代替多表查询
            for (ProductionInfo p : infoList) {
                Long columnId = p.getColumnId();
                Column columnInfo = columnService.getColumnInfoById(columnId);
                p.setColumn(columnInfo);
            }
            return ResponseVO.success(MessageEnum.FIND_SUCCESS, productionInfoList);
        } catch (ServiceException e) {
            String msg = MessageEnum.FIND_ERROR.getMsg() + ",requestVo:" + requestVo;
            ServiceException serviceException = new ServiceException(msg);
            serviceException.addSuppressed(e);
            throw serviceException;
        }
    }

    @Override
    public boolean updateProInfo(ProductionInfo proInfo) {
        AtomicBoolean removeResult = new AtomicBoolean(false);
        try {
            UpdateWrapper<ProductionInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.ge("id", proInfo.getId());
            removeResult.set(update(proInfo, updateWrapper));
            if (!removeResult.get()) {
                String msg = MessageEnum.UPDATE_ERROR.getMsg() + ",产品信息proInfo:" + proInfo;
                throw new ServiceException(msg);
            }
            return removeResult.get();
        } catch (Exception e) {
            String msg = MessageEnum.UPDATE_ERROR.getMsg() + ",产品信息proInfo:" + proInfo;
            throw new ServiceException(msg, e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(List<Long> idList) {
        Long[] ids = idList.toArray(new Long[0]);
        AtomicBoolean removeResult = new AtomicBoolean(false);
        try {
            removeResult.set(this.removeByIds(idList));
            if (!removeResult.get()) {
                String msg = MessageEnum.DELETE_ERROR.getMsg() + ",ids:" + Arrays.toString(ids);
                throw new ServiceException(msg);
            }
        } catch (Exception e) {
            String msg = MessageEnum.DELETE_ERROR.getMsg() + ",ids:" + Arrays.toString(ids);
            throw new ServiceException(msg, e);
        }
        return removeResult.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductionInfo getProInfo(Long id) {
        ProductionInfo productionInfo = getById(id);
        if (productionInfo != null) {
            AtomicReference<Long> clickNumber = new AtomicReference<>(productionInfo.getClickNumber());
            clickNumber.set(clickNumber.get() + 1);
            //产品访问点击量+1
            productionInfo.setClickNumber(clickNumber.get());
            boolean updateResult = updateProInfo(productionInfo);
            if (!updateResult) {
                String msg = MessageEnum.FIND_ERROR.getMsg() + ",访问量增加失败,productionInfo:" + productionInfo;
                throw new ServiceException(msg);
            }
            //获取产品所属栏目相关信息,代替多表查询
            Long columnId = productionInfo.getColumnId();
            Column columnInfo = columnService.getColumnInfoById(columnId);
            productionInfo.setColumn(columnInfo);
        }
        return productionInfo;
    }

    @Override
    public boolean saveProInfoAndImage(ProductionInfo proInfo, @NotNull MultipartFile imgFile) {
        try {
            String fileUploadPath = productFileUploadConfig.getPath();
            Long sizeMax = Long.valueOf(productFileUploadConfig.getSize());
            long fileSize = imgFile.getSize();
            //File size exceeds specified limit
            if (fileSize > sizeMax) {
                throw new ServiceException(MessageEnum.FILE_SIZE_MAX);
            }
            byte[] fileBytes = imgFile.getBytes();
            String filename = imgFile.getOriginalFilename();
            //generator new file name randomly
            String newFileName = System.currentTimeMillis() +
                    UUID.randomUUID().toString().replace("-", "") + filename;
            boolean saveFileResult = FileHandlerUtils.saveFileToDisk(fileBytes, newFileName, fileUploadPath);
            //file save result to disk
            if (saveFileResult) {
                //save production info to database,save result
                boolean saveInfoResult = save(proInfo.setImg(fileUploadPath + newFileName));
                //production info save failed
                if (!saveInfoResult) {
                    //delete upload file
                    boolean deleteFile = FileHandlerUtils.deleteFileFromDisk(fileUploadPath + newFileName);
                    return false;
                }
                return true;
            }
            //file save failed
            return false;
        } catch (IOException e) {
            throw new ServiceException(MessageEnum.FILE_UPLOAD_ERROR, e);
        }
    }
}

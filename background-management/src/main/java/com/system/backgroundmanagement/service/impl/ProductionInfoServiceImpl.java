package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.common.MessageEnum;
import com.system.backgroundmanagement.common.ReturnVO;
import com.system.backgroundmanagement.common.VO;
import com.system.backgroundmanagement.dao.ProductionInfoDao;
import com.system.backgroundmanagement.entity.ProductionInfo;
import com.system.backgroundmanagement.service.IProductionInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
public class ProductionInfoServiceImpl extends ServiceImpl<ProductionInfoDao, ProductionInfo> implements IProductionInfoService {

    @Override
    public ReturnVO listProInfo(VO vo) {
        try {
            QueryWrapper<ProductionInfo> infoQueryWrapper = new QueryWrapper<>();
            infoQueryWrapper.likeRight("title", vo.getTitle())
                    .ge("recommend", vo.getRecommend()).ge("top", vo.getTop());
            IPage<ProductionInfo> infoPage = new Page<>();
            infoPage.setCurrent(vo.getPage()).setSize(vo.getSize());
            IPage<ProductionInfo> productionInfoList = page(infoPage, infoQueryWrapper);
            return ReturnVO.success(MessageEnum.FIND_SUCCESS, productionInfoList);
        } catch (Exception e) {
            log.warn("查询失败,vo:{}", vo, e.getCause());
            return ReturnVO.error(MessageEnum.FIND_ERROR);
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
                log.warn("产品信息修改失败,proInfo:{}", proInfo);
            }
        } catch (Exception e) {
            log.warn("产品信息修改失败,proInfo:{}", proInfo, e.getCause());
        }
        return removeResult.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(List<Long> idList) {
        Long[] ids = idList.toArray(new Long[0]);
        AtomicBoolean removeResult = new AtomicBoolean(false);
        try {
            removeResult.set(this.removeByIds(idList));
            if (!removeResult.get()) {
                log.warn("批量删除失败,ids:{}", Arrays.toString(ids));
            }
        } catch (Exception e) {
            log.warn("批量删除异常,ids:{}", Arrays.toString(ids), e.getCause());
        }
        return removeResult.get();
    }
}

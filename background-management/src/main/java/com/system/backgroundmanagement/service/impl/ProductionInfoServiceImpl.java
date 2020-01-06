package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.common.*;
import com.system.backgroundmanagement.dao.ProductionInfoDao;
import com.system.backgroundmanagement.entity.ProductionInfo;
import com.system.backgroundmanagement.service.IProductionInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
    @Override
    public ReturnVO listProInfo(PageVO pageVO, VO vo) {
        try {
            QueryWrapper<ProductionInfo> infoQuery = new QueryWrapper<>();
            Map<String, Object> voMap = MapUtils.convertMap(vo);
            //排除size和page参数,排序列名
            infoQuery.allEq((k, v) -> !"size".equals(k) && !"page".equals(k) && !k.contains("sort"),
                    voMap, false);
            //升序
//            String[] orderColumnName = vo.getSortColumnName().split(",");
//            infoQuery.orderByAsc(orderColumnName);
            IPage<ProductionInfo> infoPage = new Page<>();
            //设置当前页码和每页数量
//            List<OrderItem> orderItems = new ArrayList<>();
//            OrderItem orderItem = new OrderItem();
//            orderItems.add
            infoPage.setCurrent(vo.getPage()).setSize(vo.getSize());
            //条件查询+分页
            IPage<ProductionInfo> productionInfoList = page(infoPage, infoQuery);
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
//            throw e;
            log.warn("批量删除异常,ids:{}", Arrays.toString(ids), e.getCause());
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
                log.warn("访问量增加失败,productionInfo:{}", productionInfo);
            }
        }
        return productionInfo;
    }
}

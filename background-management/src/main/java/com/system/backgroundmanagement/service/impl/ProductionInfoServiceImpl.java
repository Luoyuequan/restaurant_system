package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.common.ReturnVO;
import com.system.backgroundmanagement.dao.ProductionInfoDao;
import com.system.backgroundmanagement.entity.ProductionInfo;
import com.system.backgroundmanagement.service.IProductionInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品信息 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
public class ProductionInfoServiceImpl extends ServiceImpl<ProductionInfoDao, ProductionInfo> implements IProductionInfoService {

    @Override
    public ReturnVO saveProInfo(ProductionInfo proInfo) {
        return null;
    }

    @Override
    public ReturnVO delProInfo(Long id) {
        return null;
    }

    @Override
    public ReturnVO listProInfo() {
        return null;
    }

    @Override
    public ReturnVO updateProInfo(ProductionInfo proInfo) {
        return null;
    }
}

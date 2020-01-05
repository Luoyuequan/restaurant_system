package com.system.backgroundmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.system.backgroundmanagement.common.ReturnVO;
import com.system.backgroundmanagement.common.VO;
import com.system.backgroundmanagement.entity.ProductionInfo;

import java.util.List;

/**
 * <p>
 * 产品信息 服务类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
public interface IProductionInfoService extends IService<ProductionInfo> {


    /**
     * @param vo
     * @return
     */
    ReturnVO listProInfo(VO vo);

    /**
     * @param proInfo
     * @return
     */
    boolean updateProInfo(ProductionInfo proInfo);

    /**
     * 根据id集合批量删除产品信息
     *
     * @param idList
     * @return
     */
    boolean deleteByIds(List<Long> idList);
}

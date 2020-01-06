package com.system.backgroundmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.system.backgroundmanagement.common.PageVO;
import com.system.backgroundmanagement.common.RequestVO;
import com.system.backgroundmanagement.common.ResponseVO;
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
     * 根据title筛选分页(非必须)
     * 产品信息列表
     *
     * @param pageVO    分页参数
     * @param requestVo 条件参数
     * @return
     */
    ResponseVO listProInfo(PageVO pageVO, RequestVO requestVo);

    /**
     * 修改产品信息
     *
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

    /**
     * 访问产品信息
     * 增加点击数
     *
     * @param id
     * @return
     */
    ProductionInfo getProInfo(Long id);
}

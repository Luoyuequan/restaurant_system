package com.system.backgroundmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.system.backgroundmanagement.common.ReturnVO;
import com.system.backgroundmanagement.common.VO;
import com.system.backgroundmanagement.entity.CompanyInfo;

/**
 * <p>
 * 公司信息 服务类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
public interface ICompanyInfoService extends IService<CompanyInfo> {

    /**
     * 根据公司名，id
     * 获取公司信息
     *
     * @param vo
     * @return
     */
    ReturnVO getCompanyInfo(VO vo);

    /**
     * 根据id
     * 修改公司信息
     *
     * @param vo
     * @return
     */
    boolean updateCompanyInfo(CompanyInfo vo);
}

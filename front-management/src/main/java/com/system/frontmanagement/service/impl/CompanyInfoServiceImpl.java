package com.system.frontmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.frontmanagement.dao.CompanyInfoDao;
import com.system.frontmanagement.entity.CompanyInfo;
import com.system.frontmanagement.service.ICompanyInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司信息 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoDao, CompanyInfo> implements ICompanyInfoService {

}

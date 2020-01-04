package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.dao.CompanyInfoDao;
import com.system.backgroundmanagement.entity.CompanyInfo;
import com.system.backgroundmanagement.service.ICompanyInfoService;
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

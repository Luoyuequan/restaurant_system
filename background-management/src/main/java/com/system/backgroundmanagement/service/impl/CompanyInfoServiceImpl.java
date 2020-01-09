package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.common.MessageEnum;
import com.system.backgroundmanagement.common.RequestVO;
import com.system.backgroundmanagement.common.ResponseVO;
import com.system.backgroundmanagement.dao.CompanyInfoDao;
import com.system.backgroundmanagement.entity.CompanyInfo;
import com.system.backgroundmanagement.service.ICompanyInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 公司信息 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
@Slf4j
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoDao, CompanyInfo> implements ICompanyInfoService {

    @Override
    public ResponseVO getCompanyInfo(RequestVO requestVo) {
        try {
            QueryWrapper<CompanyInfo> infoQuery = new QueryWrapper<>();
            infoQuery.ge("id", requestVo.getId());
            CompanyInfo companyInfo = getOne(infoQuery);
            if (companyInfo == null) {
                return ResponseVO.success(MessageEnum.DATA_NO);
            }
            return ResponseVO.success(MessageEnum.FIND_SUCCESS, companyInfo);
        } catch (Exception e) {
            return ResponseVO.error(MessageEnum.FIND_ERROR);
        }
    }

    @Override
    public boolean updateCompanyInfo(CompanyInfo companyInfo) {
        AtomicBoolean removeResult = new AtomicBoolean(false);
        try {
            UpdateWrapper<CompanyInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.ge("id", companyInfo.getId());
            removeResult.set(update(companyInfo, updateWrapper));
            if (!removeResult.get()) {
                log.warn("修改失败,companyInfo:{}", companyInfo);
            }
        } catch (Exception e) {
            log.warn("修改失败,companyInfo:{}", companyInfo, e.getCause());
        }
        return removeResult.get();
    }
}

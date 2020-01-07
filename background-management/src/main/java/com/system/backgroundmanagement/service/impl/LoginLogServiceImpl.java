package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.common.PageVO;
import com.system.backgroundmanagement.common.RequestVO;
import com.system.backgroundmanagement.dao.LoginLogDao;
import com.system.backgroundmanagement.entity.LoginLog;
import com.system.backgroundmanagement.service.ILoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
@Slf4j
public class LoginLogServiceImpl extends ServiceImpl<LoginLogDao, LoginLog> implements ILoginLogService {

    @Override
    public IPage<LoginLog> listLoginLogInfo(PageVO pageVO, @NotNull RequestVO requestVo) {
        QueryWrapper<LoginLog> loginLogQueryWrapper = new QueryWrapper<>();
        //时间区段查询
        if (requestVo.getStartTime() != null) {
            loginLogQueryWrapper.ge("login_time", requestVo.getStartTime());
        }
        if (requestVo.getEndTime() != null) {
            loginLogQueryWrapper.le("login_time", requestVo.getEndTime());
        }
        //设置当前页码和每页数量
        IPage<LoginLog> loginLogPageList = new Page<>(pageVO.getPage(), pageVO.getSize());
        //条件查询+分页
        return page(loginLogPageList, loginLogQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(@NotNull List<Long> idList) {
        Long[] ids = idList.toArray(new Long[0]);
        AtomicBoolean removeResult = new AtomicBoolean(false);
        try {
            removeResult.set(removeByIds(idList));
            if (!removeResult.get()) {
                log.warn("批量删除失败,ids:{}", Arrays.toString(ids));
            }
        } catch (Exception e) {
            log.warn("批量删除异常,ids:{}", Arrays.toString(ids), e);
        }
        return removeResult.get();
    }
}

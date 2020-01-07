package com.system.backgroundmanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.system.backgroundmanagement.common.PageVO;
import com.system.backgroundmanagement.common.RequestVO;
import com.system.backgroundmanagement.entity.LoginLog;

import java.util.List;

/**
 * <p>
 * 登录日志 服务类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
public interface ILoginLogService extends IService<LoginLog> {

    /**
     * 获取日志信息列表
     *
     * @param pageVO
     * @param requestVo
     * @return
     */
    IPage<LoginLog> listLoginLogInfo(PageVO pageVO, RequestVO requestVo);

    /**
     * 批量删除登录日志
     *
     * @param idList
     * @return
     */
    boolean deleteByIds(List<Long> idList);
}

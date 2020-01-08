package com.system.backgroundmanagement.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.system.backgroundmanagement.common.*;
import com.system.backgroundmanagement.entity.LoginLog;
import com.system.backgroundmanagement.service.ILoginLogService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 登录日志 前端控制器
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/login-log")
public class LoginLogController implements IController<LoginLog> {
    @Autowired
    ILoginLogService loginLogService;

    @Override
    @GetMapping("list")
    public ResponseVO list(PageVO pageVO, RequestVO requestVo, HttpServletRequest request) {
        IPage<LoginLog> loginLogs = loginLogService.listLoginLogInfo(pageVO, requestVo);
        return ResponseVO.success(MessageEnum.FIND_SUCCESS, loginLogs);
    }

    @Override
    public ResponseVO save(@NotNull LoginLog data, HttpServletRequest request) {
        ResponseVO responseVO = ParamCheckUtils.checkValues(data.getAdminId(), data.getIp());
        if (responseVO != null) {
            return responseVO;
        }
        boolean saveResult = loginLogService.save(data);
        if (saveResult) {
            return ResponseVO.success(MessageEnum.ADD_ERROR);
        }
        return ResponseVO.error(MessageEnum.ADD_ERROR);
    }

    @Override
    @DeleteMapping("del")
    public ResponseVO delete(@NotNull RequestVO requestVo, HttpServletRequest request) {
        List<Long> idList = new ArrayList<>();
        ResponseVO checkResult = ParamCheckUtils.checkBatchIds(requestVo.getIds(), idList);
        if (checkResult != null) {
            return checkResult;
        }
        //根据id批量删除
        return loginLogService.deleteByIds(idList) ?
                ResponseVO.success(MessageEnum.DELETE_SUCCESS) : ResponseVO.error(MessageEnum.DELETE_ERROR);
    }
}

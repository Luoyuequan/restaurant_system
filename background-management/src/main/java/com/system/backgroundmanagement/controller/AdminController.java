package com.system.backgroundmanagement.controller;


import com.system.backgroundmanagement.common.MessageEnum;
import com.system.backgroundmanagement.common.ResponseVO;
import com.system.backgroundmanagement.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wf.jwtp.provider.TokenStore;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 管理员模块 前端控制器
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired(required = false)
    private TokenStore tokenStore;

    /**
     * 管理员登录
     *
     * @param admin
     * @return
     */
    @PostMapping(value = "login")
    public ResponseVO login(@RequestBody Admin admin, HttpServletRequest request) {
        //验证逻辑
        // 签发token
        //Token token = tokenStore.createNewToken(admin.getUsername(), permissions, roles, 60*60*24*30);

        return ResponseVO.success(MessageEnum.LOGIN_SUCCESS);
    }

    /**
     * 管理员注销
     *
     * @param admin
     * @return
     */
    @DeleteMapping(value = "logout")
    public ResponseVO logout(@RequestBody Admin admin) {
        return ResponseVO.success(MessageEnum.LOGOUT_SUCCESS);
    }

}

package com.system.frontmanagement.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * login_log
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class LoginLog implements Serializable {
    @NonNull
    private Long id;

    /**
     * 管理员id
     */
    private Long adminId;

    /**
     * 登录IP
     */
    private String ip;

    /**
     * 登录时间戳
     */
    private Long loginTime = System.currentTimeMillis();

    private static final long serialVersionUID = 1L;
}
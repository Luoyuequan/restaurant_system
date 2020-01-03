package com.system.backgroundmanagement.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * admin
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    @NonNull
    private Long id;
    /**
     * 账号
     */
    private String username;
    /**
     * 加密:密码+盐
     */
    private String password;
    /**
     * 盐
     */
    private String salt;
    /**
     * 创建时间
     */
    private Long createTime = System.currentTimeMillis();
    /**
     * 修改时间
     */
    private Long updateTime = System.currentTimeMillis();
}
package com.system.frontmanagement.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * message
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class Message implements Serializable {
    /**
     * 留言消息id
     */
    @NonNull
    private Long id;

    /**
     * 称呼
     */
    private String name;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * ip
     */
    private String ip;

    /**
     * 留言内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Long createTime = System.currentTimeMillis();

    /**
     * 修改时间
     */
    private Long updateTime = System.currentTimeMillis();

    private Boolean isDelete;

    private Boolean isChecked;

    private static final long serialVersionUID = 1L;
}
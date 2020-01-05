package com.system.backgroundmanagement.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 留言表
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 留言消息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 称呼
     */
    @TableField("name")
    private String name;

    /**
     * 电子邮件
     */
    @TableField("email")
    private String email;

    /**
     * 联系电话
     */
    @TableField("tel")
    private String tel;

    /**
     * ip
     */
    @TableField("ip")
    private String ip;

    /**
     * 留言内容
     */
    @TableField("content")
    private String content;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    @TableField("is_deleted")
//    @JSONField(serialize = false)
    @TableLogic
    private Boolean deleted;

    @TableField("is_checked")
//    @JSONField(serialize = false)
    private Boolean checked;


}

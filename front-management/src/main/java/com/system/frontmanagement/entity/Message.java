package com.system.frontmanagement.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    @TableField("create_time")
    private Long createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Long updateTime;

    @TableField("is_delete")
    private Boolean delete;

    @TableField("is_checked")
    private Boolean checked;


}

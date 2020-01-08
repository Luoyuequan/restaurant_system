package com.system.backgroundmanagement.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("留言消息")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 留言消息id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("留言消息id")
    private Long id;

    /**
     * 称呼
     */
    @TableField("name")
    @ApiModelProperty("称呼")
    private String name;

    /**
     * 电子邮件
     */
    @TableField("email")
    @ApiModelProperty("称呼")
    private String email;

    /**
     * 联系电话
     */
    @TableField("tel")
    @ApiModelProperty("联系电话")
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
    @ApiModelProperty(value = "留言内容", required = true, example = "demo")
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

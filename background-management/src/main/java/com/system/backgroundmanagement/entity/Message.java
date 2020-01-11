package com.system.backgroundmanagement.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @ApiModelProperty(value = "留言消息id", example = "1")
    private Long id;

    /**
     * 称呼
     */
    @TableField("name")
    @ApiModelProperty(value = "称呼", required = true)
    private String name;

    /**
     * 电子邮件
     */
    @TableField("email")
    @ApiModelProperty("email")
    @Email(message = "邮件格式错误")
    private String email;

    /**
     * 联系电话
     */
    @TableField("tel")
    @ApiModelProperty("联系电话")
    @Pattern(
            regexp = "^((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(166)|(17[0135678])|(18[0-9])|(19[8|9]))\\d{8}$",
            message = "电话格式错误"
    )
    private String tel;

    /**
     * ip
     */
    @TableField("ip")
    @ApiModelProperty(value = "ip", hidden = true)
    private String ip;

    /**
     * 留言内容
     */
    @TableField("content")
    @ApiModelProperty(value = "留言内容", required = true)
    @NotNull(message = "留言内容不能为空")
    private String content;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true)
    private Long createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(hidden = true)
    private Long updateTime;

    @TableField("is_deleted")
    @TableLogic
    @ApiModelProperty(hidden = true)
    private Boolean deleted;

    @TableField("is_checked")
    @ApiModelProperty(hidden = true)
    private Boolean checked;


}

package com.system.backgroundmanagement.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>
 * 公司信息
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("company_info")
@ApiModel("公司信息")
public class CompanyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id", example = "1")
    private Long id;

    /**
     * 公司名字
     */
    @TableField("`name`")
    @NotBlank(message = "公司名字不能为空")
    @ApiModelProperty(value = "公司名字", required = true)
    private String name;

    /**
     * 公司介绍
     */
    @TableField("content")
    @NotBlank(message = "公司介绍不能为空")
    @ApiModelProperty(value = "公司介绍", required = true)
    private String content;

    /**
     * 公司联系电话
     */
    @TableField("tel")
    @ApiModelProperty(value = "公司联系电话", required = true)
    @NotBlank(message = "联系电话不能为空")
    @Pattern(
            regexp = "^((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(166)|(17[0135678])|(18[0-9])|(19[8|9]))\\d{8}$",
            message = "电话格式错误"
    )
    private String tel;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true, allowEmptyValue = true)
    private Long createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(hidden = true, allowEmptyValue = true)
    private Long updateTime;


}

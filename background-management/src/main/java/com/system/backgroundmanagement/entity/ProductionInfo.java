package com.system.backgroundmanagement.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * <p>
 * 产品信息
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("production_info")
@ApiModel("产品信息")
public class ProductionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "产品信息id", example = "1")
    private Long id;

    /**
     * 所属栏目类型id
     */
    @TableField("column_id")
    @ApiModelProperty(name = "栏目id", example = "1", required = true)
    private Long columnId;

    /**
     * 对应的栏目类型
     * exist 默认值true	是否为数据库表字段
     */
    @TableField(exist = false)
    @ApiModelProperty(hidden = true, accessMode = ApiModelProperty.AccessMode.READ_ONLY, allowEmptyValue = true)
    private Column column;

    @TableField("title")
    @ApiModelProperty(name = "产品信息title", required = true)
    private String title;

    /**
     * 是否审核，0未，1已
     */
    @TableField("is_checked")
    @ApiModelProperty(name = "产品审核状态")
    private Boolean checked;

    /**
     * 排序值
     */
    @TableField("rank_value")
    @ApiModelProperty(name = "产品排序号值", required = true, example = "100")
    private Integer rankValue;

    /**
     * 点击数
     */
    @TableField(value = "click_number")
    @ApiModelProperty(hidden = true, allowEmptyValue = true)
    private Long clickNumber;

    /**
     * 缩略图
     */
    @TableField("img")
    private String img;

    @TableField(exist = false)
    @ApiModelProperty(name = "产品图片", required = true)
    private MultipartFile file;

    /**
     * 是否推荐，0未，1已
     */
    @TableField("is_recommend")
    @ApiModelProperty(value = "产品推荐状态")
    private Boolean recommend;

    /**
     * 是否置顶，0未，1已
     */
    @TableField("is_top")
    @ApiModelProperty(value = "产品置顶状态")
    private Boolean top;

    /**
     * 概述
     */
    @TableField("simple_info")
    @ApiModelProperty(value = "产品简要信息", required = true)
    private String simpleInfo;

    /**
     * 详细内容
     */
    @TableField("detail_info")
    @ApiModelProperty(value = "产品详细信息", required = true)
    private String detailInfo;

    /**
     * 是否已删除，0未，1已
     */
    @TableField("is_deleted")
    @TableLogic
    @ApiModelProperty(hidden = true)
    private Boolean deleted;

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

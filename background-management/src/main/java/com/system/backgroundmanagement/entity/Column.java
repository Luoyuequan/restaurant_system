package com.system.backgroundmanagement.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 栏目管理表
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`column`")
@ApiModel("栏目")
public class Column implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 栏目id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "栏目id(仅限于update)", example = "0", notes = "仅限于update", allowEmptyValue = true)
    private Long id;

    /**
     * 父栏目id,0为根栏目
     */
    @TableField("pid")
    @ApiModelProperty(value = "上级栏目id", example = "0", notes = "仅用于update,insert", allowEmptyValue = true)
    private Long pid;


    /**
     * 栏目名称
     */
    @TableField("`name`")
    @ApiModelProperty(value = "栏目名称", example = "demo", notes = "仅用于insert,update", required = true)
    private String name;

    /**
     * 栏目英文名称
     */
    @TableField("en_name")
    @ApiModelProperty(value = "栏目English名称", example = "demo", notes = "仅用于insert,update")
    private String enName;

    /**
     * 栏目类型id
     */
    @TableField("column_type_id")
    @ApiModelProperty(value = "栏目类型id", example = "1", notes = "仅用于insert,update", required = true)
    private Integer columnTypeId;

    /**
     * id对应的栏目类型
     */
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private ColumnType columnType;

    /**
     * 排序值
     */
    @TableField("rank_value")
    @ApiModelProperty(value = "栏目排序值", example = "100", notes = "仅用于insert,update", required = true)
    private Integer rankValue;

    /**
     * 图标样式
     */
    @TableField("icon")
    @ApiModelProperty(value = "图标地址", notes = "仅用于insert,update")
    private String icon;

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

    /**
     * 子栏目集合
     */
    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private List<Column> childColumn;
}

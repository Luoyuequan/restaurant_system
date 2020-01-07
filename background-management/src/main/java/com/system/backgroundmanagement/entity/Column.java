package com.system.backgroundmanagement.entity;

import com.baomidou.mybatisplus.annotation.*;
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
public class Column implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 栏目id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父栏目id,0为根栏目
     */
    @TableField("pid")
    private Long pid;


    /**
     * 栏目名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 栏目英文名称
     */
    @TableField("en_name")
    private String enName;

    /**
     * 栏目类型id
     */
    @TableField("column_type_id")
    private Integer columnTypeId;

    /**
     * id对应的栏目类型
     */
    @TableField(exist = false)
    private ColumnType columnType;

    /**
     * 排序值
     */
    @TableField("rank_value")
    private Integer rankValue;

    /**
     * 图标样式
     */
    @TableField("icon")
    private String icon;

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
    @TableLogic
    private Boolean deleted;

    /**
     * 子栏目集合
     */
    @TableField(exist = false)
    private List<Column> childColumn;
}

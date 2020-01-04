package com.system.backgroundmanagement.entity;

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
 * 栏目管理表
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("column")
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
    @TableField("name")
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
     * 排序号
     */
    @TableField("rank")
    private Integer rank;

    /**
     * 图标样式
     */
    @TableField("icon")
    private String icon;

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


}

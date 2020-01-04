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
public class ProductionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属栏目类型id
     */
    @TableField("column_id")
    private Long columnId;

    @TableField("title")
    private String title;

    /**
     * 是否审核，0未，1已
     */
    @TableField("is_checked")
    private Boolean checked;

    @TableField("rank")
    private Integer rank;

    /**
     * 缩略图
     */
    @TableField("img")
    private String img;

    /**
     * 是否推荐，0未，1已
     */
    @TableField("is_recoment")
    private Boolean recoment;

    /**
     * 是否置顶，0未，1已
     */
    @TableField("is_top")
    private Boolean top;

    /**
     * 简介
     */
    @TableField("resume")
    private String resume;

    /**
     * 详细内容
     */
    @TableField("detail_info")
    private String detailInfo;

    /**
     * 是否已删除，0未，1已
     */
    @TableField("is_delete")
    private Boolean delete;

    @TableField("create_time")
    private Long createTime;

    @TableField("update_time")
    private Long updateTime;


}

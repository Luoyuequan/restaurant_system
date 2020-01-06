package com.system.backgroundmanagement.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 新闻动态
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("news")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("column_id")
    private Long columnId;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 是否审核，0未，1已
     */
    @TableField("is_checked")
    private Boolean checked;

    /**
     * 排序值
     */
    @TableField("rank_value")
    private Integer rankValue;

    /**
     * 点击数
     */
    @TableField(value = "click_number")
    private Long clickNumber;

    /**
     * 缩略图
     */
    @TableField("img")
    private String img;

    /**
     * 是否推荐，0未，1已
     */
    @TableField("is_recommend")
    private Boolean recommend;

    /**
     * 是否置顶，0未，1已
     */
    @TableField("is_top")
    private Boolean top;

    /**
     * 概述
     */
    @TableField("simple_info")
    private String simpleInfo;

    /**
     * 详细内容
     */
    @TableField("detail_info")
    private String detailInfo;

    /**
     * 是否已删除，0未，1已
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

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


}

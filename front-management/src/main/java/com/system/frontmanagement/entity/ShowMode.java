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
 * 显示模式
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("show_mode")
public class ShowMode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 显示模式id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模板
     */
    @TableField("type")
    private String type;

    /**
     * 模板
     */
    @TableField("template")
    private String template;

    @TableField("create_time")
    private Long createTime;

    @TableField("update_time")
    private Long updateTime;


}

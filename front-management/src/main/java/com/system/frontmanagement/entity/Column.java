package com.system.frontmanagement.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * column
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class Column implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 栏目id
     */
    @NonNull
    private Long id;
    /**
     * 父栏目id,0为根栏目
     */
    private Long pid;
    /**
     * 栏目名称
     */
    private String name;
    /**
     * 栏目英文名称
     */
    private String enName;
    /**
     * 栏目类型id
     */
    private Integer columnTypeId;
    /**
     * 排序号
     */
    private Integer rank;
    /**
     * 图标样式
     */
    private String icon;
    /**
     * 创建时间
     */
    private Long createTime = System.currentTimeMillis();
    /**
     * 修改时间
     */
    private Long updateTime = System.currentTimeMillis();
    private Boolean isDelete;
}
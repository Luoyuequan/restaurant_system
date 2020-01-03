package com.system.frontmanagement.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * production_info
 * @author luo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class ProductionInfo implements Serializable {
    @NonNull
    private Long id;

    /**
     * 所属栏目类型id
     */
    private Long columnId;

    private String title;

    /**
     * 是否审核，0未，1已
     */
    private Boolean isChecked;

    private Integer rank;

    /**
     * 缩略图
     */
    private String img;

    /**
     * 是否推荐，0未，1已
     */
    private Boolean isRecoment;

    /**
     * 是否置顶，0未，1已
     */
    private Boolean isTop;

    /**
     * 简介
     */
    private String resume;

    /**
     * 详细内容
     */
    private String detailInfo;

    /**
     * 是否已删除，0未，1已
     */
    private Boolean isDelete;

    private Long createTime = System.currentTimeMillis();

    private Long updateTime = System.currentTimeMillis();

    private static final long serialVersionUID = 1L;
}
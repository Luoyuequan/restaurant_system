package com.system.frontmanagement.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * company_info
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class CompanyInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @NonNull
    private Long id;
    /**
     * 公司介绍
     */
    private String content;
    /**
     * 公司联系电话
     */
    private String tel;
    private Long createTime = System.currentTimeMillis();
    private Long updateTime = System.currentTimeMillis();
}
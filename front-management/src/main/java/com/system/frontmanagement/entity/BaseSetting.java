package com.system.frontmanagement.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * base_setting
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class BaseSetting implements Serializable {
    @NonNull
    private Long id;

    /**
     * 创建时间戳

     */
    private Long createTime = System.currentTimeMillis();

    /**
     * 修改时间戳
     */
    private Long updateTime = System.currentTimeMillis();

    private static final long serialVersionUID = 1L;
}
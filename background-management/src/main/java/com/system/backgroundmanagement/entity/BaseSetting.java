package com.system.backgroundmanagement.entity;

import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;

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
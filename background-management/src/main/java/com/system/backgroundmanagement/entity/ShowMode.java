package com.system.backgroundmanagement.entity;

import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * show_mode
 * @author luo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class ShowMode implements Serializable {
    /**
     * 显示模式id
     */
    @NonNull
    private Integer id;

    /**
     * 模板
     */
    private String type;

    /**
     * 模板
     */
    private String template;

    private Long createTime = System.currentTimeMillis();

    private Long updateTime = System.currentTimeMillis();

    private static final long serialVersionUID = 1L;
}
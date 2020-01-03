package com.system.backgroundmanagement.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * show_mode
 *
 * @author luo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class ShowMode implements Serializable {
    private static final long serialVersionUID = 1L;
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
}
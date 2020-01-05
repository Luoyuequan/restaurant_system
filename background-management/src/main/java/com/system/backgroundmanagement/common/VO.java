package com.system.backgroundmanagement.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p></p>
 *
 * @author luoyuequan
 * @date 2020/01/04
 * @time 下午 10:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VO {
    private Long id;
    private Integer page;
    private Integer size;
    private String name;
    private String title;
    private Boolean recommend;
    private Boolean top;

    {
        this.page = 1;
        this.size = 10;
    }
}

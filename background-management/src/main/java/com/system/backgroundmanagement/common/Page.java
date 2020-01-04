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
public class Page {
    private Integer page;
    private Integer size;
}

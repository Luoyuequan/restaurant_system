package com.system.backgroundmanagement.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author luoyuequan
 * @time 2020/1/8 16:30
 */
@Data
@Accessors(chain = true)
public class ArgumentsInvalidBean {
    private String argumentName;
    private String exceptionMsg;
}

package com.system.backgroundmanagement.service.exception;

import com.system.backgroundmanagement.common.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author luoyuequan
 * @time 2020/1/8 14:26
 */
@RestControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseVO handling(@NotNull ServiceException e) {
        System.out.println(e.getMessage());
        System.out.println(e.getLocalizedMessage());
        log.warn(e.getMessage(), e.getCause());
        int i = e.getMessage().indexOf(",");
        String responseMeg = e.getMessage().substring(0, i);
        ResponseVO responseVO = ResponseVO.exception(responseMeg);
        System.out.println(responseVO);
        return responseVO;
    }
}

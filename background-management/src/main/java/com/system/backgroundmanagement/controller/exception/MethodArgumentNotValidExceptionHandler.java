package com.system.backgroundmanagement.controller.exception;

import com.system.backgroundmanagement.common.ArgumentsInvalidBean;
import com.system.backgroundmanagement.common.MessageEnum;
import com.system.backgroundmanagement.common.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoyuequan
 * @time 2020/1/8 15:14
 */
@RestControllerAdvice
@Slf4j
public class MethodArgumentNotValidExceptionHandler {

    /**
     * 处理参数缺失,无效异常
     *
     * @param ex 异常
     * @return 响应
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVO handling(@NotNull MethodArgumentNotValidException ex) {
        List<ArgumentsInvalidBean> argumentsInvalidBeanList = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            ArgumentsInvalidBean bean = new ArgumentsInvalidBean();
            bean.setArgumentName(error.getField());
            bean.setExceptionMsg(error.getDefaultMessage());
            log.warn("field:{},msg:{}", error.getField(), error.getDefaultMessage());
            argumentsInvalidBeanList.add(bean);
        }
        return ResponseVO.exception(MessageEnum.VARIABLE_INVALID_ERROR.getMsg(), argumentsInvalidBeanList);
    }


}

package com.system.backgroundmanagement.service.exception;

import com.system.backgroundmanagement.common.MessageEnum;
import org.jetbrains.annotations.NotNull;

/**
 * @author luoyuequan
 * @time 2020/1/7 17:24
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(@NotNull MessageEnum messageEnum) {
        super(messageEnum.getMsg());
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(@NotNull MessageEnum messageEnum, Throwable cause) {
        super(messageEnum.getMsg(), cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

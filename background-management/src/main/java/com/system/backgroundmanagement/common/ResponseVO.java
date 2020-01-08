package com.system.backgroundmanagement.common;

import lombok.*;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * RequestVO（View Object）：显示层对象，通常是 Web 向模板渲染引擎层传输的对象。
 *
 * @author luoyuequan
 * @time 2019/12/17 14:25
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ResponseVO {
    /**
     * 状态码
     * 成功1
     * 失败0
     * 参数无效400
     */
    private Integer code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;


    /**
     * 成功响应，有响应数据
     *
     * @param messageEnum 消息
     * @param data        反馈数据
     * @return responseVo
     */
    @NotNull
    public static ResponseVO success(@NotNull MessageEnum messageEnum, Object data) {
        return getInstance(1, messageEnum.getMsg(), data);
    }

    /**
     * 成功响应，无响应数据
     *
     * @param messageEnum 消息
     * @return responseVo
     */
    @NotNull
    public static ResponseVO success(MessageEnum messageEnum) {
        return success(messageEnum, null);
    }

    /**
     * 失败响应，无响应数据
     *
     * @param messageEnum 消息
     * @return responseVo
     */
    @NotNull
    public static ResponseVO error(MessageEnum messageEnum) {
        return error(messageEnum, null);
    }

    /**
     * 异常响应消息
     *
     * @param message 消息
     * @return responseVo
     */
    @NotNull
    public static ResponseVO exception(String message) {
        return getInstance(0, message, null);
    }

    /**
     * 异常响应消息
     *
     * @param message 消息
     * @param data    异常数据
     * @return responseVo
     */
    @NotNull
    public static ResponseVO exception(String message, Object data) {
        return getInstance(400, message, data);
    }

    /**
     * 失败响应，有响应数据
     *
     * @param messageEnum 消息
     * @param data        响应数据
     * @return responseVo
     */
    @NotNull
    public static ResponseVO error(@NotNull MessageEnum messageEnum, Object data) {
        return getInstance(0, messageEnum.getMsg(), data);
    }

    /**
     * 私有 实例
     *
     * @param code 状态码
     * @param msg  响应消息
     * @param data 响应数据
     * @return responseVo
     */
    @NotNull
    @Contract("_, _, _ -> new")
    private static ResponseVO getInstance(Integer code, String msg, Object data) {
        return new ResponseVO(code, msg, data);
    }
}

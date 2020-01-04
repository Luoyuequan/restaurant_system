package com.system.backgroundmanagement.common;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * VO（View Object）：显示层对象，通常是 Web 向模板渲染引擎层传输的对象。
 *
 * @author luoyuequan
 * @time 2019/12/17 14:25
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ReturnVO {
    /**
     * 状态码
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
     * 无数据构造
     *
     * @param code 响应码
     * @param msg  响应消息
     */
    private ReturnVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private static ReturnVO getInstance(Integer code, String msg, Object data) {
        return new ReturnVO(code, msg, data);
    }

    /**
     * 成功响应，有响应数据
     *
     * @param messageEnum 消息
     * @param data        反馈数据
     * @return vo
     */
    public static ReturnVO success(MessageEnum messageEnum, Object data) {
        return getInstance(messageEnum.getCode(), messageEnum.getMsg(), data);
    }

    /**
     * 成功响应，无响应数据
     *
     * @param messageEnum 消息
     * @return vo
     */
    public static ReturnVO success(MessageEnum messageEnum) {
        return success(messageEnum, null);
    }

    /**
     * 失败响应，无响应数据
     *
     * @param messageEnum 消息
     * @return vo
     */
    public static ReturnVO error(MessageEnum messageEnum) {
        return error(messageEnum, null);
    }

    /**
     * 失败响应，有响应数据
     *
     * @param messageEnum 消息
     * @param data        响应数据
     * @return vo
     */
    public static ReturnVO error(MessageEnum messageEnum, Object data) {
        return getInstance(messageEnum.getCode(), messageEnum.getMsg(), data);
    }
}

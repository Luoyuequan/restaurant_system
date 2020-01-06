package com.system.backgroundmanagement.common;

/**
 * 消息枚举类
 * 可自行添加消息
 *
 * @author luoyuequan
 * @time 2019/12/18 10:31
 */
public enum MessageEnum {
    /**
     * 无此数据
     */
    DATA_NO(0, "无此数据"),
    /**
     * 数据无效
     */
    DATA_INVALID(0, "数据无效"),
    /**
     * 操作成功 提示语
     */
    ACTION_SUCCESS(1, "操作成功"),
    /**
     * 新增失败
     */
    ADD_ERROR(0, "新增失败"),
    /**
     * 新增成功
     */
    ADD_SUCCESS(1, "新增成功"),
    /**
     * 删除失败
     */
    DELETE_ERROR(0, "删除失败"),
    /**
     * 删除成功
     */
    DELETE_SUCCESS(1, "删除成功"),
    /**
     * 查询失败
     */
    FIND_ERROR(0, "查询失败"),
    /**
     * 查询成功 提示语
     */
    FIND_SUCCESS(1, "查询成功"),
    /**
     * 注销成功
     */
    LOGOUT_SUCCESS(1, "注销成功"),
    /**
     * 注销失败
     */
    LOGOUT_ERROR(0, "注销失败"),
    /**
     * 登录成功
     */
    LOGIN_SUCCESS(1, "登录成功"),
    /**
     * 登录错误
     */
    LOGIN_ERROR(0, "用户名或密码错误"),
    /**
     * 修改失败
     */
    UPDATE_ERROR(0, "修改失败"),
    /**
     * 接收的参数无效
     */
    VARIABLE_INVALID_ERROR(0, "参数无效,请检查后,重新尝试"),

    /**
     * 接收的参数数量不符合要求
     */
    VARIABLE_MISS_ERROR(0, "参数缺失,请检查后,重新尝试");
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String msg;

    MessageEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

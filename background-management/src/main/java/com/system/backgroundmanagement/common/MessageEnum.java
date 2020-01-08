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
     * 文件删除失败
     */
    FILE_DELETE_ERROR("文件删除失败"),

    /**
     * 文件大小超出
     */
    FILE_SIZE_MAX("文件大小超出"),

    /**
     * 文件上传失败
     */
    FILE_UPLOAD_ERROR("文件上传失败"),

    /**
     * 操作成功 提示语
     */
    ACTION_SUCCESS("操作成功"),

    /**
     * 新增失败
     */
    ADD_ERROR("新增失败"),

    /**
     * 新增成功
     */
    ADD_SUCCESS("新增成功"),

    /**
     * 数据无效
     */
    DATA_INVALID("数据无效"),

    /**
     * 不能为空
     */
    DATA_NOT_BLANK("不能为空"),

    /**
     * 无此数据
     */
    DATA_NO("无此数据"),

    /**
     * 删除失败
     */
    DELETE_ERROR("删除失败"),

    /**
     * 删除成功
     */
    DELETE_SUCCESS("删除成功"),

    /**
     * 查询失败
     */
    FIND_ERROR("查询失败"),

    /**
     * 查询成功 提示语
     */
    FIND_SUCCESS("查询成功"),

    /**
     * 登录错误
     */
    LOGIN_ERROR("用户名或密码错误"),

    /**
     * 登录成功
     */
    LOGIN_SUCCESS("登录成功"),

    /**
     * 注销失败
     */
    LOGOUT_ERROR("注销失败"),

    /**
     * 注销成功
     */
    LOGOUT_SUCCESS("注销成功"),

    /**
     * 接受的时间范围值错误
     */
    TIME_ERROR("时间范围错误"),

    /**
     * 修改失败
     */
    UPDATE_ERROR("修改失败"),

    /**
     * 接收的参数无效
     */
    VARIABLE_INVALID_ERROR("参数无效"),

    /**
     * 接收的参数数量不符合要求
     */
    VARIABLE_MISS_ERROR("参数缺失");

    /**
     * 消息
     */
    private String msg;

    MessageEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

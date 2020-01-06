package com.system.backgroundmanagement.common;

import javax.servlet.http.HttpServletRequest;

/**
 * base Controller 接口
 *
 * @param <T> 对象泛型
 * @author luoyuequan
 * @time 2020/1/6 17:17
 */
public interface IController<T> {

    /**
     * 获取单一对象
     *
     * @param vo      从前端接受的参数集
     * @param request request请求
     * @return 响应前端的数据对象
     */
    default ReturnVO get(VO vo, HttpServletRequest request) {
        return null;
    }

    /**
     * 对象列表集查询
     *
     * @param vo      从前端接受的参数集
     * @param request request请求
     * @return 响应前端的数据对象
     */
    default ReturnVO list(VO vo, HttpServletRequest request) {
        return null;
    }

    /**
     * 修改指定对象
     *
     * @param data    从前端接受的待修改的对象
     * @param request request请求
     * @return 响应前端的数据对象
     */
    default ReturnVO modify(T data, HttpServletRequest request) {
        return null;
    }

    /**
     * 添加对象请求
     *
     * @param data    从前端接受的待新增的对象
     * @param request request请求
     * @return 响应前端的数据对象
     */
    default ReturnVO save(T data, HttpServletRequest request) {
        return null;
    }

    /**
     * 删除对象请求
     *
     * @param vo      从前端接受的参数集
     * @param request request请求
     * @return 响应前端的数据对象
     */
    default ReturnVO delete(VO vo, HttpServletRequest request) {
        return null;
    }
}

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
     * @param requestVo 从前端接受的参数集
     * @param request   request请求
     * @return 响应前端的数据对象
     */
    default ResponseVO getOne(RequestVO requestVo, HttpServletRequest request) {
        return null;
    }


    /**
     * 对象列表集查询
     *
     * @param pageVO    分页参数
     * @param requestVo 从前端接受的参数集
     * @param request   request请求
     * @return 响应前端的数据对象
     */
    default ResponseVO list(PageVO pageVO, RequestVO requestVo, HttpServletRequest request) {
        return null;
    }

    /**
     * 对象列表集查询
     * 无分页
     *
     * @param requestVO 请求参数集
     * @param request   request请求
     * @return 响应数据
     */
    default ResponseVO list(RequestVO requestVO, HttpServletRequest request) {
        return list(null, requestVO, request);
    }

    /**
     * 对象列表集查询
     * 无分页
     * 无request请求对象
     *
     * @param requestVO 请求参数集
     * @return 响应数据
     */
    default ResponseVO list(RequestVO requestVO) {
        return list(null, requestVO, null);
    }

    /**
     * 修改指定对象
     *
     * @param data    从前端接受的待修改的对象
     * @param request request请求
     * @return 响应前端的数据对象
     */
    default ResponseVO modify(T data, HttpServletRequest request) {
        return null;
    }

    /**
     * 添加对象请求
     *
     * @param data    从前端接受的待新增的对象
     * @param request request请求
     * @return 响应前端的数据对象
     */
    default ResponseVO save(T data, HttpServletRequest request) {
        return null;
    }

    /**
     * 删除对象请求
     *
     * @param requestVo 从前端接受的参数集
     * @param request   request请求
     * @return 响应前端的数据对象
     */
    default ResponseVO delete(RequestVO requestVo, HttpServletRequest request) {
        return null;
    }
}

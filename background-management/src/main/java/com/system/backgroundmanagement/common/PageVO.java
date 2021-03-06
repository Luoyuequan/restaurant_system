package com.system.backgroundmanagement.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 接受前端分页参数
 *
 * @author luoyuequan
 * @time 2020/1/6 17:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PageVO {
    /**
     * 当前页码
     */
    private Integer page;
    /**
     * 每页数量
     */
    private Integer size;

    ///////////////////////////////////////////////////////////////////////////
    // 每次实例化之前,设置当前页码和每页数量的初始值
    // 应用与分页查询时,未传分页参数
    ///////////////////////////////////////////////////////////////////////////
    {
        this.page = 1;
        this.size = 10;
    }
}

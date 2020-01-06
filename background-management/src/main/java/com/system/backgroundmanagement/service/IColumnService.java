package com.system.backgroundmanagement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.system.backgroundmanagement.common.PageVO;
import com.system.backgroundmanagement.common.RequestVO;
import com.system.backgroundmanagement.common.ResponseVO;
import com.system.backgroundmanagement.entity.Column;

import java.util.List;

/**
 * <p>
 * 栏目管理表 服务类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
public interface IColumnService extends IService<Column> {

    /**
     * 条件查询+分页
     *
     * @param pageVO
     * @param requestVo
     * @return
     */
    IPage<Column> listColumn(PageVO pageVO, RequestVO requestVo);

    /**
     * 修改栏目信息
     *
     * @param column
     * @return
     */
    ResponseVO updateColumn(Column column);

    /**
     * 删除指定栏目
     *
     * @param idList
     * @return
     */
    boolean deleteByIds(List<Long> idList);
}

package com.system.backgroundmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.system.backgroundmanagement.common.RequestVO;
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
     * 全部栏目信息以及栏目类型信息
     *
     * @param requestVo
     * @return
     */
    List<Column> listColumn(RequestVO requestVo);

    /**
     * 修改栏目信息
     *
     * @param column
     * @return
     */
    boolean updateColumn(Column column);

    /**
     * 删除指定栏目
     *
     * @param idList
     * @return
     */
    boolean deleteByIds(List<Long> idList);

    /**
     * 根据栏目id
     * 获取栏目信息以及栏目类型信息
     *
     * @param id id
     * @return
     */
    Column getColumnInfoById(Long id);
}

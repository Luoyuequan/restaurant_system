package com.system.backgroundmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.system.backgroundmanagement.entity.ColumnType;

import java.util.List;

/**
 * <p>
 * 栏目类型 服务类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
public interface IColumnTypeService extends IService<ColumnType> {

    /**
     * 修改栏目类型信息
     *
     * @param columnType 栏目类型对象
     * @return 修改结果
     */
    boolean updateColumnTypeInfo(ColumnType columnType);

    /**
     * 删除栏目类型信息
     * 可批量删除
     *
     * @param idList
     * @return
     */
    boolean deleteByIds(List<Long> idList);
}

package com.system.backgroundmanagement.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.system.backgroundmanagement.entity.Column;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 栏目管理表 Mapper 接口
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
public interface ColumnDao extends BaseMapper<Column> {
    /**
     * 根据栏目父id
     * 是否已逻辑删除
     * 查询
     * 栏目和栏目类型 连表结果
     *
     * @param pid     父id
     * @param deleted 是否已逻辑删除
     * @return 子栏目集
     */
    List<Column> selectColumnAndTypeByPid(@Param("pid") Long pid, @Param("deleted") Boolean deleted);

    /**
     * 根据父栏目id 0 ，即为根栏目层级
     * 是否已逻辑删除 0，未删除
     * 查询
     * 栏目和栏目类型 连表结果
     *
     * @return 子栏目集
     */
    default List<Column> selectColumnAndTypeByPid() {
        return selectColumnAndTypeByPid(null, null);
    }

    /**
     * @param pid
     * @return
     */
    default List<Column> selectColumnAndTypeByPid(Long pid) {
        return selectColumnAndTypeByPid(pid, null);
    }
}

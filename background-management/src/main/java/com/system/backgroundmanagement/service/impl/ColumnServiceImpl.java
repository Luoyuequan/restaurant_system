package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.common.RequestVO;
import com.system.backgroundmanagement.dao.ColumnDao;
import com.system.backgroundmanagement.entity.Column;
import com.system.backgroundmanagement.entity.ColumnType;
import com.system.backgroundmanagement.service.IColumnService;
import com.system.backgroundmanagement.service.IColumnTypeService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 栏目管理表 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
@Slf4j
public class ColumnServiceImpl extends ServiceImpl<ColumnDao, Column> implements IColumnService {

    private final IColumnTypeService columnTypeService;

    public ColumnServiceImpl(IColumnTypeService columnTypeService) {
        this.columnTypeService = columnTypeService;
    }

    @Override
    public List<Column> listColumn(@NotNull RequestVO requestVo) {
        //columnId 作为 pid 条件传入
        //根节点栏目id为0
        long pid = requestVo.getColumnId() == null ? 0 : requestVo.getColumnId();
        //默认根栏目层级,未删除的条件
        List<Column> columnList = baseMapper.selectColumnAndTypeByPid(pid);
        if (CollectionUtils.isEmpty(columnList)) {
            return null;
        }
        getChildColumnList(columnList);
        return columnList;
    }

    private void getChildColumnList(@NotNull List<Column> columnList) {
        for (Column parentColumn : columnList) {
            //父级id
            Long id = parentColumn.getId();
            List<Column> childColumn = baseMapper.selectColumnAndTypeByPid(id);
            if (CollectionUtils.isEmpty(childColumn)) {
                continue;
            }
            //设置二级栏目层级
            parentColumn.setChildColumn(childColumn);
            //
            getChildColumnList(childColumn);
        }
    }

    @Override
    public boolean updateColumn(Column column) {
        AtomicBoolean removeResult = new AtomicBoolean(false);
        try {
            UpdateWrapper<Column> updateWrapper = new UpdateWrapper<>();
            updateWrapper.ge("id", column.getId());
            removeResult.set(update(column, updateWrapper));
            if (!removeResult.get()) {
                log.warn("修改失败,column:{}", column);
            }
        } catch (Exception e) {
            log.warn("修改失败,column:{}", column, e.getCause());
        }
        return removeResult.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(@NotNull List<Long> idList) {
        Long[] ids = idList.toArray(new Long[0]);
        AtomicBoolean removeResult = new AtomicBoolean(false);
        try {
            removeResult.set(removeByIds(idList));
            if (!removeResult.get()) {
                log.warn("批量删除失败,ids:{}", Arrays.toString(ids));
            }
        } catch (Exception e) {
            log.warn("批量删除异常,ids:{}", Arrays.toString(ids), e);
        }
        return removeResult.get();
    }

    @Override
    public Column getColumnInfoById(Long id) {
        //获取栏目信息
        Column column = getById(id);
        //根据栏目类型id，获取栏目类型信息
        ColumnType columnType = columnTypeService.getById(column.getColumnTypeId());
        return column.setColumnType(columnType);
    }
}

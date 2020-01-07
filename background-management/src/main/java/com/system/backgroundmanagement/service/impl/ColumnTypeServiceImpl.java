package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.dao.ColumnTypeDao;
import com.system.backgroundmanagement.entity.ColumnType;
import com.system.backgroundmanagement.service.IColumnTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 栏目类型 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
@Slf4j
public class ColumnTypeServiceImpl extends ServiceImpl<ColumnTypeDao, ColumnType> implements IColumnTypeService {

    @Override
    public boolean updateColumnTypeInfo(ColumnType columnType) {
        AtomicBoolean removeResult = new AtomicBoolean(false);
        try {
            UpdateWrapper<ColumnType> updateWrapper = new UpdateWrapper<>();
            updateWrapper.ge("id", columnType.getId());
            removeResult.set(update(columnType, updateWrapper));
            if (!removeResult.get()) {
                log.warn("修改失败,columnType:{}", columnType);
            }
        } catch (Exception e) {
            log.warn("修改失败,columnType:{}", columnType, e.getCause());
        }
        return removeResult.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(List<Long> idList) {
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
}

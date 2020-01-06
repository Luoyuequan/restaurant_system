package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.common.PageVO;
import com.system.backgroundmanagement.common.RequestVO;
import com.system.backgroundmanagement.common.ResponseVO;
import com.system.backgroundmanagement.dao.ColumnDao;
import com.system.backgroundmanagement.entity.Column;
import com.system.backgroundmanagement.service.IColumnService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 栏目管理表 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnDao, Column> implements IColumnService {

    @Override
    public IPage<Column> listColumn(PageVO pageVO, RequestVO requestVo) {
        return null;
    }

    @Override
    public ResponseVO updateColumn(Column column) {
        return null;
    }

    @Override
    public boolean deleteByIds(List<Long> idList) {
        return false;
    }
}

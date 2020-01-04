package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.dao.ColumnDao;
import com.system.backgroundmanagement.entity.Column;
import com.system.backgroundmanagement.service.IColumnService;
import org.springframework.stereotype.Service;

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

}

package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.dao.AdminDao;
import com.system.backgroundmanagement.entity.Admin;
import com.system.backgroundmanagement.service.IAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员模块 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements IAdminService {

}

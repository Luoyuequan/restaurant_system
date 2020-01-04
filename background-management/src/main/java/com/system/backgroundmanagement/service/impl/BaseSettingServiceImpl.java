package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.dao.BaseSettingDao;
import com.system.backgroundmanagement.entity.BaseSetting;
import com.system.backgroundmanagement.service.IBaseSettingService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站基本设置 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
public class BaseSettingServiceImpl extends ServiceImpl<BaseSettingDao, BaseSetting> implements IBaseSettingService {

}

package com.system.backgroundmanagement.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 数据库时间相关的字段自动填充为当前时间戳
 * </p>
 *
 * @author luoyuequan
 * @date 2020/01/05
 * @time 下午 04:49
 */
@Component
@Slf4j
public class TimeFieldFillConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        long currentTimeMillis = System.currentTimeMillis();
        this.setFieldValByName("createTime", currentTimeMillis, metaObject);
        this.setFieldValByName("updateTime", currentTimeMillis, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        long currentTimeMillis = System.currentTimeMillis();
        this.setFieldValByName("updateTime", currentTimeMillis, metaObject);
    }
}

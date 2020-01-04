package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.dao.MessageDao;
import com.system.backgroundmanagement.entity.Message;
import com.system.backgroundmanagement.service.IMessageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 留言表 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements IMessageService {

}

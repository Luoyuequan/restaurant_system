package com.system.frontmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.frontmanagement.dao.MessageDao;
import com.system.frontmanagement.entity.Message;
import com.system.frontmanagement.service.IMessageService;
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

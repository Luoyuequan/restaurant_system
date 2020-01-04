package com.system.backgroundmanagement.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.system.backgroundmanagement.common.ReturnVO;
import com.system.backgroundmanagement.entity.Message;

import java.util.Map;

/**
 * <p>
 * 留言表 服务类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
public interface IMessageService extends IService<Message> {

    ReturnVO saveMessage(Message message);

    ReturnVO listMessage(Page<Message> messagePage);

    ReturnVO searchList(Map map);

    ReturnVO deleteMessage(Long id);
}

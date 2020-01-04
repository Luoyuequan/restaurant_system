package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.common.MessageEnum;
import com.system.backgroundmanagement.common.ReturnVO;
import com.system.backgroundmanagement.dao.MessageDao;
import com.system.backgroundmanagement.entity.Message;
import com.system.backgroundmanagement.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 留言表 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
@Slf4j
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements IMessageService {

    @Override
    public ReturnVO saveMessage(Message message) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            boolean save = save(message.setCreateTime(currentTimeMillis).setUpdateTime(currentTimeMillis));
            if (save) {
                return ReturnVO.success(MessageEnum.ADD_SUCCESS);
            }
            return ReturnVO.success(MessageEnum.ADD_ERROR);
        } catch (Exception e) {
            log.warn("{}", MessageEnum.ADD_ERROR.getMsg(), e);
            return ReturnVO.success(MessageEnum.ADD_ERROR);
        }
    }

    @Override
    public ReturnVO listMessage(Page<Message> messagePage) {

        List<Message> messageList = list();
        return null;
    }

    @Override
    public ReturnVO searchList(Map map) {
        return null;
    }

    @Override
    public ReturnVO deleteMessage(Long id) {
        return null;
    }
}

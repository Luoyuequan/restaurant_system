package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.common.MessageEnum;
import com.system.backgroundmanagement.common.ReturnVO;
import com.system.backgroundmanagement.common.VO;
import com.system.backgroundmanagement.dao.MessageDao;
import com.system.backgroundmanagement.entity.Message;
import com.system.backgroundmanagement.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
    public ReturnVO listMessage(VO vo) {
        try {
            QueryWrapper<Message> messageQuery = new QueryWrapper<>();
            messageQuery.likeRight("name", vo.getName());
            IPage<Message> messagePage = new Page<>();
            messagePage.setCurrent(vo.getPage()).setSize(vo.getSize());
            IPage<Message> messageList = page(messagePage, messageQuery);
            return ReturnVO.success(MessageEnum.FIND_SUCCESS, messageList);
        } catch (Exception e) {
            return ReturnVO.error(MessageEnum.FIND_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(@NotNull List<Long> idList) {
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

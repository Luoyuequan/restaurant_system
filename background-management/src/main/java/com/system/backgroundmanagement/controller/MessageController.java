package com.system.backgroundmanagement.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.system.backgroundmanagement.common.MessageEnum;
import com.system.backgroundmanagement.common.ReturnVO;
import com.system.backgroundmanagement.entity.Message;
import com.system.backgroundmanagement.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 留言表 前端控制器
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {
    private final IMessageService messageService;

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 游客留言请求接口
     *
     * @param message 消息体
     * @return vo
     */
    @PostMapping("add")
    public ReturnVO save(@RequestBody Message message) {
        boolean checked = message.getName() == null || message.getContent() == null;
        if (checked) {
            return ReturnVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        return messageService.saveMessage(message);
    }

    /**
     * 获取留言表和分页接口
     *
     * @param messagePage
     * @return
     */
    @GetMapping("list")
    public ReturnVO list(@RequestBody Page<Message> messagePage) {
        return messageService.listMessage(messagePage);
    }

    /**
     * 对留言列表进行关键字搜索接口
     *
     * @param map
     * @return
     */
    @PostMapping("search")
    public ReturnVO search(@RequestBody Map map) {
        return messageService.searchList(map);
    }

    /**
     * 删除指定id留言记录接口
     *
     * @param id
     * @return
     */
    @DeleteMapping("del/{id}")
    public ReturnVO deleteMessage(@PathVariable Long id) {
        return messageService.deleteMessage(id);
    }
}

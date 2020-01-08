package com.system.backgroundmanagement.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.system.backgroundmanagement.common.*;
import com.system.backgroundmanagement.entity.Message;
import com.system.backgroundmanagement.service.IMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 留言表 前端控制器
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@RestController
@Api(tags = "留言消息管理接口")
@RequestMapping("message")
@Slf4j
public class MessageController {
    private final IMessageService messageService;

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 游客留言添加请求接口
     *
     * @param message 消息体
     * @return vo
     */
    @PostMapping("add")
    @ApiOperation("游客留言添加请求接口")
    @ApiImplicitParam(name = "message", value = "留言消息体", dataTypeClass = Message.class, required = true)
    public ResponseVO save(@NotNull @RequestBody Message message, HttpServletRequest request) {
        //校验新增的留言消息的非空参数是否符合
        boolean checked = message.getName() == null || message.getContent() == null;
        if (checked) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        AtomicBoolean saveResult = new AtomicBoolean(false);
        try {
            String ip = request.getRemoteAddr();
            message.setIp(ip);
            saveResult.set(messageService.save(message));
        } catch (Exception e) {
            log.warn("留言消息添加异常,{}", message, e.getCause());
        }
        return saveResult.get() ? ResponseVO.success(MessageEnum.ADD_SUCCESS) : ResponseVO.error(MessageEnum.ADD_ERROR);
    }

    /**
     * 根据分页和关键字查询(非必须)
     * 获取留言列表接口
     *
     * @param pageVO    分页参数
     * @param requestVo 请求参数条件
     * @param request   request请求
     * @return
     */
    @GetMapping("list")
    @ApiOperation("根据分页和关键字查询获取留言列表接口")
    public ResponseVO list(PageVO pageVO, RequestVO requestVo, HttpServletRequest request) {
        try {
            IPage<Message> listMessage = messageService.listMessage(pageVO, requestVo);
            return ResponseVO.success(MessageEnum.FIND_SUCCESS, listMessage);
        } catch (RuntimeException e) {
            log.warn("uri:{},msg:{}", request.getRequestURI(), MessageEnum.FIND_ERROR.getMsg(), e.getCause());
            return ResponseVO.error(MessageEnum.FIND_ERROR);
        }
    }

    /**
     * 获取指定id留言消息
     *
     * @param requestVo 请求参数(id)
     * @return requestVo
     */
    @GetMapping("get")
    @ApiOperation("获取指定id留言消息")
    public ResponseVO getById(@NotNull RequestVO requestVo, HttpServletRequest request) {
        Long id = requestVo.getId();
        if (id == null) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        try {
            Message message = messageService.getById(id);
            return ResponseVO.success(MessageEnum.FIND_SUCCESS, message);
        } catch (Exception e) {
            log.warn("uri:{},msg:{}", request.getRequestURI(), MessageEnum.FIND_ERROR.getMsg(), e.getCause());
            return ResponseVO.error(MessageEnum.FIND_ERROR);
        }
    }

    /**
     * 删除指定id集合的留言记录接口
     *
     * @param requestVo 请求参数(多个id由英文逗号拼接成字符串)
     * @return requestVo
     */
    @DeleteMapping("del")
    @ApiOperation("删除指定id集合的留言记录接口")
    public ResponseVO deleteMessage(@NotNull RequestVO requestVo) {
        List<Long> idList = new ArrayList<>();
        ResponseVO checkResult = ParamCheckUtils.checkBatchIds(requestVo.getIds(), idList);
        if (checkResult != null) {
            return checkResult;
        }
        //根据id批量删除
        return messageService.deleteByIds(idList) ?
                ResponseVO.success(MessageEnum.DELETE_SUCCESS) : ResponseVO.error(MessageEnum.DELETE_ERROR);
    }
}

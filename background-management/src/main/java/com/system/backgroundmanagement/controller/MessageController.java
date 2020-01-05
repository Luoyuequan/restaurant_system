package com.system.backgroundmanagement.controller;


import com.system.backgroundmanagement.common.MessageEnum;
import com.system.backgroundmanagement.common.ParamCheckUtils;
import com.system.backgroundmanagement.common.ReturnVO;
import com.system.backgroundmanagement.common.VO;
import com.system.backgroundmanagement.entity.Message;
import com.system.backgroundmanagement.service.IMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/message")
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
    public ReturnVO save(@NotNull @RequestBody Message message) {
        //校验新增的留言消息的非空参数是否符合
        boolean checked = message.getName() == null || message.getContent() == null;
        if (checked) {
            return ReturnVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        AtomicBoolean saveResult = new AtomicBoolean(false);
        try {
            saveResult.set(messageService.save(message));
        } catch (Exception e) {
            log.warn("留言消息添加异常,{}", message, e.getCause());
        }
        return saveResult.get() ? ReturnVO.success(MessageEnum.ADD_SUCCESS) : ReturnVO.success(MessageEnum.ADD_ERROR);
    }

    /**
     * 根据分页和关键字查询(非必须)
     * 获取留言列表接口
     *
     * @param vo 请求参数(包含分页,关键字查询)
     * @return vo
     */
    @GetMapping("list")
    @ApiOperation("根据分页和关键字查询获取留言列表接口")
    public ReturnVO list(VO vo) {
        return messageService.listMessage(vo);
    }

    /**
     * 获取指定id留言消息
     *
     * @param id id
     * @return vo
     */
    @GetMapping("get/{id}")
    @ApiOperation("获取指定id留言消息")
    @ApiImplicitParam(name = "id", value = "留言消息id", dataTypeClass = Long.class)
    public ReturnVO getById(@PathVariable Long id) {
        Message message = messageService.getById(id);
        return ReturnVO.success(MessageEnum.FIND_SUCCESS, message);
    }

    /**
     * 删除指定id集合的留言记录接口
     *
     * @param ids 请求参数(多个id由英文逗号拼接成字符串)
     * @return vo
     */
    @DeleteMapping("del/{ids}")
    @ApiOperation("删除指定id集合的留言记录接口")
    @ApiImplicitParam(name = "ids", value = "请求参数(多个id由英文逗号拼接成字符串)",
            dataTypeClass = String.class, required = true
    )
    public ReturnVO deleteMessage(@NotNull @PathVariable String ids) {
        List<Long> idList = new ArrayList<>();
        ReturnVO checkResult = ParamCheckUtils.checkBatchIds(ids, idList);
        if (checkResult != null) {
            return checkResult;
        }
        //根据id批量删除
        return messageService.deleteByIds(idList) ?
                ReturnVO.success(MessageEnum.DELETE_SUCCESS) : ReturnVO.success(MessageEnum.DELETE_ERROR);
    }
}

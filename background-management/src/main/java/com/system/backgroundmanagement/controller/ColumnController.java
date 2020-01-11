package com.system.backgroundmanagement.controller;


import com.system.backgroundmanagement.common.*;
import com.system.backgroundmanagement.entity.Column;
import com.system.backgroundmanagement.service.IColumnService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 栏目管理表 前端控制器
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/column")
@Slf4j
@Api(tags = "栏目信息管理模块")
public class ColumnController implements IController<Column> {
    private final IColumnService columnService;

    public ColumnController(IColumnService columnService) {
        this.columnService = columnService;
    }

    @GetMapping("get")
    @Override
    public ResponseVO getOne(@NotNull RequestVO requestVo, HttpServletRequest request) {
        ResponseVO responseVO = ParamCheckUtils.checkValues(requestVo.getId());
        if (responseVO != null) {
            return responseVO;
        }
        try {
            Column column = columnService.getColumnInfoById(requestVo.getId());
            return ResponseVO.success(MessageEnum.FIND_SUCCESS, column);
        } catch (Exception e) {
            log.warn("uri:{},msg:{}", request.getRequestURI(), MessageEnum.FIND_ERROR.getMsg(), e.getCause());
            return ResponseVO.error(MessageEnum.FIND_ERROR);
        }
    }

    /**
     * 获取栏目列表信息集合
     *
     * @param requestVo 从前端接受的参数集
     * @param request   request请求
     * @return
     */
    @GetMapping("list")
    @Override
    public ResponseVO list(RequestVO requestVo, HttpServletRequest request) {
        try {
            List<Column> listColumn = columnService.listColumn(requestVo);
            return ResponseVO.success(MessageEnum.FIND_SUCCESS, listColumn);
        } catch (RuntimeException e) {
            log.warn("uri:{},msg:{}", request.getRequestURI(), MessageEnum.FIND_ERROR.getMsg(), e.getCause());
            return ResponseVO.error(MessageEnum.FIND_ERROR);
        }
    }

    @PutMapping("update")
    @Override
    public ResponseVO modify(@NotNull @RequestBody Column column, HttpServletRequest request) {
        ResponseVO responseVO = ParamCheckUtils.checkValues(column.getId());
        if (responseVO != null) {
            return responseVO;
        }
        return columnService.updateColumn(column) ?
                ResponseVO.success(MessageEnum.ACTION_SUCCESS) : ResponseVO.error(MessageEnum.UPDATE_ERROR);
    }

    @PostMapping("add")
    @Override
    public ResponseVO save(@NotNull @RequestBody Column column, HttpServletRequest request) {
        //检查栏目 name，栏目类型id，排序值是否非空
        ResponseVO responseVO = ParamCheckUtils.checkValues(column.getName(), column.getColumnTypeId(), column.getRankValue());
        if (responseVO != null) {
            return responseVO;
        }
        boolean saveResult = columnService.save(column);
        return saveResult ? ResponseVO.success(MessageEnum.ADD_SUCCESS) :
                ResponseVO.error(MessageEnum.ADD_ERROR);
    }

    /**
     * 批量删除
     *
     * @param requestVo ids
     * @param request   request请求
     * @return
     */
    @DeleteMapping("del")
    @Override
    public ResponseVO delete(@NotNull RequestVO requestVo, HttpServletRequest request) {
        List<Long> idList = new ArrayList<>();
        ResponseVO checkResult = ParamCheckUtils.checkBatchIds(requestVo.getIds(), idList);
        if (checkResult != null) {
            return checkResult;
        }
        //根据id批量删除
        return columnService.deleteByIds(idList) ? ResponseVO.success(MessageEnum.DELETE_SUCCESS) :
                ResponseVO.error(MessageEnum.DELETE_ERROR);
    }
}

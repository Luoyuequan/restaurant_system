package com.system.backgroundmanagement.controller;


import com.system.backgroundmanagement.common.*;
import com.system.backgroundmanagement.entity.ColumnType;
import com.system.backgroundmanagement.service.IColumnTypeService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 栏目类型 前端控制器
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/column-type")
@Slf4j
public class ColumnTypeController implements IController<ColumnType> {
    private final IColumnTypeService columnTypeService;

    public ColumnTypeController(IColumnTypeService columnTypeService) {
        this.columnTypeService = columnTypeService;
    }

    /**
     * 获取栏目类型
     *
     * @param requestVo 从前端接受的参数集
     * @param request   request请求
     * @return 响应参数
     */
    @Override
    @GetMapping("get")
    public ResponseVO getOne(@NotNull RequestVO requestVo, HttpServletRequest request) {
        ResponseVO responseVO = ParamCheckUtils.checkValues(requestVo.getId());
        if (responseVO != null) {
            return responseVO;
        }
        try {
            ColumnType columnType = columnTypeService.getById(requestVo.getId());
            return ResponseVO.success(MessageEnum.FIND_SUCCESS, columnType);
        } catch (Exception e) {
            log.warn("uri:{},msg:{}", request.getRequestURI(), MessageEnum.FIND_ERROR.getMsg(), e.getCause());
            return ResponseVO.error(MessageEnum.FIND_ERROR);
        }
    }

    /**
     * 获取栏目类型集合请求接口
     * 无分页
     *
     * @param requestVo 从前端接受的参数集
     * @param request   request请求
     * @return 响应参数
     */
    @Override
    @GetMapping("list")
    public ResponseVO list(RequestVO requestVo, HttpServletRequest request) {
        // TODO: 2020/01/06 目前无条件查询,后期根据情况抉择是否需要条件
        try {
            List<ColumnType> columnTypeList = columnTypeService.list();
            return ResponseVO.success(MessageEnum.FIND_SUCCESS, columnTypeList);
        } catch (RuntimeException e) {
            log.warn("uri:{},msg:{}", request.getRequestURI(), MessageEnum.FIND_ERROR.getMsg(), e.getCause());
            return ResponseVO.error(MessageEnum.FIND_ERROR);
        }
    }

    @Override
    @PutMapping("update")
    public ResponseVO modify(@NotNull @RequestBody ColumnType columnType, HttpServletRequest request) {
        //接收的参数是否缺少
        ResponseVO responseVO = ParamCheckUtils.checkValues(columnType.getId(), columnType.getValue());
        if (responseVO != null) {
            return responseVO;
        }
        return columnTypeService.updateColumnTypeInfo(columnType) ?
                ResponseVO.success(MessageEnum.ACTION_SUCCESS) : ResponseVO.success(MessageEnum.UPDATE_ERROR);
    }

    @Override
    @PostMapping("add")
    public ResponseVO save(@RequestBody ColumnType columnType, HttpServletRequest request) {
        //校验新增的栏目类型信息的非空参数是否符合
        ResponseVO responseVO = ParamCheckUtils.checkValues(columnType.getValue());
        if (responseVO != null) {
            return responseVO;
        }
        AtomicBoolean saveResult = new AtomicBoolean(false);
        try {
            saveResult.set(columnTypeService.save(columnType));
        } catch (Exception e) {
            log.warn("栏目类型信息添加异常,{}", columnType, e.getCause());
        }
        return saveResult.get() ? ResponseVO.success(MessageEnum.ADD_SUCCESS) : ResponseVO.success(MessageEnum.ADD_ERROR);
    }

    /**
     * 根据id删除栏目类型信息
     * 可批量删除
     *
     * @param requestVo 从前端接受的参数集
     * @param request   request请求
     * @return 响应数据
     */
    @Override
    @DeleteMapping("del")
    public ResponseVO delete(RequestVO requestVo, HttpServletRequest request) {
        List<Long> idList = new ArrayList<>();
        ResponseVO checkResult = ParamCheckUtils.checkBatchIds(requestVo.getIds(), idList);
        if (checkResult != null) {
            return checkResult;
        }
        //根据id批量删除
        return columnTypeService.deleteByIds(idList) ?
                ResponseVO.success(MessageEnum.DELETE_SUCCESS) : ResponseVO.success(MessageEnum.DELETE_ERROR);
    }
}

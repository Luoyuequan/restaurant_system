package com.system.backgroundmanagement.controller;


import com.system.backgroundmanagement.common.*;
import com.system.backgroundmanagement.entity.ProductionInfo;
import com.system.backgroundmanagement.service.IProductionInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 产品信息 前端控制器
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/production-info")
@Slf4j
@Api(tags = "产品信息管理模块")
public class ProductionInfoController {


    @Autowired
    private IProductionInfoService proInfoService;

    /**
     * 产品信息添加和文件上传接口
     *
     * @param proInfo 新的产品信息
     * @return 响应vo
     */
    @PostMapping(path = "add")
    @ApiOperation("产品信息添加和文件上传接口")
    public ResponseVO saveProInfo(
            ProductionInfo proInfo
    ) {
        //校验新增的产品信息的非空参数是否符合
        boolean checked = proInfo.getTitle() == null || proInfo.getColumnId() == null ||
                proInfo.getRankValue() == null || proInfo.getSimpleInfo() == null || proInfo.getDetailInfo() == null;
        if (checked) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        AtomicBoolean saveResult = new AtomicBoolean(false);
        try {
            saveResult.set(proInfoService.saveProInfoAndImage(proInfo, proInfo.getFile()));
        } catch (Exception e) {
            log.warn("产品信息添加异常,{}", proInfo, e.getCause());
        }
        return saveResult.get() ? ResponseVO.success(MessageEnum.ADD_SUCCESS) : ResponseVO.error(MessageEnum.ADD_ERROR);
    }


    /**
     * 删除指定ids集合的产品信息接口
     *
     * @param requestVo 请求参数(多个id由英文逗号拼接成字符串)
     * @return requestVo
     */
    @DeleteMapping("del")
    @ApiOperation("删除指定ids集合的产品信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestVo", value = "请求信息", dataTypeClass = RequestVO.class, required = true),
    })
    public ResponseVO delProInfo(RequestVO requestVo) {
        List<Long> idList = new ArrayList<>();
        ResponseVO checkResult = ParamCheckUtils.checkBatchIds(requestVo.getIds(), idList);
        if (checkResult != null) {
            return checkResult;
        }
        //根据id批量删除
        return proInfoService.deleteByIds(idList) ?
                ResponseVO.success(MessageEnum.DELETE_SUCCESS) : ResponseVO.error(MessageEnum.DELETE_ERROR);
    }

    /**
     * 根据 title，recommend，top 筛选分页(非必须)
     * 产品信息列表接口
     *
     * @param pageVO    分页参数
     * @param requestVo 条件查询参数
     * @return 响应数据
     */
    @GetMapping("list")
    @ApiOperation(value = "产品信息列表接口", notes = "根据 title，recommend，top 筛选分页(非必须)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestVo", value = "请求信息"),
    })
    public ResponseVO listProInfo(PageVO pageVO, RequestVO requestVo) {
        return proInfoService.listProInfo(pageVO, requestVo);
    }

    /**
     * 获取指定id产品信息
     *
     * @param requestVo 请求参数(id)
     * @return requestVo
     */
    @GetMapping("get")
    @ApiOperation(value = "获取指定id产品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestVo", value = "请求信息"),
    })
    public ResponseVO getProInfo(RequestVO requestVo) {
        Long id = requestVo.getId();
        if (id == null) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        ProductionInfo productionInfo = proInfoService.getProInfo(id);
        if (productionInfo == null) {
            return ResponseVO.success(MessageEnum.DATA_NO);
        }
        return ResponseVO.success(MessageEnum.FIND_SUCCESS, productionInfo);
    }

    /**
     * 修改产品信息接口
     *
     * @param proInfo 产品新的信息
     * @return vo
     */
    @PutMapping("update")
    @ApiOperation("修改产品信息")
    @ApiImplicitParam(name = "companyInfo", value = "公司新的信息", required = true)
    public ResponseVO updateProInfo(@RequestBody ProductionInfo proInfo) {
        boolean checked = proInfo.getId() == null;
        //接收的参数是否缺少
        if (checked) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        return proInfoService.updateProInfo(proInfo) ?
                ResponseVO.success(MessageEnum.ACTION_SUCCESS) : ResponseVO.error(MessageEnum.UPDATE_ERROR);
    }
}

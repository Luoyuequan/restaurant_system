package com.system.backgroundmanagement.controller;


import com.system.backgroundmanagement.common.*;
import com.system.backgroundmanagement.entity.ProductionInfo;
import com.system.backgroundmanagement.service.IProductionInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
public class ProductionInfoController {

    private final IProductionInfoService proInfoService;

    public ProductionInfoController(IProductionInfoService proInfoService) {
        this.proInfoService = proInfoService;
    }

    /**
     * 产品信息添加接口
     *
     * @param proInfo 新的产品信息
     * @return 响应vo
     */
    @PostMapping("add")
    public ResponseVO saveProInfo(@NotNull @RequestBody ProductionInfo proInfo, @RequestParam(name = "img") MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();
        long fileSize = multipartFile.getSize();
        try {
            byte[] fileBytes = multipartFile.getBytes();
            // TODO: 2020/01/07 文件上传，读取配置文件信息，保存到项目路径下
//            new ConfigurationSource.Resource().
            OutputStream stream = new FileOutputStream("");
        } catch (IOException e) {
            log.warn("file upload waring", e.getCause());
        }
        //校验新增的产品信息的非空参数是否符合
        boolean checked = proInfo.getTitle() == null || proInfo.getColumnId() == null ||
                proInfo.getRankValue() == null || proInfo.getSimpleInfo() == null;
        if (checked) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        AtomicBoolean saveResult = new AtomicBoolean(false);
        try {
            saveResult.set(proInfoService.save(proInfo));
        } catch (Exception e) {
            log.warn("产品信息添加异常,{}", proInfo, e.getCause());
        }
        return saveResult.get() ? ResponseVO.success(MessageEnum.ADD_SUCCESS) : ResponseVO.success(MessageEnum.ADD_ERROR);
    }


    /**
     * 删除指定id集合的产品信息接口
     *
     * @param requestVo 请求参数(多个id由英文逗号拼接成字符串)
     * @return requestVo
     */
    @DeleteMapping("del")
    public ResponseVO delProInfo(@NotNull RequestVO requestVo) {
        List<Long> idList = new ArrayList<>();
        ResponseVO checkResult = ParamCheckUtils.checkBatchIds(requestVo.getIds(), idList);
        if (checkResult != null) {
            return checkResult;
        }
        //根据id批量删除
        return proInfoService.deleteByIds(idList) ?
                ResponseVO.success(MessageEnum.DELETE_SUCCESS) : ResponseVO.success(MessageEnum.DELETE_ERROR);
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
    public ResponseVO listProInfo(PageVO pageVO, RequestVO requestVo) {
        return proInfoService.listProInfo(pageVO, requestVo);
    }

    /**
     * 获取指定id留言消息
     *
     * @param requestVo 请求参数(id)
     * @return requestVo
     */
    @GetMapping("get")
    public ResponseVO getProInfo(@NotNull RequestVO requestVo) {
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
    @ApiImplicitParam(name = "companyInfo", value = "公司新的信息")
    public ResponseVO updateProInfo(@NotNull @RequestBody ProductionInfo proInfo) {
        boolean checked = proInfo.getId() == null;
        //接收的参数是否缺少
        if (checked) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        return proInfoService.updateProInfo(proInfo) ?
                ResponseVO.success(MessageEnum.ACTION_SUCCESS) : ResponseVO.success(MessageEnum.UPDATE_ERROR);
    }
}

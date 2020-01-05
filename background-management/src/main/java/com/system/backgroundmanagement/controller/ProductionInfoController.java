package com.system.backgroundmanagement.controller;


import com.system.backgroundmanagement.common.MessageEnum;
import com.system.backgroundmanagement.common.ParamCheckUtils;
import com.system.backgroundmanagement.common.ReturnVO;
import com.system.backgroundmanagement.common.VO;
import com.system.backgroundmanagement.entity.ProductionInfo;
import com.system.backgroundmanagement.service.IProductionInfoService;
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
     * @param proInfo
     * @return
     */
    @PostMapping("add")
    public ReturnVO saveProInfo(@NotNull @RequestBody ProductionInfo proInfo) {
        //校验新增的留言消息的非空参数是否符合
        boolean checked = proInfo.getTitle() == null || proInfo.getRank() == null;
        if (checked) {
            return ReturnVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        AtomicBoolean saveResult = new AtomicBoolean(false);
        try {
            saveResult.set(proInfoService.save(proInfo));
        } catch (Exception e) {
            log.warn("产品信息添加异常,{}", proInfo, e.getCause());
        }
        return saveResult.get() ? ReturnVO.success(MessageEnum.ADD_SUCCESS) : ReturnVO.success(MessageEnum.ADD_ERROR);
    }

    @DeleteMapping("del/{ids}")
    public ReturnVO delProInfo(@PathVariable String ids) {
        List<Long> idList = new ArrayList<>();
        ReturnVO checkResult = ParamCheckUtils.checkBatchIds(ids, idList);
        if (checkResult != null) {
            return checkResult;
        }
        //根据id批量删除
        return proInfoService.deleteByIds(idList) ?
                ReturnVO.success(MessageEnum.DELETE_SUCCESS) : ReturnVO.success(MessageEnum.DELETE_ERROR);
    }

    /**
     * 根据title，recommend，top筛选分页(非必须)
     * 产品信息列表接口
     *
     * @param vo
     * @return
     */
    @GetMapping("list")
    public ReturnVO listProInfo(VO vo) {
        return proInfoService.listProInfo(vo);
    }

    /**
     * 修改产品信息接口
     *
     * @param proInfo
     * @return
     */
    @PutMapping("update")
    @ApiOperation("修改产品信息")
    @ApiImplicitParam(name = "companyInfo", value = "公司新的信息")
    public ReturnVO updateProInfo(@NotNull @RequestBody ProductionInfo proInfo) {
        boolean checked = proInfo.getId() == null;
        //接收的参数是否缺少
        if (checked) {
            return ReturnVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        return proInfoService.updateProInfo(proInfo) ?
                ReturnVO.success(MessageEnum.ACTION_SUCCESS) : ReturnVO.success(MessageEnum.UPDATE_ERROR);
    }
}

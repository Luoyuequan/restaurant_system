package com.system.backgroundmanagement.controller;


import com.system.backgroundmanagement.common.MessageEnum;
import com.system.backgroundmanagement.common.ParamCheckUtils;
import com.system.backgroundmanagement.common.RequestVO;
import com.system.backgroundmanagement.common.ResponseVO;
import com.system.backgroundmanagement.entity.CompanyInfo;
import com.system.backgroundmanagement.service.ICompanyInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 公司信息 前端控制器
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/company-info")
@Slf4j
public class CompanyInfoController {

    private final ICompanyInfoService companyInfoService;

    public CompanyInfoController(ICompanyInfoService companyInfoService) {
        this.companyInfoService = companyInfoService;
    }

    /**
     * 公司信息添加请求接口
     *
     * @param companyInfo 公司信息
     * @return vo
     */
    @PostMapping("add")
    @ApiOperation("公司信息添加请求接口")
    @ApiImplicitParam(name = "companyInfo", value = "公司信息", dataTypeClass = CompanyInfo.class, required = true)
    public ResponseVO save(@NotNull @RequestBody CompanyInfo companyInfo) {
        //校验新增的公司信息的非空参数是否符合
        ResponseVO responseVO = ParamCheckUtils.checkValues(companyInfo.getContent(), companyInfo.getTel());
        if (responseVO != null) {
            return responseVO;
        }
        AtomicBoolean saveResult = new AtomicBoolean(false);
        try {
            saveResult.set(companyInfoService.save(companyInfo));
        } catch (Exception e) {
            log.warn("公司信息添加异常,{}", companyInfo, e.getCause());
        }
        return saveResult.get() ? ResponseVO.success(MessageEnum.ADD_SUCCESS) : ResponseVO.success(MessageEnum.ADD_ERROR);
    }

    /**
     * 根据公司名字，id
     * 获取指定公司信息
     *
     * @return requestVo
     */
    @GetMapping("get")
    @ApiOperation("获取指定公司信息")
    public ResponseVO getCompanyInfo(@NotNull RequestVO requestVo) {
        //检查id，name是否非空
        ResponseVO responseVO = ParamCheckUtils.checkValues(requestVo.getId(), requestVo.getName());
        if (responseVO != null) {
            return responseVO;
        }
        return companyInfoService.getCompanyInfo(requestVo);
    }

    /**
     * 修改指定id的公司信息
     *
     * @param companyInfo 公司新的信息
     * @return vo
     */
    @PutMapping("update")
    @ApiOperation("修改公司信息")
    @ApiImplicitParam(name = "companyInfo", value = "公司新的信息")
    public ResponseVO updateCompanyInfo(@NotNull @RequestBody CompanyInfo companyInfo) {
        //检查公司信息id是否非空
        ResponseVO responseVO = ParamCheckUtils.checkValues(companyInfo.getId());
        if (responseVO != null) {
            return responseVO;
        }
        return companyInfoService.updateCompanyInfo(companyInfo) ?
                ResponseVO.success(MessageEnum.ACTION_SUCCESS) : ResponseVO.success(MessageEnum.UPDATE_ERROR);
    }

}

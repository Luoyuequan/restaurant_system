package com.system.backgroundmanagement.controller;


import com.system.backgroundmanagement.common.MessageEnum;
import com.system.backgroundmanagement.common.ReturnVO;
import com.system.backgroundmanagement.common.VO;
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
    public ReturnVO save(@NotNull @RequestBody CompanyInfo companyInfo) {
        //校验新增的留言消息的非空参数是否符合
        boolean checked = companyInfo.getContent() == null || companyInfo.getTel() == null;
        if (checked) {
            return ReturnVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        AtomicBoolean saveResult = new AtomicBoolean(false);
        try {
            saveResult.set(companyInfoService.save(companyInfo));
        } catch (Exception e) {
            log.warn("公司信息添加异常,{}", companyInfo, e.getCause());
        }
        return saveResult.get() ? ReturnVO.success(MessageEnum.ADD_SUCCESS) : ReturnVO.success(MessageEnum.ADD_ERROR);
    }

    /**
     * 根据公司名字或id
     * 获取指定公司信息
     *
     * @return vo
     */
    @GetMapping("get")
    @ApiOperation("获取指定公司信息")
    public ReturnVO getCompanyInfo(@NotNull VO vo) {
        boolean checked = vo.getId() == null && vo.getName() == null;
        if (checked) {
            return ReturnVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        return companyInfoService.getCompanyInfo(vo);
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
    public ReturnVO updateCompanyInfo(@NotNull @RequestBody CompanyInfo companyInfo) {
        boolean checked = companyInfo.getId() == null;
        //接收的参数是否缺少
        if (checked) {
            return ReturnVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        return companyInfoService.updateCompanyInfo(companyInfo) ?
                ReturnVO.success(MessageEnum.ACTION_SUCCESS) : ReturnVO.success(MessageEnum.UPDATE_ERROR);
    }

}

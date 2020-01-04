package com.system.backgroundmanagement.controller;


import com.system.backgroundmanagement.common.ReturnVO;
import com.system.backgroundmanagement.entity.ProductionInfo;
import com.system.backgroundmanagement.service.IProductionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class ProductionInfoController {

    @Autowired
    IProductionInfoService proInfoService;

    @PutMapping("add")
    public ReturnVO saveProInfo(@RequestBody ProductionInfo proInfo) {
        return proInfoService.saveProInfo(proInfo);
    }

    @DeleteMapping("del/{id}")
    public ReturnVO delProInfo(@PathVariable Long id) {
        return proInfoService.delProInfo(id);
    }

    @GetMapping("list")
    public ReturnVO listProInfo() {
        return proInfoService.listProInfo();
    }

    @PostMapping("update")
    public ReturnVO updateProInfo(@RequestBody ProductionInfo proInfo) {
        return proInfoService.updateProInfo(proInfo);
    }
}

package com.system.backgroundmanagement.controller;


import com.system.backgroundmanagement.common.IController;
import com.system.backgroundmanagement.common.ReturnVO;
import com.system.backgroundmanagement.common.VO;
import com.system.backgroundmanagement.entity.Column;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
public class ColumnController implements IController<Column> {

    @GetMapping("get")
    @Override
    public ReturnVO get(VO vo, HttpServletRequest request) {
        return null;
    }

    @GetMapping("list")
    @Override
    public ReturnVO list(VO vo, HttpServletRequest request) {
        return null;
    }

    @PutMapping("update")
    @Override
    public ReturnVO modify(Column data, HttpServletRequest request) {
        return null;
    }

    @PostMapping("add")
    @Override
    public ReturnVO save(Column data, HttpServletRequest request) {
        return null;
    }

    @DeleteMapping("del")
    @Override
    public ReturnVO delete(VO vo, HttpServletRequest request) {
        return null;
    }
}

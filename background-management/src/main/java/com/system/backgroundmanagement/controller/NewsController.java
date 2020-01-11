package com.system.backgroundmanagement.controller;


import com.system.backgroundmanagement.common.*;
import com.system.backgroundmanagement.entity.News;
import com.system.backgroundmanagement.service.INewsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 新闻动态 前端控制器
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@RestController
@RequestMapping("/news")
@Slf4j
public class NewsController {
    private final INewsService newsService;

    public NewsController(INewsService newsService) {
        this.newsService = newsService;
    }


    /**
     * 新闻动态添加接口
     *
     * @param newsInfo 新的新闻动态
     * @return 响应vo
     */
    @PostMapping("add")
    public ResponseVO saveNewsInfo(@RequestBody News newsInfo) {
        //校验新增的新闻动态的非空参数是否符合
        boolean checked = newsInfo.getTitle() == null || newsInfo.getColumnId() == null ||
                newsInfo.getRankValue() == null || newsInfo.getSimpleInfo() == null || newsInfo.getDetailInfo() == null;
        if (checked) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        AtomicBoolean saveResult = new AtomicBoolean(false);
        try {
            saveResult.set(newsService.save(newsInfo));
        } catch (Exception e) {
            log.warn("新闻动态添加异常,{}", newsInfo, e.getCause());
        }
        return saveResult.get() ? ResponseVO.success(MessageEnum.ADD_SUCCESS) : ResponseVO.error(MessageEnum.ADD_ERROR);
    }


    /**
     * 删除指定id集合的新闻动态接口
     *
     * @param requestVo 请求参数(多个id由英文逗号拼接成字符串)
     * @return requestVo
     */
    @DeleteMapping("del")
    public ResponseVO delNewsInfo(RequestVO requestVo) {
        List<Long> idList = new ArrayList<>();
        ResponseVO checkResult = ParamCheckUtils.checkBatchIds(requestVo.getIds(), idList);
        if (checkResult != null) {
            return checkResult;
        }
        //根据id批量删除
        return newsService.deleteByIds(idList) ?
                ResponseVO.success(MessageEnum.DELETE_SUCCESS) : ResponseVO.error(MessageEnum.DELETE_ERROR);
    }

    /**
     * 根据 title，recommend，top 筛选分页(非必须)
     * 新闻动态列表接口
     *
     * @param pageVO    分页参数
     * @param requestVo 条件查询参数
     * @return 响应数据
     */
    @GetMapping("list")
    public ResponseVO listNewsInfo(PageVO pageVO, RequestVO requestVo) {
        return newsService.listNewsInfo(pageVO, requestVo);
    }

    /**
     * 获取指定id新闻动态
     *
     * @param requestVo 请求参数(id)
     * @return requestVo
     */
    @GetMapping("get")
    public ResponseVO getNewsInfo(RequestVO requestVo) {
        Long id = requestVo.getId();
        if (id == null) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        News news = newsService.getNewsInfo(id);
        if (news == null) {
            return ResponseVO.success(MessageEnum.DATA_NO);
        }
        return ResponseVO.success(MessageEnum.FIND_SUCCESS, news);
    }

    /**
     * 修改新闻动态接口
     *
     * @param newsInfo 待修改新闻动态
     * @return vo
     */
    @PutMapping("update")
    @ApiOperation("修改新闻动态")
    @ApiImplicitParam(name = "newsInfo", value = "待修改的新闻动态")
    public ResponseVO updateNewsInfo(@RequestBody News newsInfo) {
        boolean checked = newsInfo.getId() == null;
        //接收的参数是否缺少
        if (checked) {
            return ResponseVO.error(MessageEnum.VARIABLE_MISS_ERROR);
        }
        return newsService.updateNewsInfo(newsInfo) ?
                ResponseVO.success(MessageEnum.ACTION_SUCCESS) : ResponseVO.error(MessageEnum.UPDATE_ERROR);
    }
}

package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.common.*;
import com.system.backgroundmanagement.dao.NewsDao;
import com.system.backgroundmanagement.entity.Column;
import com.system.backgroundmanagement.entity.News;
import com.system.backgroundmanagement.service.IColumnService;
import com.system.backgroundmanagement.service.INewsService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * 新闻动态 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
@Slf4j
public class NewsServiceImpl extends ServiceImpl<NewsDao, News> implements INewsService {
    @Autowired
    IColumnService columnService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(@NotNull List<Long> idList) {
        Long[] ids = idList.toArray(new Long[0]);
        AtomicBoolean removeResult = new AtomicBoolean(false);
        try {
            removeResult.set(this.removeByIds(idList));
            if (!removeResult.get()) {
                log.warn("批量删除失败,ids:{}", Arrays.toString(ids));
            }
        } catch (Exception e) {
            log.warn("批量删除异常,ids:{}", Arrays.toString(ids), e.getCause());
        }
        return removeResult.get();
    }

    @Override
    public ResponseVO listNewsInfo(PageVO pageVO, RequestVO requestVo) {
        try {
            QueryWrapper<News> newsQuery = new QueryWrapper<>();
            Map<String, Object> voMap = MapUtils.convertMap(requestVo);
            //非null的作为查询参数
            newsQuery.allEq(voMap, false);
            IPage<News> newsPage = new Page<>();
            newsPage.setCurrent(pageVO.getPage()).setSize(pageVO.getSize());
            //条件查询+分页
            IPage<News> newsPageList = page(newsPage, newsQuery);
            List<News> newsList = newsPageList.getRecords();
            //无数据
            if (CollectionUtils.isEmpty(newsList)) {
                return ResponseVO.error(MessageEnum.DATA_NO);
            }
            //获取新闻动态所属栏目相关信息,代替多表查询
            for (News news : newsList) {
                Long columnId = news.getColumnId();
                Column columnInfo = columnService.getColumnInfoById(columnId);
                news.setColumn(columnInfo);
            }
            return ResponseVO.success(MessageEnum.FIND_SUCCESS, newsPageList);
        } catch (Exception e) {
            log.warn("查询失败,requestVo:{}", requestVo, e.getCause());
            return ResponseVO.error(MessageEnum.FIND_ERROR);
        }
    }

    @Override
    public News getNewsInfo(Long id) {
        News newsInfo = getById(id);
        if (newsInfo != null) {
            AtomicReference<Long> clickNumber = new AtomicReference<>(newsInfo.getClickNumber());
            clickNumber.set(clickNumber.get() + 1);
            //新闻动态访问点击量+1
            newsInfo.setClickNumber(clickNumber.get());
            boolean updateResult = this.updateNewsInfo(newsInfo);
            if (!updateResult) {
                log.warn("访问量增加失败,newsInfo:{}", newsInfo);
            }
            //获取新闻动态所属栏目相关信息,代替多表查询
            Long columnId = newsInfo.getColumnId();
            Column columnInfo = columnService.getColumnInfoById(columnId);
            newsInfo.setColumn(columnInfo);
        }
        return newsInfo;
    }

    @Override
    public boolean updateNewsInfo(News newsInfo) {
        AtomicBoolean removeResult = new AtomicBoolean(false);
        try {
            UpdateWrapper<News> updateWrapper = new UpdateWrapper<>();
            updateWrapper.ge("id", newsInfo.getId());
            removeResult.set(update(newsInfo, updateWrapper));
            if (!removeResult.get()) {
                log.warn("新闻动态修改失败,newsInfo:{}", newsInfo);
            }
        } catch (Exception e) {
            log.warn("新闻动态修改失败,newsInfo:{}", newsInfo, e.getCause());
        }
        return removeResult.get();
    }
}

package com.system.backgroundmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.system.backgroundmanagement.common.PageVO;
import com.system.backgroundmanagement.common.RequestVO;
import com.system.backgroundmanagement.common.ResponseVO;
import com.system.backgroundmanagement.entity.News;

import java.util.List;

/**
 * <p>
 * 新闻动态 服务类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
public interface INewsService extends IService<News> {

    boolean deleteByIds(List<Long> idList);

    ResponseVO listNewsInfo(PageVO pageVO, RequestVO requestVo);

    News getNewsInfo(Long id);

    boolean updateNewsInfo(News newsInfo);
}

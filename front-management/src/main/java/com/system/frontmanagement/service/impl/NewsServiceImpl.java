package com.system.frontmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.frontmanagement.dao.NewsDao;
import com.system.frontmanagement.entity.News;
import com.system.frontmanagement.service.INewsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 新闻动态 服务实现类
 * </p>
 *
 * @author luoyuequan
 * @since 2020-01-04
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsDao, News> implements INewsService {

}

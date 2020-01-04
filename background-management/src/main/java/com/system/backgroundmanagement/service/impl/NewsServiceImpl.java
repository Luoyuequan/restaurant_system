package com.system.backgroundmanagement.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.system.backgroundmanagement.dao.NewsDao;
import com.system.backgroundmanagement.entity.News;
import com.system.backgroundmanagement.service.INewsService;
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

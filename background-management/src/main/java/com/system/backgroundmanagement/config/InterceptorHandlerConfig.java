package com.system.backgroundmanagement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luoyuequan
 * @time 2020/1/9 15:30
 */
@Component
public class InterceptorHandlerConfig implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(InterceptorHandlerConfig.class);
    private ThreadLocal<Long> time = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        time.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long startTime = time.get();
        long endTime = System.currentTimeMillis();
        logger.info("uri:{},response time:{}ms", request.getRequestURI(), (endTime - startTime));
        time.remove();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

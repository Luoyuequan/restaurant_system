package com.system.backgroundmanagement.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author luoyuequan
 * @time 2020/1/9 15:08
 */
@Slf4j
@Component
@WebFilter(urlPatterns = {"/*"}, filterName = "GlobalFilter")
public class FilterConfig implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

}

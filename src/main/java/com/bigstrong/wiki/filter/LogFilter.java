//package com.bigstrong.wiki.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * @author BigStrong
// * @date 2022/01/01
// * @description 打印接口耗时过滤器
// */
//@Slf4j
//@Component
//public class LogFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        // 打印请求日志
//        HttpServletRequest request = (HttpServletRequest)servletRequest;
//        log.info("----------------------LogFilter开始----------------------");
//        log.info("请求地址：{} {}", request.getRequestURL(), request.getMethod());
//        log.info("远程地址：{}", request.getRemoteAddr());
//        long startTime = System.currentTimeMillis();
//        filterChain.doFilter(servletRequest, servletResponse);
//        log.info("----------------------LogFilter结束 耗时：{} ms ----------------------",
//                System.currentTimeMillis() - startTime);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}

//package com.bigstrong.wiki.interceptor;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author BigStrong
// * @date 2022/01/01
// * @description 统计接口耗时拦截器，常用于登录，API拦截等校验
// */
//@Slf4j
//@Component
//public class LogInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 打印请求信息
//        log.info("----------------------LogInterceptor开始----------------------");
//        log.info("请求地址：{} {}", request.getRequestURL(), request.getMethod());
//        log.info("远程地址：{}", request.getRemoteAddr());
//
//        long startTime = System.currentTimeMillis();
//        request.setAttribute("requestStartTime", startTime);
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        long startTime = (Long) request.getAttribute("requestStartTime");
//        log.info("----------------------LogInterceptor结束 耗时：{} ms ----------------------", System.currentTimeMillis() - startTime);
//    }
//}

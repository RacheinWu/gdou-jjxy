package com.rachein.laoshixiangmu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/6
 * @Description
 */
@Configuration
public class x implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().contains("static/res/")) {
            System.out.println(request.getRequestURI().substring(1));
        }
        return true;
    }
}

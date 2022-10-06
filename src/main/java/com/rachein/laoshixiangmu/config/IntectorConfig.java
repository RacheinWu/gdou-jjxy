package com.rachein.laoshixiangmu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/6
 * @Description
 */
@Configuration
public class IntectorConfig implements WebMvcConfigurer {

    @Autowired
    private x x;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(x);
    }
}

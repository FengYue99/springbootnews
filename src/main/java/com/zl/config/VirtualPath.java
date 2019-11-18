package com.zl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author fy
 */
@Configuration
public class VirtualPath extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:D:/img/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        /**
         * 调用父类方法
         */
        super.addResourceHandlers(registry);
    }
}

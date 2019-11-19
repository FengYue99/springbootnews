package com.zl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author fy
 */
@Configuration
public class VirtualPath extends WebMvcConfigurationSupport {
    /**
     * 通过java类配置图片上传视图解析器
     */
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver cmr=new CommonsMultipartResolver();
        cmr.setDefaultEncoding("utf-8");
        cmr.setMaxUploadSize(100);
        cmr.setResolveLazily(true);
        return cmr;
    }

    /**
     * 配置文件类
     */
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

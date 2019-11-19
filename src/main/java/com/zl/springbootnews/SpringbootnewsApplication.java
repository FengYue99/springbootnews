package com.zl.springbootnews;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author fy
 */
@SpringBootApplication
@ComponentScan("com.zl")
//告诉springboot项目dao层接口所在的包
@MapperScan("com.zl.dao")
//指定servletApi所在的位置
@ServletComponentScan("com.zl.filter")
//开启事务注解开发功能
@EnableTransactionManagement
//开启session共享功能
@EnableRedisHttpSession
//开启任务调度
@EnableScheduling

public class SpringbootnewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootnewsApplication.class, args);
    }

}

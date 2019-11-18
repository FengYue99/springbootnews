package com.zl.springbootnews;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author fy
 */
@SpringBootApplication
@ComponentScan("com.zl")
@MapperScan("com.zl.dao")
@ServletComponentScan("com.zl.filter")
public class SpringbootnewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootnewsApplication.class, args);
    }

}

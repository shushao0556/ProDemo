package com.evshou.admin.firstspringboot.controller;

import com.evshou.admin.firstspringboot.entity.User;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration  //代表当前类是一个配置类
public class UserConfig {
    @Bean       // 构建一个实例，放到spring容器
    public User user(){
        return new User(1,"admin");
    }

}

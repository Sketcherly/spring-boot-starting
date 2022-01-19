package com.xiaozu.server.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xiaozu.server.repository")
public class MybatisConfiguration {

}

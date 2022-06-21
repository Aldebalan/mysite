package com.douzone.mysite.config.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.app.DBConfig;
import com.douzone.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.douzone.mysite.aspect", "com.douzone.mysite.service", "com.douzone.mysite.repository"})
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {
}

package com.zdn.EasyCarServer;

import com.zdn.EasyCarServer.config.ApiSecurityFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ApiSecurityFilter.class})})
@MapperScan(basePackages = {"com.zdn.EasyCarServer.mapper", "com.zdn.EasyCarServer.dao"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

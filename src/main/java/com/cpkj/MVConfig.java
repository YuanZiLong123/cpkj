package com.cpkj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cpkj.filter.LoginFilter;

@SpringBootConfiguration
public class MVConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private LoginFilter loginFilter;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(loginFilter).addPathPatterns("/**").excludePathPatterns("/user/**");
	}
	
}

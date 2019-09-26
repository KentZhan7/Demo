package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.demo.interceptors.Interceptor1;

/**
 * <h1>註冊攔截器</h1>
 * 
 * @author KentZhan
 */
@Component
public class InterceptorRegister extends WebMvcConfigurerAdapter {
	@Autowired
	Interceptor1 interceptor1;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(interceptor1).addPathPatterns("/**");
	}
}

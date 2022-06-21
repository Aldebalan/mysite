package com.douzone.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.mysite.security.AuthInterceptor;
import com.douzone.mysite.security.AuthUserHandlerMethodArgumentResolver;
import com.douzone.mysite.security.LoginInterceptor;
import com.douzone.mysite.security.LogoutInterceptor;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {
	
	// Argument Resolver
	@Bean
	public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(handlerMethodArgumentResolver());
	}

	// Security Interceptors
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}
	
	@Bean
	public HandlerInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(loginInterceptor())
			.addPathPatterns("/user/auth");
		
		registry
			.addInterceptor(logoutInterceptor())
			.addPathPatterns("/user/logout");
		
		registry
			.addInterceptor(authInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/assets/**")
			.excludePathPatterns("/user/auth")
			.excludePathPatterns("/user/logout");
	}
	
	
}

package com.operation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:C:/003Operation/uploads/");
		registry.addResourceHandler("/profileImgs/**").addResourceLocations("file:C:/003Operation/profileImgs/");
	}
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/kiosk/**").allowedOrigins("https://pushssun.github.io").allowCredentials(true);
//	}
}

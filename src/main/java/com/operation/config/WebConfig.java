package com.operation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:C:/003Operation/");
		//registry.addResourceHandler("/profileImgs/**").addResourceLocations("file:C:/003Operation/profileImgs");
		registry.addResourceHandler("/profileImgs/**").addResourceLocations("file:C:/profileImgs/");
	}
}

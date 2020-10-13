package com.nw.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"};
	
	/*
	 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 * 
	 * registry.addResourceHandler( "/webjars/**") .addResourceLocations(
	 * "classpath:/META-INF/resources/webjars/"); }
	 */

}

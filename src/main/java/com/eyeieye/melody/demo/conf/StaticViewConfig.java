package com.eyeieye.melody.demo.conf;

import com.eyeieye.melos.MelosMvcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticViewConfig extends MelosMvcConfig  {

	@Autowired
	private WebMvcProperties properties;
	@Autowired
	private ResourceProperties resourceProperties;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler(properties.getStaticPathPattern()).addResourceLocations(resourceProperties.getStaticLocations());
	}
}
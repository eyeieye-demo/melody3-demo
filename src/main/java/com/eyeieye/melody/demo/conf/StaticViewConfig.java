package com.eyeieye.melody.demo.conf;

import com.eyeieye.melos.MelosMvcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class StaticViewConfig extends MelosMvcConfig  {

	@Autowired
	private WebMvcProperties properties;
	@Autowired
	private ResourceProperties resourceProperties;

	/**
	 * 继承WebMvcConfigurationSupport会覆盖默认配置，此处重新配置静态资源访问
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler(properties.getStaticPathPattern())
				.addResourceLocations(resourceProperties.getStaticLocations());
	}
}
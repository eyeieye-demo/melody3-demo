package com.eyeieye.melody.demo.conf;

import com.eyeieye.melody.demo.controller.UserArgumentResolver;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 注意：若通过继承WebMvcConfigurationSupport方式实现配置，可能会覆盖默认配置，导致LCN分布式事务失效。
 */
@Configuration
public class ResolverConfiguration implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserArgumentResolver());
    }
}

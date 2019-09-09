package com.eyeieye.melody.demo;

import com.eyeieye.melos.MelosConfig;
import com.eyeieye.melos.MelosMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication()
@ComponentScan(basePackages = { "com.eyeieye.melos.profiler","com.eyeieye.melos.rpc","com.eyeieye.melos.util","com.eyeieye.melos.web","com.eyeieye.melody.demo"},
basePackageClasses = MelosConfig.class
)
@ImportResource(locations = { "classpath:service-beans.xml" })
public class DemoBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBootApplication.class, args);
	}
}

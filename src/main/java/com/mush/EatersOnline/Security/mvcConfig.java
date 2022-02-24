package com.mush.EatersOnline.Security;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class mvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path imageUploadDir = Paths.get("./food-images");
		String imageUploadPath = imageUploadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/food-images/**").addResourceLocations("file:/" + imageUploadPath +"/");
	}

}

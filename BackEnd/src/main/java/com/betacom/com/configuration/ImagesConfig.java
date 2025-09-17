package com.betacom.com.configuration;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImagesConfig implements WebMvcConfigurer {
	 
	@Value("${app.upload.dir}")
	    private String uploadDir;
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") 
                .addResourceLocations("file:" + Path.of(uploadDir).toAbsolutePath() + "/");
    }

}

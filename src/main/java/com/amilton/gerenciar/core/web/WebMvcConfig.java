package com.amilton.gerenciar.core.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("pages/overview");
        registry.addViewController("/login").setViewName("pages/login");
        registry.addViewController("/totalizador").setViewName("pages/totalizador");
    }
}

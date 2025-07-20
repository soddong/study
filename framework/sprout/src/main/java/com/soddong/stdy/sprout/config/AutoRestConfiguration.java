package com.soddong.stdy.sprout.config;

import com.soddong.stdy.sprout.core.AutoArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
class AutoRestConfiguration implements WebMvcConfigurer {

    @Bean
    public AutoArgumentResolver autoArgumentResolver() {
        return new AutoArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(autoArgumentResolver());
        System.out.println("[AutoRestConfiguration] AutoArgumentResolver registered");
    }
}
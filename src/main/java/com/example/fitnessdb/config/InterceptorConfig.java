package com.example.fitnessdb.config;


import com.example.fitnessdb.web.interceptor.PageInterceptor;
import com.example.fitnessdb.web.interceptor.StatsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final StatsInterceptor logInterceptor;
    private final PageInterceptor pageInterceptor;


    public InterceptorConfig(StatsInterceptor logInterceptor, PageInterceptor pageInterceptor) {
        this.logInterceptor = logInterceptor;
        this.pageInterceptor = pageInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.logInterceptor);
        registry.addInterceptor(this.pageInterceptor);
    }
}


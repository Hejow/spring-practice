package com.cos.jwt.config;

import com.cos.jwt.filter.Myfilter1;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Myfilter1> filter1() {
        FilterRegistrationBean<Myfilter1> bean = new FilterRegistrationBean<>(new Myfilter1());
        bean.addUrlPatterns("/*");
        bean.setOrder(0); // 낮은 번호가 높은 우선순위

        return bean;
    }
}

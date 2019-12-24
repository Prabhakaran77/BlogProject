package com.blog.config;

import com.blog.dao.operation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan({"com.blog"})
public class ConfigurationClass {

    @Bean
    public InternalResourceViewResolver viewResolver () {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");

        return resolver;

    }
    @Bean
    public operation oper(){return new operation();}
}

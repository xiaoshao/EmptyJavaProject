package com.configuration;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
@PropertySource("classpath:application.properties")
public class RootConfiguation {

    @Bean
    public PropertySourcesPlaceholderConfigurer initResolver(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}

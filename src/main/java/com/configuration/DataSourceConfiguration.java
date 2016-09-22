package com.configuration;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DataSourceConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource initDataSource(){
        DataSource dataSource = new DataSource();
        dataSource.setUsername(environment.getProperty("database.username"));
        dataSource.setPassword(environment.getProperty("database.password"));
        dataSource.setDriverClassName(environment.getProperty("database.driver"));
        dataSource.setUrl(environment.getProperty("database.url"));
        return dataSource;
    }
}

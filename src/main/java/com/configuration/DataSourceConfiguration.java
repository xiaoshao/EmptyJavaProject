package com.configuration;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DataSourceConfiguration {

    @Autowired
    private Environment environment;
//
//    @Bean(name="dataSource")
//    public DataSource initDataSource(){
//        DataSource dataSource = new DataSource();
//        dataSource.setUsername(environment.getProperty("database.username"));
//        dataSource.setPassword(environment.getProperty("database.password"));
//        dataSource.setDriverClassName(environment.getProperty("database.driver"));
//        dataSource.setUrl(environment.getProperty("database.url"));
//        return dataSource;
//    }

    @Bean(name="dataSource")
    public DriverManagerDataSource initTestDataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUsername(environment.getProperty("database.username"));
        driverManagerDataSource.setPassword(environment.getProperty("database.password"));
        driverManagerDataSource.setDriverClassName(environment.getProperty("database.driver"));
        driverManagerDataSource.setUrl(environment.getProperty("database.url"));
//        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/purely");
//        driverManagerDataSource.setCatalog("purely");
//        driverManagerDataSource.setSchema("purely");
        return driverManagerDataSource;
    }

    @Bean
    @Autowired
    public JdbcTemplate initJDBCTemplate(DriverManagerDataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}

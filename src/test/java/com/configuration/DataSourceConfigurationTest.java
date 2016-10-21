package com.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfiguration.class)
})
@TestPropertySource(locations = "classpath:application.properties")
public class DataSourceConfigurationTest {
    @Autowired
    private DriverManagerDataSource dataSource;

    @Test
    public void configDataSourceCorrectly() {
        assertThat(dataSource.getUsername(), is("root"));
        assertThat(dataSource.getPassword(), is(""));
//        assertThat(dataSource.getUrl(), is("jdbc:mysql://localhost:3306/purely"));
//        assertThat(dataSource., is("org.mariadb.jdbc.Driver"));
    }
}

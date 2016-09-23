package com.db;

import com.bean.User;
import com.configuration.ApplicationConfiguration;
import com.configuration.DataSourceConfiguration;
import com.configuration.RootConfiguation;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = DataSourceConfiguration.class),
        @ContextConfiguration(classes = RootConfiguation.class),
        @ContextConfiguration(classes = ApplicationConfiguration.class)
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
})
@TestPropertySource(locations = "classpath:application.properties")
@DbUnitConfiguration
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DataSource datasource;

    @Test
    public void shouldSaveTheUserToDataBaseCorrectly() throws Exception {
        userDao.save();
    }

    @Test
    @DatabaseSetup(value="user_data.xml")
    public void shouldGetTheUserById() throws Exception {
        assertThat(userDao.queryById(1).get(), samePropertyValuesAs(new User(1, "xiaoshao")));
    }
}

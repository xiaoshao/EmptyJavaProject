package com.db;

import com.bean.Visitor;
import com.configuration.ApplicationConfiguration;
import com.configuration.DataSourceConfiguration;
import com.configuration.RootConfiguration;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = DataSourceConfiguration.class),
        @ContextConfiguration(classes = RootConfiguration.class),
        @ContextConfiguration(classes = ApplicationConfiguration.class)
})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
})
@TestPropertySource(locations = "classpath:application.properties")
public class VisitorDaoTest {

    @Autowired
    private VisitorDao userDao;

    @Test
    public void shouldSaveTheUserToDataBaseCorrectly() throws Exception {
        userDao.save();
    }

    @Test
    @DatabaseSetup(value = {"user_data.xml"}, connection= "dataSource")
    public void shouldGetTheUserById() throws Exception {
        assertThat(userDao.queryById(2).get(), samePropertyValuesAs(new Visitor(2, "DBUnit 2", "xiaoshao", "ROLE_ADMIN")));
    }

    @Test
    @DatabaseSetup({"user_data.xml"})
    public void shouldGetTheVisitorByName(){
        assertThat(userDao.getUserByName("xiaoshao").get(), samePropertyValuesAs(new Visitor(1, "xiaoshao", "xiaoshao", "ROLE_ADMIN")));
    }
}

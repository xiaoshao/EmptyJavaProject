package com.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringRunner.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class
})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void shouldSaveTheUserToDataBaseCorrectly() throws Exception {
        userDao.save();
    }
}

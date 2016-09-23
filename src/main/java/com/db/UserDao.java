package com.db;

import com.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save() {
        this.jdbcTemplate.update("insert into USER(id, name) values(?, ?)", "10", "xiaoshao");
    }

    public Optional<User> queryById(int id) {
        List<User> users = this.jdbcTemplate.query("select id, name from USER where id=?",
                new Object[]{id},
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        return new User(id, name);
                    }
                });

        return users.size() > 0 ? Optional.of(users.get(0)) : Optional.empty();
    }


}

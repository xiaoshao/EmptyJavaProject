package com.db;

import com.bean.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class VisitorDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public VisitorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save() {
        this.jdbcTemplate.update("insert into USER(id, username, password, role) values(?, ?, ?, ?)", "10", "addedUser", "password", "ROLE_ADMIN");

        List<Map<String, Object>> results  = jdbcTemplate.queryForList("show databases");

        for(Map<String, Object> result: results){
            for(HashMap.Entry<String, Object> entry : result.entrySet()){
                System.out.println("key:" + entry.getKey());
                System.out.println("value:" + entry.getValue());
            }
        }

//        jdbcTemplate.query("show databases");
    }

    public Optional<Visitor> queryById(int id) {
        List<Visitor> users = this.jdbcTemplate.query("select id, username, password, role from USER where id=?",
                new Object[]{id},
                new UserRowMapper());

        return users.size() > 0 ? Optional.of(users.get(0)) : Optional.empty();
    }

    public Optional<Visitor> getUserByName(String username) {
        List<Visitor> users = this.jdbcTemplate.query("select id, username, password, role from USER where username=?",
                new Object[]{username},
                new UserRowMapper());

        return users.size() > 0 ? Optional.of(users.get(0)) : Optional.empty();
    }

    private class UserRowMapper implements RowMapper<Visitor> {

        @Override
        public Visitor mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("username");
            String role = rs.getString("role");
            String password = rs.getString("password");
            return new Visitor(id, name, password, role);
        }
    }
}

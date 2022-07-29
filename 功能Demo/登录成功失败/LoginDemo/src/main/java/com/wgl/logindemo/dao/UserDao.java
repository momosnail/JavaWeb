package com.wgl.logindemo.dao;

import com.wgl.logindemo.domain.User;
import com.wgl.logindemo.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    private JdbcTemplate mTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    public User login(User loginUser) {
        try {
            // 1.sql语句
            String sql = "select * from user_info where username = ? and password = ?";

            // 2.执行sql语句
            User user = mTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace(); //这里可以保存日志
            return null;
        }
    }
}

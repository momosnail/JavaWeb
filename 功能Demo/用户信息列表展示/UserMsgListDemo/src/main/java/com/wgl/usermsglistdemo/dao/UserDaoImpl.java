package com.wgl.usermsglistdemo.dao;

import com.wgl.usermsglistdemo.domain.User;
import com.wgl.usermsglistdemo.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate mTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        try {
            // 1.定义sql
            String sql = "select * from user";

            // 2.查询
            List<User> users = mTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));

            return users;
        } catch (DataAccessException e) {
            e.printStackTrace(); //保存日志
            return null;
        }
    }
}

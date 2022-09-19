package com.futurus.usermsgdemo.dao.impl;

import com.futurus.usermsgdemo.dao.UserDao;
import com.futurus.usermsgdemo.domain.SystemUser;
import com.futurus.usermsgdemo.domain.User;
import com.futurus.usermsgdemo.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate mTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        try {
            // 使用JDBC创建数据库
            // 1.定义sql
            String sql = "select * from user";
            List<User> users = mTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));

            return users;
        } catch (DataAccessException e) {
            e.printStackTrace(); //保存日志
            return null;
        }
    }

    @Override
    public SystemUser findUserByUsernameandPassword(String username, String password) {
        try {
            // 1.定义sql
            String sql = "select * from system_user where username = ? and password = ?";

            // 2.执行sql
            SystemUser user = mTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(SystemUser.class), username, password);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace(); // 保存日志
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        // 1.定义sql
        String sql = "insert into user values(null,?,?,?,?,?,?)";
        // 2.执行sql
        mTemplate.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void delete(int id) {
        // 1.定义sql
        String sql = "delete from user where id = ?";
        // 2.执行sql
        mTemplate.update(sql, id);
    }

    @Override
    public User findById(int id) {
        // 1.定义sql
        String sql = "select * from user where id = ?";
        // 2.执行sql
        return mTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    @Override
    public void update(User user) {
        // 1.定义sql
        String sql = "update user set name = ?,gender = ? ,age = ? , address = ?, qq = ?, email = ? where id = ?";
        // 2.执行sql
        mTemplate.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        // 1.定义模板sql where 1=1 是为了让后面的条件格式统一
        String sql = "select count(*) from user where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        // 2.遍历map
        Set<String> keySet = condition.keySet();
        // 定义参数的集合
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            // 获取value 返回的是数组，因为我们的查询参数不是数组，所以取第一个就是。
            String value = condition.get(key)[0];
            // 判断value是否有值
            if (value != null && !"".equals(value)) {
                // 排除分页条件参数
                if ("currentPage".equals(key) || "rows".equals(key)) {
                    continue;
                }
                // 有值
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%"); //?条件的值
            }
        }

        System.out.println(sb.toString());
        System.out.println(params);
        return mTemplate.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        // 1.定义sql
        String sql = "select * from user where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        // 2.遍历map
        Set<String> keySet = condition.keySet();
        // 定义参数的集合
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            // 获取value 返回的是数组，因为我们的查询参数不是数组，所以取第一个就是。
            String value = condition.get(key)[0];
            // 判断value是否有值
            if (value != null && !"".equals(value)) {
                // 排除分页条件参数
                if ("currentPage".equals(key) || "rows".equals(key)) {
                    continue;
                }
                // 有值
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%"); //?条件的值
            }
        }

        // 添加分页查询
        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);

        sql = sb.toString();

        return mTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
    }
}

package com.futurus.usermsgdemo.dao;

import com.futurus.usermsgdemo.domain.SystemUser;
import com.futurus.usermsgdemo.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的DAO
 */
public interface UserDao {

    public List<User> findAll();
    public SystemUser findUserByUsernameandPassword(String  username, String password);
    public void addUser(User user);
    void delete(int parseInt);

    User findById(int parseInt);

    void update(User user);

    /**
     * 查询总记录数
     * @return
     * @param condition
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}

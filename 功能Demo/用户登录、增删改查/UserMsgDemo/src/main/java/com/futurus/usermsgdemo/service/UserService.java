package com.futurus.usermsgdemo.service;

import com.futurus.usermsgdemo.domain.PageBean;
import com.futurus.usermsgdemo.domain.SystemUser;
import com.futurus.usermsgdemo.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {

    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<User> findAll();

    /**
     * 用户登录
     * @param user
     * @return
     */
    public SystemUser login(SystemUser user);

    /**
     * 保存User
     * @param user
     */
    public void addUser(User user);

    /**
     * 根据id删除User
     * @param id
     */
    void deleteUser(String id);

    /**
     * 根据id查找对应的user对象
     * @param id
     * @return
     */
    User findUserById(String id);

    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 批量删除用户
     * @param ids
     */
    void delSelectedUser(String[] ids);

    /**
     * 分页、条件查询
     * @param currentPage 当前页
     * @param rows 条目
     * @param condition 条件查询条件
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);


}

package com.wgl.logindemo.test;

import com.wgl.logindemo.dao.UserDao;
import com.wgl.logindemo.domain.User;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void loginTest() {
        // 1.模拟登录对象
        User loginUser = new User();
        loginUser.setUsername("lisi");
        loginUser.setPassword("111");

        // 2.执行登录
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);

        // 3.打印返回对象
        System.out.println(user);
    }
}

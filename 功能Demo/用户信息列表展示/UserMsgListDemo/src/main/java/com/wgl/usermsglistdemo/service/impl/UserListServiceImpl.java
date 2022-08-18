package com.wgl.usermsglistdemo.service.impl;

import com.wgl.usermsglistdemo.dao.UserDao;
import com.wgl.usermsglistdemo.dao.UserDaoImpl;
import com.wgl.usermsglistdemo.domain.User;
import com.wgl.usermsglistdemo.service.UserListService;

import java.util.List;

public class UserListServiceImpl implements UserListService {
    @Override
    public List<User> findAll() {
        UserDao dao = new UserDaoImpl();
        return dao.findAll();
    }
}

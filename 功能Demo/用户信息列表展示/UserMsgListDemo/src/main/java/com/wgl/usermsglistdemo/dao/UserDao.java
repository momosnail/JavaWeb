package com.wgl.usermsglistdemo.dao;

import com.wgl.usermsglistdemo.domain.User;

import java.util.List;

public interface UserDao {

    public List<User> findAll();
}

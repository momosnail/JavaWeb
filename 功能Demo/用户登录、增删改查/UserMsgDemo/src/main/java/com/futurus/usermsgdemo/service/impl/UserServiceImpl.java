package com.futurus.usermsgdemo.service.impl;

import com.futurus.usermsgdemo.dao.UserDao;
import com.futurus.usermsgdemo.dao.impl.UserDaoImpl;
import com.futurus.usermsgdemo.domain.PageBean;
import com.futurus.usermsgdemo.domain.SystemUser;
import com.futurus.usermsgdemo.domain.User;
import com.futurus.usermsgdemo.service.UserService;

import java.util.List;
import java.util.Map;

import static java.lang.Integer.*;

public class UserServiceImpl implements UserService {
    private UserDao mDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        // 调用Dao完成查询
        return mDao.findAll();
    }

    @Override
    public SystemUser login(SystemUser systemUser) {
        return mDao.findUserByUsernameandPassword(systemUser.getUsername(), systemUser.getPassword());
    }

    @Override
    public void addUser(User user) {
        mDao.addUser(user);
    }

    @Override
    public void deleteUser(String id) {
        mDao.delete(parseInt(id));
    }

    @Override
    public User findUserById(String id) {
        return mDao.findById(parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        mDao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        // 1.遍历数组
        for (String id : ids) {
            // 2.调用dao删除
            mDao.delete(parseInt(id));
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = parseInt(_rows);

        // 1.创建空的PageBean
        PageBean<User> pb = new PageBean<>();

        // 2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        // 3.调用dao查询总记录数
        int totalCount = mDao.findTotalCount(condition);
        pb.setTotalCount(totalCount);

        // 4.调用dao查询list集合
        // 计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> list = mDao.findByPage(start, rows,condition);
        pb.setList(list);

        // 5.计算总页码
        int totalPage = totalCount % rows == 0 ? totalCount / rows : (totalCount / rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

}

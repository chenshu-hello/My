package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;

/**
 * @title: UserServiceImpl
 * @Author ChenShu
 * @Date: 2021/6/2 10:59
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();
    @Override
    public void registUser(User user) {
           userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if (userDao.queryUserByUsername(username)==null){
            return false;
        }
        return true;
    }
}

package com.atguigu.test;

import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;


/**
 * @title: UserDaoTest
 * @Author ChenShu
 * @Date: 2021/6/2 10:40
 * @Version 1.0
 */
public class UserDaoTest {

    UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername(){
        if (userDao.queryUserByUsername("admin1234")==null){
            System.out.println("用户名可用");
        }else {
            System.out.println("用户名已存在");
        }
    }
    @Test
    public  void queryUsernameAndPassword(){
        if (userDao.queryUserByUsernameAndPassword("admin","admin1234")==null){
            System.out.println("用户名密码错误，登陆失败");
        }else {
            System.out.println("登陆成功");
        }
    }
    @Test
    public  void saveUser(){
        System.out.println(userDao.saveUser(new User(null,"wzg168","123456","wzg168@qq.com")));
    }



}

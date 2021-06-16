package com.atguigu.service;

import com.atguigu.pojo.User;

public interface UserService {
    //注册用户

    public void registUser(User user);
    /**
     *
     * @param user
     * @return 如果返回null，说明登陆失败，返回有值，是登陆成功
     *
     */
    public User login(User user);
    /**
     *
     * @param username
     * @return
     *
     */
    public boolean existUsername(String username);

}

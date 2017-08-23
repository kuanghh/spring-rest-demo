package com.khh.service;

import com.khh.entity.User;

import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/8/22.
 */
public interface UserService {

    List<User> findAllUser();

    int saveUser(User user);
}

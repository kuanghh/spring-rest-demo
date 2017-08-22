package com.khh.service;

import com.khh.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/8/22.
 */
@Service("userService")
public class UserServiceImpl implements UserService {


    public List<User> findAllUser() {

        List<User> userList = new ArrayList<User>();
        userList.add(new User(1,"user1",new Date()));
        userList.add(new User(2,"user2",new Date()));

        return userList;
    }
}

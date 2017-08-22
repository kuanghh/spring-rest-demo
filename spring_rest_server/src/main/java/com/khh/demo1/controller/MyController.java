package com.khh.demo1.controller;

import com.khh.service.UserService;
import com.khh.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/8/22.
 *
 * 发布一个简单的Rest
 *
 */
@Controller
@RequestMapping("/simple")
public class MyController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findAllUser" , method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

}

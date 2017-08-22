package com.khh.demo2.controller;

import com.khh.entity.User;
import com.khh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.Date;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/8/22.
 */
@Controller
@RequestMapping("/demo2")
public class Demo2Controller {

    @Autowired
    private UserService userService;

    /**
     * 由于配置了contentNegotiationConfigurer.defaultContentType(MediaType.APPLICATION_JSON)，那么返回的默认类型json
     *
     *  即：
     *      访问localhost:8080/spring_rest_server/demo2/findAllUser.json,将会返回json格式的数据
     *      访问localhost:8080/spring_rest_server/demo2/findAllUser.xml,将会返回xml格式的数据
     *
     *  如果，访问localhost:8080/spring_rest_server/demo2/findAllUser.action,将会返回xml格式的数据,
     *      因为请求头中的ACCEPT值为：text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng.....
     */
    @RequestMapping(value = "/findAllUser" , method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @RequestMapping(value = "/find" , method = RequestMethod.GET)
    @ResponseBody
    public User find(){
        return new User(3,"中文",new Date());
    }
}

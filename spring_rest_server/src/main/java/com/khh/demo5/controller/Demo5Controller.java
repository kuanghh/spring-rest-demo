package com.khh.demo5.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.khh.entity.User;
import com.khh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 951087952@qq.com on 2017/8/23.
 *
 * 专门为spring_rest_client服务的controller
 *
 */
@RestController
@RequestMapping("/demo5")
public class Demo5Controller {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/putUser/{user}",method = RequestMethod.PUT)
    public ResponseEntity<User> putUser(@PathVariable("user") String userJson) throws Exception{
        System.out.println(userJson);
        JSONObject juser = JSONObject.parseObject(userJson);
        User user = JSONObject.toJavaObject(juser, User.class);
        userService.saveUser(user);
        ResponseEntity<User> entity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return entity;
    }

    /**
     * 直接加上@RequestBody，处理接收过来的json数据，并把它解析为user
     * 这里配合看 spring_rest_client 项目下的 com.khh.put.TestRestTemplateForPut.testPUT2方法
     *
     *
     * 其实 @RequestBody接收的是一个Json对象的字符串，而不是一个Json对象。然而在ajax请求往往传的都是Json对象，
     * 后来发现用 JSON.stringify(data)的方式就能将对象变成字符串。
     * 同时ajax请求的时候也要指定dataType: "json",contentType:"application/json" 这样就可以轻易的将一个对象或者List传到Java端，
     * 使用@RequestBody即可绑定对象或者List.
     *
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/putUser2",method = RequestMethod.PUT,consumes = "application/json")
    public ResponseEntity<User> putUser2(@RequestBody User user) throws Exception{
        userService.saveUser(user);
        ResponseEntity<User> entity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return entity;
    }
}

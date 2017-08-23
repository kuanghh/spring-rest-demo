package com.khh.demo4.controller;

import com.khh.entity.User;
import com.khh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;

/**
 * Created by 951087952@qq.com on 2017/8/23.
 *
 *  这个demo为了演示  在响应中设置头部信息
 */
@RestController
@RequestMapping("/demo4")
public class Demo4Controller {

    @Autowired
    private UserService userService;

    /**
     * 当返回ResponseEntity时，在响应中设置头部信息
     *
     * consumes = "application/json"的意思是：它会告诉spring这个方法只会处理对"/saveUser"的GET请求，并且请求的Content-Type
     * 头部信息为“application/json"
     *
     * @return
     */
//    @RequestMapping(value = "/saveUser",method = RequestMethod.GET,consumes = "application/json")
    @RequestMapping(value = "/saveUser",method = RequestMethod.GET)
    public ResponseEntity<User> saveUser(){

        User user  = new User(6,"user6",new Date());
        userService.saveUser(user);

        //开始设置头部信息
        HttpHeaders httpHeaders = new HttpHeaders();
        //配合302状态码，让客户端重定向到http://localhost:8080/spring_rest_server/demo3/find/{id}.xml
        URI locationUri = URI.create("http://localhost:8080/spring_rest_server/demo3/find/" + user.getId() + ".xml");
        httpHeaders.setLocation(locationUri);

        ResponseEntity<User> responseEntity = new ResponseEntity<User>(user,httpHeaders, HttpStatus.FOUND);
        return responseEntity;
    }

    /**
     * 使用UriComponentsBuilder组建 URI
     * @param ucb
     * @return
     */
    @RequestMapping(value = "/saveUser2",method = RequestMethod.GET)
    public ResponseEntity<User> saveUser2(UriComponentsBuilder ucb){

        User user  = new User(6,"user6",new Date());
        userService.saveUser(user);

        //开始设置头部信息
        HttpHeaders httpHeaders = new HttpHeaders();

        URI locationUri = ucb.path("/demo3/find/").path(String.valueOf(user.getId())).path(".xml").build().toUri();
//        URI locationUri = URI.create("http://localhost:8080/spring_rest_server/demo3/find/" + user.getId() + ".json");
        httpHeaders.setLocation(locationUri);

        ResponseEntity<User> responseEntity = new ResponseEntity<User>(user,httpHeaders, HttpStatus.FOUND);
        return responseEntity;
    }
}

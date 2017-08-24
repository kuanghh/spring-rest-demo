package com.khh.demo5.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.khh.entity.User;
import com.khh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 951087952@qq.com on 2017/8/23.
 *
 * 专门为spring_rest_client服务的controller
 *
 */
@RestController
//@Controller
@RequestMapping("/demo5")
public class Demo5Controller {
    @Autowired
    private UserService userService;

    /*******************************************PUT*******************************************************/
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
    /*******************************************PUT*******************************************************/

    /*******************************************Delete*******************************************************/
    @RequestMapping(value = "/deleteUser/{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("id") Integer id) throws Exception{

        int i = userService.deleteById(id);
        ResponseEntity entity = new ResponseEntity(id,HttpStatus.OK);
        return entity;
    }
    /*******************************************Delete*******************************************************/


    /**************************************POST***************************************************/
    /**
     * 这里注意：非常非常重要！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     *
     *
     * 在平常开发中，jquery中的.ajax  .post  .get方法提交数据到Controller的时候，如果不声明ContentType，
     *      那么将会以表单的形式提交到Controller，也就是，contentType = "application/x-www-form-urlencoded"（即使通过form提交，也是默认为该方式）
     *      然而，springmvc配置了<mvc:annotation-driven/>之后，会自动的把请求方式为"application/x-www-form-urlencoded"的请求参数
     *      绑定到Controller方法中对应的参数
     *
     * 在开发RESTFul中，当在Controller方法上用了注解@RequestMapping，并 指定了consumes属性后，那么当前方法只能处理consumes属性的请求方式
     *      比如：consumes = {"application/json"}，那么该方法就只能处理请求头中的Content-Type为application/json的请求，
     *      如果请求方式对应不上，会报415状态码
     *      然后在方法参数中，如果要绑定参数信息，那么可以用到 @RequestBody,@PathVariable等注解
     *      @RequestBody 作用是：通过请求的方式，找到应该对应的消息转换器，把传进来的消息类型（json,xml），转变成参数类型
     *                  其实 @RequestBody接收的是一个Json对象的字符串，而不是一个Json对象。然而在ajax请求往往传的都是Json对象，
     *                  后来发现用 JSON.stringify(data)的方式就能将对象变成字符串。
     *                  同时ajax请求的时候也要指定dataType: "json",contentType:"application/json" 这样就可以轻易的将一个对象或者List传到Java端，
     *                  使用@RequestBody即可绑定对象或者List.
     *      @PathVariable 作用是：在路径中，带有参数
     *      其次，所有参数对象不能有基本数据类型，不然会报400状态码
     *
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/postUser",method = RequestMethod.POST,consumes = {"application/json"})
    public ResponseEntity postUser(@RequestBody User user) throws Exception{
        userService.saveUser(user);
        ResponseEntity<User> entity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return entity;
    }

    @RequestMapping(value = "/postUser2",method = RequestMethod.POST)
    public ResponseEntity postUser2(User user) throws Exception{
        userService.saveUser(user);
        ResponseEntity<User> entity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return entity;
    }
    /****************************************POST*********************************************************/


    /****************************************Exchange*********************************************************/

    /**
     *  restTemplate.exchange()方法可以在发送请求的时候，设置请求头信息
     *
     *  注意一下：如果method = RequestMethod.GET，也就是提交的方法要GET的话，那么在客户端提交的请求，body里面放致任何东西都不对，
     *              因为GET请求的参数在URL的后面...形如http://../../*.action?id=1
     */
    @RequestMapping(value = "/exchange",method = RequestMethod.POST,consumes = {"application/json"})
    public ResponseEntity<User> exchange(@RequestBody  User user) throws Exception{
        userService.saveUser(user);
        ResponseEntity<User> entity = new ResponseEntity<>(user, HttpStatus.CREATED);
        return entity;
    }
    /****************************************Exchange*********************************************************/
}

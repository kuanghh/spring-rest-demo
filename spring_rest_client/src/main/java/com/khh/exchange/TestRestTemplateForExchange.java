package com.khh.exchange;

import com.alibaba.fastjson.JSONObject;
import com.khh.entity.User;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by 951087952@qq.com on 2017/8/23.
 *
 * 使用RestTemplate 创建REST的客户端
 *
 */
public class TestRestTemplateForExchange {

    /****************************Exchange方法************************************/
    /**
     *
     * 使用exchange()，方法可以在发送给服务端的请求中设置头信息！！！！！
     *
     * 注意一下：如果method = RequestMethod.GET，也就是提交的方法要GET的话，那么在客户端提交的请求，body里面放致任何东西都不对，
     *              会报400状态码，提示你Required request body is missing，
     *              因为GET请求的参数在URL的后面...形如http://../../*.action?id=1
     *
     * @throws Exception
     */
    @Test
    public void testExchange1() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring_rest_server/demo5/exchange.action";

        User user = new User(99,"用户99",new Date());

        HttpHeaders httpHeaders = new HttpHeaders();
        //设置contentType为application/json类型
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Accept","application/json");

        HttpEntity<User> requestEntity = new HttpEntity<User>(user,httpHeaders);

        restTemplate.exchange(url, HttpMethod.POST,requestEntity, User.class);

    }


    /****************************Exchange方法************************************/
}

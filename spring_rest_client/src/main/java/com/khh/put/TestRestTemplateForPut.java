package com.khh.put;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.khh.entity.User;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by 951087952@qq.com on 2017/8/23.
 *
 * 使用RestTemplate 创建REST的客户端
 *
 */
public class TestRestTemplateForPut {

    /****************************PUT方法************************************/
    /**
     * 通过restTemplate.put方法，对数据进行PUT操作
     *
     *  .put（url,request,uriVariables..）
     *  url:访问路径
     *  request:发送的对象
     *  uriVariables:发送路径中的参数（多个）
     * @throws Exception
     */
    @Test
    public void testPUT1() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring_rest_server/demo5/putUser/{json}.action";
        User user = new User(10,"user10",new Date());
        String json = JSONObject.toJSONString(user);
        System.out.println(json);
        restTemplate.put(url,user,json);

    }

    /**
     * 通过restTemplate.put方法，对数据进行PUT操作
     * @throws Exception
     */
    @Test
    public void testPUT2() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring_rest_server/demo5/putUser2.action";
        User user = new User(10,"user1qq",new Date());
        //因为@RequestBody是接收一个json对象的字符串，所以，要加一个转换器，将对象转变成json对象的字符串
        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
        restTemplate.put(url,user);
    }

    /****************************PUT方法************************************/
}

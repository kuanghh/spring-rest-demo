package com.khh.post;

import com.khh.entity.User;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 951087952@qq.com on 2017/8/23.
 *
 * 使用RestTemplate 创建REST的客户端
 *
 */
public class TestRestTemplateForPost {

    /****************************POST方法************************************/
    /**
     * 通过restTemplate.getForObject方法，访问资源
     * 这个是一个方便的方法，详细请看方法4 testPOST4
     * @throws Exception
     */
    @Test
    public void testPOST1() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring_rest_server/demo5/postUser.action";

        User user = new User(88,"用户88",new Date());
        //不加下面一行代码的话，会报415 Unsupported Media Type
        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, user, User.class);
        System.out.println(responseEntity.getBody().toString());
    }

    /**
     * 此方法，传入的数据到Controller的时候，参数注入失败User{id=null, name='null', birthday=null}
     * @throws Exception
     */
    @Test
    public void testPOST2() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring_rest_server/demo5/postUser2.action";

        User user = new User(88,"用户88",new Date());
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, user, User.class);
        System.out.println(responseEntity.getBody().toString());
    }

    /**
     * 此方法，传入的数据到Controller的时候，参数注入失败User{id=null, name='null', birthday=null}
     * @throws Exception
     */
    @Test
    public void testPOST3() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring_rest_server/demo5/postUser2.action";

        User user = new User(88,"用户88",new Date());

        Map<String,Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("name",user.getName());
        map.put("birthday",user.getBirthday());

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, user, User.class,map);
        System.out.println(responseEntity.getBody().toString());
    }


    /**
     * 通过restTemplate.getForObject方法，访问资源
     *
     * @throws Exception
     */
    @Test
    public void testPOST4() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring_rest_server/demo5/postUser.action";

        User user = new User(88,"用户88",new Date());
        //设置请求头为application/json
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        //把请求体和请求头放进HttpEntity对象中
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user,httpHeaders);

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, userHttpEntity, User.class);
        System.out.println(responseEntity.getBody().toString());
    }
    /****************************POST方法************************************/
}

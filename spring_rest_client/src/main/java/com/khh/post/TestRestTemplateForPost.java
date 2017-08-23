package com.khh.post;

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
     * @throws Exception
     */
    @Test
    public void testPOST1() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring_rest_server/demo5/postUser.action";

        User user = new User(88,"用户88",new Date());
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

    /****************************POST方法************************************/
}

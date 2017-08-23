package com.khh.get;

import com.khh.entity.User;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 951087952@qq.com on 2017/8/23.
 *
 * 使用RestTemplate 创建REST的客户端
 *
 */
public class TestRestTemplateForGet {

    /****************************GET方法************************************/
    /**
     * 通过restTemplate.getForObject方法，访问资源
     * @throws Exception
     */
    @Test
    public void testGET1() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring_rest_server/demo3/find/{id}.json";

        String id = "2";

        User user = restTemplate.getForObject(url, User.class, id);
        System.out.println(user);
        //输出：User{id=2, name='user2', birthday=Wed Aug 23 09:37:35 CST 2017}
    }

    /**
     * 通过restTemplate.getForEntity方法，访问资源
     *
     *  .getForEntity 与 .getForObject的区别在于，前者会返回请求对象以及响应相关的额外信息，而后者只返回请求类型的对象，
     *
     * @throws Exception
     */
    @Test
    public void testGET2() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring_rest_server/demo3/find/{id}.json";

        String id = "2";

        ResponseEntity<User> forEntity = restTemplate.getForEntity(url, User.class, id);
        System.out.println(forEntity);
        //输出：<200 OK,User{id=2, name='user2', birthday=Wed Aug 23 09:36:53 CST 2017},{Server=[Apache-Coyote/1.1], Content-Type=[application/json;charset=UTF-8], Transfer-Encoding=[chunked], Date=[Wed, 23 Aug 2017 01:36:53 GMT]}>
    }

    /**
     * 通过restTemplate.getForEntity方法，访问资源，并获取指定的响应头信息
     *
     * @throws Exception
     */
    @Test
    public void testGET3() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring_rest_server/demo3/find/{id}.json";

        String id = "3";
        ResponseEntity<User> response = restTemplate.getForEntity(url, User.class, id);
        HttpStatus statusCode = response.getStatusCode();//获取状态码
        User user = response.getBody();//获取资源
        HttpHeaders headers = response.getHeaders();//获取响应头信息
        MediaType contentType = headers.getContentType();
        System.out.println("contentType :" + contentType);
        System.out.println("status Code :" + statusCode.value());
        System.out.println("user is :" + user);
//        输出如下：
//        contentType :application/json;charset=UTF-8
//        status Code :200
//        user is :User{id=3, name='user3', birthday=Wed Aug 23 09:44:14 CST 2017}
    }
    /****************************GET方法************************************/
}

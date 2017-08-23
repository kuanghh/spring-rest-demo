package com.khh.delete;

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
public class TestRestTemplateForDelete {

    /****************************Delete方法************************************/
    /**
     * 通过restTemplate.getForObject方法，访问资源
     * @throws Exception
     */
    @Test
    public void testDELETE1() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/spring_rest_server/demo5/deleteUser/{id}.action";
        String id = "2";
        restTemplate.delete(url,id);
    }


    /****************************Delete方法************************************/
}

package com.khh.demo3.controller;

import com.khh.entity.Error;
import com.khh.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by 951087952@qq.com on 2017/8/22.
 * 提供资源之外的其他内容
 */
@RestController//当用 @RestController 取代了 @Controller之后，所有返回给客户端的方法均默认带上@ResponseBody注解
@RequestMapping(value = "/demo3")
public class Demo3Controller {


    /**
     * 访问  http://localhost:8080/spring_rest_server/demo3/find/5.json，则返回{"id":5,"name":"user5","birthday":1503391721927}
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/find/{id}",method = RequestMethod.GET)
    public User find(@PathVariable Integer id) throws Exception{
        return new User(id,"user" + id ,new Date());
    }

    /**
     * 考虑上面的方法，
     *
     *          当如果传上来的id为空，或者id的类型无法转换成Integer类型的话，是否应该传一个错误的消息返回到客户端页面呢？
     *
     *      Spring提供了多种方式来处理这样的情景：
     *          1、使用@ResponseStatus注解可以指定状态码
     *          2、控制器方法可以返回ResponseEntity对象，该对象能够包含更多响应相关的元数据
     *          3、异常处理器能够应对错误场景，这样处理器方法就能关注与正常的状况
     *
     * @param idStr
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findForError/{idStr}",method = RequestMethod.GET)
    public ResponseEntity findForError(@PathVariable String idStr){
        if(StringUtils.isEmpty(idStr)){
            Error error = new Error(HttpStatus.NOT_FOUND.value(),"检测不到任何id");
            return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);//这里会返回xml格式，而其他会返回json格式
        }
        Integer id = 0;
        try {
             id = Integer.valueOf(idStr);
        }catch (Exception e){
//            e.printStackTrace();
            /**以下两行代码可以用@ExcpetionHandler把它解耦出来,只需要抛出异常，可单独处理**/
//            Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"id识别错误");
//            return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
            throw new NumberFormatException();
        }
        return new ResponseEntity<User>(new User(id,"user" + id ,new Date()),HttpStatus.OK);
    }

    /**
     * 单独处理 NumberFormatException异常
     * @param e
     * @return
     */
//    @ExceptionHandler(NumberFormatException.class)
//    public ResponseEntity<Error> idParseError(NumberFormatException e){
//        Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"id识别错误");
//        return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    /**
     * 上面方法的升级版
     * @param e
     * @return
     */
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
    public Error idParseError2(NumberFormatException e){
        Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"id识别错误2");
        return error;
    }

}

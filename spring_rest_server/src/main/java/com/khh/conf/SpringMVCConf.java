package com.khh.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

/**
 * Created by 951087952@qq.com on 2017/8/22.
 * spring mvc 配置
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.khh.*.controller")
public class SpringMVCConf extends  WebMvcConfigurerAdapter{



    /************************************************************************************************/
        /*
            spring 提供了两种方法将资源的java表述形式转换为发送给客户端的表述形式
                1、消息转换器：通过一个消息转换器将控制器所返回的对象转换为呈现给客户端的表述形式
                2、内容协商：选择一个视图，它能够将模型渲染为呈现给客户端的表述形式

                注意：
                        1、在每个方法上都要加上@ResponseBody，或者直接带类上面用@RestController取代@Controller
                        2、当两种方法同时使用时，一切返回类型都按照第一种方法来

        */
    /************************************************************************************************/
    /****************************************************************************************************
     *  1、消息转换器：通过一个消息转换器将控制器所返回的对象转换为呈现给客户端的表述形式
     * 配合demo1使用
     *
     * 创建一个Http信息转换器
     *
     *  这里使用MappingJackson2HttpMessageConverter转换器，这样声明了 @ResponseBody 的方法
     *  ，将会返回以json格式的信息给客户端
     *
     *  如果使用了MappingJackson2XmlHttpMessageConverter, 那么声明了 @ResponseBody 的方法
     *  ，将会返回以xml格式的信息给客户端
     *
     * @return
     */
    @Bean
    public HttpMessageConverter httpMessageConverter(){
        return new MappingJackson2HttpMessageConverter();
//        return new MappingJackson2XmlHttpMessageConverter();
    }

    /**这个很重要**/
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(HttpMessageConverter httpMessageConverter){
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        adapter.getMessageConverters().add(httpMessageConverter);//把消息转换器注入
        return adapter;
    }

    /**这个很重要**/
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping(){
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        return mapping;
    }
    /************************************************************************************************/




    /***********************************************************************************************
     * 2、内容协商：选择一个视图，它能够将模型渲染为呈现给客户端的表述形式
     *
     * 配合demo2使用
     *
     * 在内容协商当中，第一步首先是确定请求的媒体类型
     * 如何确定？
     *      我们可以配置一个ContentNegotiatingViewResolver，ContentNegotiatingViewResolver将会考虑到Accept头部信息并使用它所请求的媒体类型
     *      ，但是它会先查看URL的文件扩展名。如果URL在结尾处有文件扩展名的话，ContentNegotiatingViewResolver将会基于该扩展名确定所需的类型
     *      例如：
     *            如果扩展名是“.json”的话，那么所需要的内容就必须是“application/json”。
     *            如果扩展名是“.xml”的话，那么所需要的内容就必须是“application/xml”。
     * ！！   如果根据文件扩展名不能得到任何媒体类型的话，那就会考虑请求中的Accept头部信息。
     * ！！！ 最后，如果没有Accept头部信息，并且扩展名也不能提供帮助的话，ContentNegotiatingViewResolver将会使用“/"作为默认的内容类型，
     *        这就意味着客户端必须接受服务器发送的任何形式的表述
     *
     * ContentNegotiatingViewResolver本身不会解析视图，而是委托给其他的视图解析器，让它们来解析视图
     *
     */
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager cnm){
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(cnm);
        return resolver;
    }
    /**
     * 继续上面的内容
     *
     * 影响媒体类型的选择
     *      在上述的选择过程中，我们阐述了确定所请求媒体类型的默认策略。但是通过为其设置一个ContentNegotiationManager，我们能够改变它的行为
     *
     * 这里需要继承WebMvcConfigurerAdapter，然后重载configureContentNegotiation方法,
     *      或者声明一个ContentNegotiationManagerFactoryBean，让它来产生ContentNegotiationManager
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.defaultContentType(MediaType.APPLICATION_XML);//设置默认的内容类型为xml类型
        configurer.defaultContentType(MediaType.APPLICATION_JSON);//设置默认的内容类型为json类型
    }
    /************************************************************************************************/


}

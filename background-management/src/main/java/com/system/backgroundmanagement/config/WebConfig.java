package com.system.backgroundmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author luoyuequan
 * @time 2020/1/9 15:03
 */
@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    InterceptorHandlerConfig interceptorHandlerConfig;


    /**
     * 全局跨域访问配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedOrigins("*");
    }

    /**
     * 拦截器添加
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(interceptorHandlerConfig);
        List<String> excludePath = List.of("/static/**", "/webjar/**", "swagger-ui.html", "/swagger-resources/**", "/v2/**");
        registration.excludePathPatterns(excludePath).addPathPatterns("/**");
    }

//    /**
//     * 静态资源文件处理
//     *
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //可以通过http://127.0.0.1:8080/web/home.html访问resources/web/home.html页面
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//    }


//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //创建fastJson消息转换器
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        //  FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        List<MediaType> supportedMediaTypes = new ArrayList<>();
//        supportedMediaTypes.add(MediaType.ALL);
//        fastConverter.setSupportedMediaTypes(supportedMediaTypes);
//
//        //创建配置类
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
//
//        //修改配置返回内容的过滤
//        fastJsonConfig.setSerializerFeatures(
//                //List字段如果为null,输出为[],而非null
//                SerializerFeature.WriteNullListAsEmpty,
//                //消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
//                SerializerFeature.DisableCircularReferenceDetect,
//                //是否输出值为null的字段,默认为false
////                SerializerFeature.WriteMapNullValue,
//                //字符类型字段如果为null,输出为"",而非null
//                SerializerFeature.WriteNullStringAsEmpty,
//                //Boolean字段如果为null,输出为false,而非null
//                SerializerFeature.WriteNullBooleanAsFalse
////                SerializerFeature.PrettyFormat
//        );
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        //将fastjson添加到视图消息转换器列表内
//        converters.add(fastConverter);
//    }
}

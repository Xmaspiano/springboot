package com.springboot.architecture;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:14
 * @param   
 * @return   
 */  
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(fastJsonHttpMessageConverter());
//    }

    @Bean
    public HttpMessageConverter fastJsonHttpMessageConverter(){
        return new com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter();
    }
//
//    /**
//     * 设置视图解析器
//     * @param templateEngine
//     * @return
//     */
//    @Bean
//    public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine);
//        return resolver;
//    }
//
//    /**
//     * 设置模板引擎
//     * @param templateResolver
//     * @return
//     */
//    @Bean
//    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver){
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(templateResolver);
//        return engine;
//    }
//
//    /**
//     * 模板解析引擎
//     * @return
//     */
//    @Bean
//    public TemplateResolver templateResolver(){
//        TemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setPrefix("/WEB-INF/template/");//设置地址前缀
//        resolver.setSuffix(".html");//设置后缀
//        resolver.setCacheable(false);//设置不缓存
//        resolver.setTemplateMode("HTML5");
//        return resolver;
//
//    }
}

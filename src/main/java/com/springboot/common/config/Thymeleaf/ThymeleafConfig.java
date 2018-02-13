//package com.springboot.common.config.Thymeleaf;
//
//import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
//import com.springboot.system.PackageUtil;
//import com.springboot.system.thymeleaf.dialect.ShiroDialectEx;
//import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.thymeleaf.dialect.AbstractDialect;
//import org.thymeleaf.dialect.IDialect;
//import org.thymeleaf.spring4.SpringTemplateEngine;
//import org.thymeleaf.templateresolver.TemplateResolver;
//
//import java.util.LinkedHashSet;
//import java.util.Set;
//
////@Configuration
//public class ThymeleafConfig extends WebMvcAutoConfiguration {
//
//    /**
//     * 设置模板引擎
//     * @param templateResolver
//     * @return
//     */
////    @Bean
////    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver){
////        SpringTemplateEngine engine = new SpringTemplateEngine();
////        engine.setTemplateResolver(templateResolver);
////
////        Set<IDialect> additionalDialects = new LinkedHashSet();
////        additionalDialects.add(new ShiroDialect());
////
////        Dialect dialect;
////        Class<AbstractDialect> abstractDialectClass;
////        try {
////            for (Class clas : new PackageUtil().getClasses("com.springboot")) {
////                if(AbstractDialect.class.isAssignableFrom(clas)){
////                    abstractDialectClass = clas;
////                    dialect = abstractDialectClass.getAnnotation(Dialect.class);
////                    if (dialect != null) {
////                        additionalDialects.add(abstractDialectClass.newInstance());
////                    }
////                }
////            }
////        } catch (InstantiationException e) {
////            e.printStackTrace();
////        } catch (IllegalAccessException e) {
////            e.printStackTrace();
////        }
////
////        engine.setAdditionalDialects(additionalDialects);
////        return engine;
////    }
//
//}

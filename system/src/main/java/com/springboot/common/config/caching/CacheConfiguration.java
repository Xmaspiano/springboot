package com.springboot.common.config.caching;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

@Configuration
@EnableCaching // 标注启动缓存
public class CacheConfiguration {

    @Bean
    public org.apache.shiro.cache.ehcache.EhCacheManager EhCacheManagerShiro(){
        org.apache.shiro.cache.ehcache.EhCacheManager  manager= new org.apache.shiro.cache.ehcache.EhCacheManager();
        net.sf.ehcache.CacheManager managerEhcache = ehCacheManagerFactoryBean().getObject();

        CustomerCacheManagerEventListenerFactory customerCacheManagerEventListenerFactory
                = new CustomerCacheManagerEventListenerFactory();

        managerEhcache.setCacheManagerEventListener(
                customerCacheManagerEventListenerFactory.createCacheManagerEventListener(
                        managerEhcache, new Properties()));

        manager.setCacheManager(managerEhcache);
        return manager;
    }
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache/ehcache.xml"));
        factoryBean.setShared(true);
        return factoryBean;
    }


}
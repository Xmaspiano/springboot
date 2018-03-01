package com.springboot.architecture.caching;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;
/**  
 *    
 * 标注启动缓存
 * @author XmasPiano  
 * @date 2018/3/1 上午10:19
 * @param   
 * @return   
 */  
@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    public org.apache.shiro.cache.ehcache.EhCacheManager ehCacheManagerShiro(){
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
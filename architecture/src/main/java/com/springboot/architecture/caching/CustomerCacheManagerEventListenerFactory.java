package com.springboot.architecture.caching;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.event.CacheManagerEventListener;
import net.sf.ehcache.event.CacheManagerEventListenerFactory;

import java.util.Properties;
/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:19
 * @param   
 * @return   
 */  
public class CustomerCacheManagerEventListenerFactory extends CacheManagerEventListenerFactory {
    @Override
    public CacheManagerEventListener createCacheManagerEventListener(CacheManager cacheManager, Properties properties) {
        return new CustomerCacheManagerEventListener(cacheManager);
    }
}
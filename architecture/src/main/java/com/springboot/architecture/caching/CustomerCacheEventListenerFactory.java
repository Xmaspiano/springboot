package com.springboot.architecture.caching;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

import java.util.Properties;
/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:19
 * @param   
 * @return   
 */  
public class CustomerCacheEventListenerFactory extends CacheEventListenerFactory {
    @Override
    public CacheEventListener createCacheEventListener(Properties properties) {
        return new CustomerCacheEventListener();
    }
}
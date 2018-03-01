package com.springboot.system.util;

import com.springboot.system.shiro.AuthRealm;
import com.springboot.system.auth.entity.firstDsE.Auth;
import com.springboot.system.service.HrmresourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:25
 * @param   
 * @return   
 */  
@Component
public class ShiroCacheUtil {

    @Autowired
    private HrmresourceService hrmresourceService;

    public void removeCacheByUser(Iterable<Auth> entities){
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        AuthRealm shiroRealm = (AuthRealm)rsm.getRealms().iterator().next();

        entities.forEach(e -> {
            if(e.getUserId() != 0L) {
                SimplePrincipalCollection principals = new SimplePrincipalCollection(
                        hrmresourceService.findOne(e.getUserId()),AuthRealm.class.getName()
                );
                shiroRealm.getAuthorizationCache().remove(principals);
            }
        });
    }
}

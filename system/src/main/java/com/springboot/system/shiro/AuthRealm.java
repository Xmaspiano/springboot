package com.springboot.system.shiro;

import com.springboot.system.auth.service.AuthService;
import com.springboot.system.oa.entity.secondDsE.Hrmresource;
import com.springboot.system.resources.entity.firstDsE.Resources;
import com.springboot.system.oa.service.HrmresourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:16
 * @param   
 * @return   
 */  
public class AuthRealm extends AuthorizingRealm implements CacheManagerAware {
    @Autowired
    private HrmresourceService hrmresourceService;

    @Autowired
    private AuthService authService;

    @Value("${admin.loginid}")
    private String adminName;

    @Value("${admin.password}")
    private String password;

    @Value("${admin.lastname}")
    private String lastname;

    /**
     * 认证.登录
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户输入的token
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;
        String username = utoken.getUsername();
//        User user = userService.findByLoginid(username);
        Hrmresource hrmresource = hrmresourceService.findByLoginid(username);
        if(hrmresource == null && adminName.equals(username)){
            hrmresource = new Hrmresource();
            hrmresource.setLoginid(adminName);
            hrmresource.setPassword(password);
            hrmresource.setLastname(lastname);
        } else if( hrmresource == null ) {
            throw new UnknownAccountException("用户名密码不存在");
        }
        //放入shiro.调用CredentialsMatcher检验密码
        return new SimpleAuthenticationInfo(hrmresource, hrmresource.getPassword(),this.getClass().getName());
    }

    /**
     * 授权
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //获取session中的用户
        Hrmresource hrmresource=(Hrmresource) principal.fromRealm(this.getClass().getName()).iterator().next();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        if(adminName.equals(hrmresource.getLoginid())){
            info.addStringPermission("*");
        }else {
            List<String> permissions = new ArrayList();
            List<Resources> resources = authService.findAllResByUser(hrmresource);
            if (resources.size() > 0) {
                resources.forEach(resource -> permissions.add(resource.getShiroAuth()));
            }

            //将权限放入shiro中.
            info.addStringPermissions(permissions);
        }
        return info;
    }

    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}
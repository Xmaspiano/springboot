package com.springboot.common.config.shiro;

import com.springboot.system.auth.service.AuthService;
import com.springboot.system.entity.firstDsE.Role;
import com.springboot.system.entity.secondDsE.Hrmresource;
import com.springboot.system.resources.entity.firstDsE.Resources;
import com.springboot.system.service.HrmresourceService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//import com.springboot.system.entity.firstDsE.User;

public class AuthRealm extends AuthorizingRealm implements CacheManagerAware {
    @Autowired
    private HrmresourceService hrmresourceService;

    @Autowired
    private AuthService authService;

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
//        User user = userService.findByLoginid(username);
        Hrmresource hrmresource = hrmresourceService.findByLoginid(username);
        if(hrmresource == null){
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(hrmresource, hrmresource.getPassword(),this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        Hrmresource hrmresource=(Hrmresource) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions=new ArrayList();
        List<Resources> resources = authService.findAllResByUser(hrmresource);
        if(resources.size()>0) {
            resources.forEach(resource -> permissions.add(resource.getShiroAuth()));
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;
    }



}
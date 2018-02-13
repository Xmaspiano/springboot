package com.springboot.common.config.shiro;

import com.springboot.common.config.filter.LoginFilter;
import com.springboot.common.config.filter.TokenOverFilter;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.servlet.Filter;
import java.util.*;


@Configuration
public class ShiroConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean
    @Primary
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/loginSuccess");

        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();

        LoginFilter authcFilter = new LoginFilter();

        filtersMap.put("ajaxLogin", new TokenOverFilter());
        filtersMap.put("authc", authcFilter);
        bean.setFilters(filtersMap);

        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<String, String>();
        //拦截器.
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/init/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout*","logout");

        filterChainDefinitionMap.put("/error.jsp*","anon");
        filterChainDefinitionMap.put("/druid/*","anon");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "ajaxLogin,authc");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    //配置核心安全事务管理器
    @Bean
    @Primary
    public SecurityManager asecurityManager(AuthRealm authRealm, EhCacheManager EhCacheManagerShiro) {
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();

        authRealm.setCachingEnabled(true); //启用缓存，默认false；
        authRealm.setAuthenticationCachingEnabled(true);//启用身份验证缓存，即缓存AuthenticationInfo信息，默认false；
        authRealm.setAuthenticationCacheName("AuthenticationRetryCache");//缓存AuthenticationInfo信息的缓存名称；
        authRealm.setAuthorizationCachingEnabled(true);//启用授权缓存，即缓存AuthorizationInfo信息，默认false；
        authRealm.setAuthorizationCacheName("AuthorizationRetryCache");//缓存AuthorizationInfo信息的缓存名称；

        manager.setRememberMeManager(rememberMeManager());

        manager.setRealm(authRealm);
        manager.setCacheManager(EhCacheManagerShiro);
        return manager;
    }

    //配置自定义的权限登录器
    @Bean
    @Primary
    public AuthRealm authRealm(CredentialsMatcher credentialsMatcher) {
        AuthRealm authRealm=new AuthRealm();
        authRealm.setCredentialsMatcher(credentialsMatcher);
        return authRealm;
    }
    //配置自定义的密码比较器
    @Bean
    @Primary
    public CredentialsMatcher credentialsMatcher() {
        return new MyCredentialsMatcher();
    }

    @Bean
    @Primary
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    @Primary
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    @Bean
    @Primary
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * cookie管理器;
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        LOGGER.info("注入Shiro的记住我(CookieRememberMeManager)管理器-->rememberMeManager", CookieRememberMeManager.class);
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
        cookieRememberMeManager.setCipherKey(cipherKey);
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
        simpleCookie.setHttpOnly(true);
        //记住我cookie生效时间,默认30天 ,单位秒：60 * 60 * 24 * 30
        simpleCookie.setMaxAge(259200);

        return simpleCookie;
    }
}

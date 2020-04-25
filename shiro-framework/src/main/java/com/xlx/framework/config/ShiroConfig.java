package com.xlx.framework.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.xlx.framework.shiro.credentials.RetryLimitHashedCredentialsMatcher;
import com.xlx.framework.shiro.filter.captcha.CaptchaValidateFilter;
import com.xlx.framework.shiro.realm.UserRealm;
import com.xlx.framework.shiro.session.OnlineSessionDAO;
import net.sf.ehcache.CacheManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.net.CookieManager;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置
 *
 * @author xielx at 2020/4/22 21:00
 */
@Configuration
public class ShiroConfig {
    
    
    /**
     * 缓存管理
     * 使用Ehcache实现
     * @return EhCacheManager
     */
    @Bean
    public EhCacheManager ehCacheManager(){
        //net.sf
        CacheManager cacheManager = CacheManager.getCacheManager("fastshiro");
        
        EhCacheManager em = new EhCacheManager();
        
        if (cacheManager == null){
            em.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
        }else {
            em.setCacheManager(cacheManager);
        }
        
        return em;
    }
    
    /**
     * 自定义的realm
     * @param cacheManager 缓存
     * @return Realm
     */
    @Bean("userRealm")
    public Realm realm(EhCacheManager cacheManager){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        userRealm.setCacheManager(cacheManager);
        return userRealm;
    }
    
    /**
     * 自定义的密码验证器
     * @return HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher  credentialsMatcher = new RetryLimitHashedCredentialsMatcher(ehCacheManager());
        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }
    
    
    /**
     * 会话ID生成器
     * @return UUID
     */
    public SessionIdGenerator sessionIdGenerator(){
        return new JavaUuidSessionIdGenerator();
    }
    
    /**
     * SessionCookie
     * @return Session的cookie
     */
    public SimpleCookie sessionIdCookie(){
        SimpleCookie sessionIdCookie = new SimpleCookie("sid");
        sessionIdCookie.setHttpOnly(true);
        // 默认值为-1,表示浏览器关闭就失效
        sessionIdCookie.setMaxAge(-1);
        
        return sessionIdCookie;
    }
    
    /**
     * SessionDAO
     * 进行Session的CRUD管理
     * @return SessionDAO
     */
    public SessionDAO sessionDAO(){
        OnlineSessionDAO sessionDAO = new OnlineSessionDAO();
        //
        //sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        //sessionDAO.setSessionIdGenerator(sessionIdGenerator());
        return sessionDAO;
    }
    
    /**
     * 会话验证调度器
     */
    
    
    /**
     * 会话管理
     * @return DefaultWebSessionManager
     */
    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        // 缓存管理器
        manager.setCacheManager(ehCacheManager());
        // 删除过期的session
        manager.setDeleteInvalidSessions(true);
        // 设置全局Session过期时间,默认30mins
        manager.setGlobalSessionTimeout(30 * 60 * 1000); // 单位:毫秒
        // 去掉JSESSIONID
        manager.setSessionIdUrlRewritingEnabled(false);
        // 会话验证调度器
        //manager.setSessionValidationScheduler();
        // 定时检查session
        //manager.setSessionValidationSchedulerEnabled(true);
        //
        //manager.setSessionIdCookieEnabled(true);
        //manager.setSessionIdCookie(sessionIdCookie());
        // sessionDAO
       // manager.setSessionDAO(sessionDAO());
        
        return manager;
    }
    
    /**
     * 记住我
     * @return Cookie
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //cookie.setDomain();
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7 * 24 * 60 * 60); // 单位:秒
        return cookie;
    }
    
    /**
     * 记住我管理器
     * @return CookieRememberMeManager
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie());
        rememberMeManager.setCipherKey(Base64.decode("bGVhcm4gc2hpcm8gcXVpY2tseSBhbmQgc3lzdGVtYXRpY2FsbHk="));
        return rememberMeManager;
    }
    
    /**
     * 安去管理器
     * @param realm UserRealm
     * @return SecurityManager
     */
    @Bean("securityManager")
    public SecurityManager securityManager(Realm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // Realm
        securityManager.setRealm(realm);
        // 记住我管理器
        securityManager.setRememberMeManager(cookieRememberMeManager());
        // 缓存管理器
        securityManager.setCacheManager(ehCacheManager());
        // Session管理器
        securityManager.setSessionManager(sessionManager());
        
        return securityManager;
    }
    
    /**
     * 自定义验证码验证过滤器
     * @return  CaptchaValidateFilter
     */
    //@Bean
    public CaptchaValidateFilter captchaValidateFilter(){
        return new CaptchaValidateFilter();
    }
    
    
    /**
     * Shiro的Web过滤器
     * @param securityManager SecurityManager
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //
        shiroFilter.setSecurityManager(securityManager);
        
        // 登录url
        shiroFilter.setLoginUrl("/login");
        // 登录成功后跳转url
        //shiroFilter.setSuccessUrl("/index");
        // 未授权跳转url
        shiroFilter.setUnauthorizedUrl("/unauth");
        
        // 设置过滤链
        LinkedHashMap<String, String> filterChainMap= new LinkedHashMap<>();
        
        // 不经过滤器
        filterChainMap.put("/favicon.ico**", "anon");
        filterChainMap.put("/css/**","anon");
        filterChainMap.put("/js/**","anon");
        filterChainMap.put("/fonts/**","anon");
        filterChainMap.put("/img/**","anon");
        filterChainMap.put("/druid/**","anon");
        filterChainMap.put("/docs/**","anon");
        filterChainMap.put("/captcha/captchaImage**","anon");
        
        filterChainMap.put("/logout","logout");
        filterChainMap.put("/login","anon");
        
        // 其他请求需要过滤
        filterChainMap.put("/**","user");
        shiroFilter.setFilterChainDefinitionMap(filterChainMap);
        
        // 配置自定义拦截器
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("captchaFilter",captchaValidateFilter());
        shiroFilter.setFilters(filters);
        
        return shiroFilter;
    }
    
    /**
     * thymeleaf使用shiro标签
     * @return ShiroDialect
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
    
    /**
     * 开启Shiro的权限注解
     * @param securityManager SecurityManager
     * @return AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor attributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        attributeSourceAdvisor.setSecurityManager(securityManager);
        return attributeSourceAdvisor;
    }
}

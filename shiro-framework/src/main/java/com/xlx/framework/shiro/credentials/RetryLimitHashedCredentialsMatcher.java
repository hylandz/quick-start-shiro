package com.xlx.framework.shiro.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xielx at 2020/4/22 21:12
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    
    private final Cache<String,AtomicInteger> passwordRetryCache;
    
    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager){
        passwordRetryCache = cacheManager.getCache("loginRecordCache");
    }
    
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username);
        
        if (retryCount == null){
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username,retryCount);
        }
    
        if (retryCount.incrementAndGet() > 5){
            throw new ExcessiveAttemptsException("你已连续输错5次,请10分钟后再试");
        }
    
    
        boolean match = super.doCredentialsMatch(token, info);
        if (match){
            passwordRetryCache.remove(username);
        }
        
        return match;
    }
}

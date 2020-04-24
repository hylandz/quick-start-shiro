package com.xlx.framework.shiro.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * @author xielx at 2020/4/22 21:12
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return super.doCredentialsMatch(token, info);
    }
}

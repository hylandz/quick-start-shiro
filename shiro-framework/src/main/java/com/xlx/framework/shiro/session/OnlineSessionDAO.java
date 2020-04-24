package com.xlx.framework.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import java.io.Serializable;

/**
 * SessionDAO
 *
 * @author xielx at 2020/4/24 14:58
 */
public class OnlineSessionDAO extends EnterpriseCacheSessionDAO {
    
    
    @Override
    protected Session doReadSession(Serializable sessionId) {
        return super.doReadSession(sessionId);
    }
    
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
    }
    
    
}

package com.xlx.framework.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 * SessionDAO
 *
 * @author xielx at 2020/4/24 14:58
 */
public class OnlineSessionDAO extends EnterpriseCacheSessionDAO {
    
    public OnlineSessionDAO(){
        super();
    }
    
    @Override
    protected Session doReadSession(Serializable sessionId) {
        return super.doReadSession(sessionId);
    }
    
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
    }
    
    @Override
    protected Serializable doCreate(Session session) {
        return super.doCreate(session);
    }
    
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
    }
    
    @Override
    public void setSessionIdGenerator(SessionIdGenerator sessionIdGenerator) {
        super.setSessionIdGenerator(sessionIdGenerator);
    }
}

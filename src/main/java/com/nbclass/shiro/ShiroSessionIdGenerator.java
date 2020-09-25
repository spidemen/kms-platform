package com.nbclass.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 * @Description 自定义SessionId生成器
 * @Author Sans
 * @CreateTime 2019/6/11 11:48
 */
public class ShiroSessionIdGenerator implements SessionIdGenerator {
    /**
     * 实现SessionId生成
     * @Author Sans
     * @CreateTime 2019/6/11 11:54
     */
    String REDIS_PREFIX_LOGIN = "login_token_%s";

    @Override
    public Serializable generateId(Session session) {
        Serializable sessionId = new JavaUuidSessionIdGenerator().generateId(session);
        return String.format(REDIS_PREFIX_LOGIN, sessionId);
    }
}
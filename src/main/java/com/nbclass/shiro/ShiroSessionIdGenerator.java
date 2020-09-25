package com.nbclass.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;

/**
 *  <p> 自定义SessionId生成器 </p>
 *
 * @description :
 * @author : zhengqing
 * @date : 2019/8/23 15:47
 */
public class ShiroSessionIdGenerator implements SessionIdGenerator {
    /**
     * 实现SessionId生成
     */
    @Override
    public Serializable generateId(Session session) {
        return new JavaUuidSessionIdGenerator().generateId(session);
    }
}

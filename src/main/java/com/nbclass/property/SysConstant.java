package com.nbclass.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*常量配置类*/
@Component
@ConfigurationProperties(prefix = "const")
public class SysConstant {
    private String staticServer;

    public String getStaticServer() {
        return staticServer;
    }

    public void setStaticServer(String staticServer) {
        this.staticServer = staticServer;
    }
}

package com.eyeieye.melody.demo.controller.nosession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.eyeieye.melos.web.nosession.Encode;
import com.eyeieye.melos.web.nosession.SessionCookieStoreConfig;

import java.util.HashSet;
import java.util.Set;

@Component
@ConfigurationProperties(prefix = "melodymelody.web.meta.cookie")
public class LocalSessionCookieStoreConfig extends SessionCookieStoreConfig implements InitializingBean{


    @Override
    @Autowired
    @Qualifier("defaultEncode")
    public void setEncode(Encode encode) {
        super.setEncode(encode);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set<String> attributeNames = new HashSet<>();
        attributeNames.add("_user");
        setAttributeNames(attributeNames);
        setCookieName("_u_");
    }
}

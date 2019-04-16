package com.eyeieye.melody.demo.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.eyeieye.melos.web.url.URLBroker;
import com.eyeieye.melos.web.url.URLConfig;

@Configuration
public class UrlBrokerConfiguration {

    @Autowired
    Environment environment;

    //TODO 留一个,其他的配xml
    @Bean("imageServer")
    public URLBroker imageServer(@Autowired URLBroker appServer){
        URLConfig defaultConfig = appServer.getConfig();
        URLBroker urlBroker = new URLBroker();
        URLConfig urlConfig = new URLConfig();

        urlConfig.setHost(environment.getProperty("imageServer.host",defaultConfig.getHost()));
        urlConfig.setPort(environment.getProperty("imageServer.port",Integer.class,defaultConfig.getPort()));
        urlConfig.setPath(environment.getProperty("imageServer.path",defaultConfig.getPath()));
        urlConfig.setProtocol(environment.getProperty("imageServer.protocol",defaultConfig.getProtocol()));

        urlBroker.setConfig(urlConfig);
        urlBroker.setEncoding("UTF-8");

        return urlBroker;
    }

}

package com.eyeieye.melody.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;

import com.eyeieye.melos.web.interceptor.AutoAsyncWebRequestInterceptor;

@Component
public class AutoAsyncWebRequestInterceptorTest implements AutoAsyncWebRequestInterceptor {

    final String NAME = "AutoAsyncWebRequestInterceptorTest";
    @Override
    public String[] pathPatterns() {
        return new String[]{"/interceptor/path/*"};
    }

    @Override
    public String[] excludePatterns() {
        return new String[]{"/interceptor/path/exclude.htm"};
    }

    @Override
    public void preHandle(WebRequest webRequest) throws Exception {
        webRequest.setAttribute(NAME + "-preHandle","yes",0);

    }

    @Override
    public void postHandle(WebRequest webRequest, ModelMap modelMap) throws Exception {

        webRequest.setAttribute(NAME + "-postHandle","yes",0);
    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {

        webRequest.setAttribute(NAME +"-afterCompletion","yes",0);

    }
}

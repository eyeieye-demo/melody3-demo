package com.eyeieye.melody.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.eyeieye.melos.web.interceptor.AutoHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AutoHandlerInterceptorTest implements AutoHandlerInterceptor {



    final String NAME = "AutoHandlerInterceptorTest";
    @Override
    public String[] pathPatterns() {
        return new String[]{"/interceptor/path/*"};
    }

    @Override
    public String[] excludePatterns() {
        return new String[]{"/interceptor/path/exclude.htm"};
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(NAME + "-preHandle","yes");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        request.setAttribute(NAME + "-postHandle","yes");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        request.setAttribute(NAME + "-afterCompletion","yes");
    }
}

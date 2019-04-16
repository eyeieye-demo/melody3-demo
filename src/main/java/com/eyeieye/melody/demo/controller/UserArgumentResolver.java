package com.eyeieye.melody.demo.controller;

import com.eyeieye.melody.demo.domain.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	@Value("${system.devMode:false}")
	public boolean devMode;
	@Value("${test.roles:user,admin}")
	public String[] roles;


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if(methodParameter.getParameterType().equals(User.class)){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("_user");
        User user= null;
        if(obj != null && obj instanceof User) {
            user = (User) obj;
        }
        return user;
    }
}
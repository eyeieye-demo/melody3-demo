package com.eyeieye.melody.demo.controller;

import com.eyeieye.melody.demo.controller.form.LoginForm;
import com.eyeieye.melody.demo.domain.User;
import com.eyeieye.melody.demo.domain.UserGenderEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "nosession")
public class NosessionController {

    Logger logger = LoggerFactory.getLogger(NosessionController.class);

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public void login(HttpSession session) {
        logger.debug((String)session.getAttribute("_test")+"****");

    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(LoginForm loginForm, HttpSession session) {
        User user = new User();
        user.setName("张三");
        user.setAddress("北京市西城区复兴门大街");
        user.setAge(30);
        user.setUuid("10");
        user.setGender(UserGenderEnum.FEMALE);

        session.setAttribute("_user", user);

        session.setAttribute("_userext", "我是存在session里的一句话,但我不存在cookie里");

        session.setAttribute("_test", "这是输入的测试语句");



        return "redirect:center.htm";
    }

    @RequestMapping("center")
    public void center(HttpSession session, ModelMap map) {
        map.put("user", session.getAttribute("_user"));
    }

    @RequestMapping("info")
    public void info(HttpSession session, ModelMap map) {
        User user = (User) session.getAttribute("_user");
        map.put("user", user);
        map.put("userext", session.getAttribute("_userext"));
    }

    @RequestMapping("logout")
    public String logout(HttpSession session, ModelMap map) {
        session.removeAttribute("_user");
        session.removeAttribute("_user");
        session.invalidate();
        return "redirect:login.htm";
    }

    @RequestMapping("extends")
    public void extend() {


    }

    @RequestMapping("extended_user_login")
    public void extendedUserLogin() {


    }

    @RequestMapping("redis_config")
    public void redisConfig() {


    }

    @RequestMapping("use_no_session")
    public void useNoSession() {


    }


    @RequestMapping("introduce")
    public void introducePage(ModelMap modelMap){
        modelMap.put("selected","nosession");
    }


}

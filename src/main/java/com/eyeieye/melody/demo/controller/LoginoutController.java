package com.eyeieye.melody.demo.controller;

import com.eyeieye.melody.demo.controller.form.RegisterForm;
import com.eyeieye.melody.demo.domain.User;
import com.eyeieye.melos.util.StringUtil;
import com.eyeieye.melos.util.uuid.UUIDGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("login")
public class LoginoutController {

    private static Map<String, String> passStore = new HashMap<>();
    private static Map<String, User> userStore = new HashMap<>();

    @Autowired
    private UUIDGenerator uuidGenerator;



    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(ModelMap modelMap,HttpSession session){
        User user = (User)session.getAttribute("_user");

        if(user == null || user.getName() == null){
            return "/login/login";
        }
        return "redirect:/login/user_main.htm";

    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String username, String password, ModelMap modelMap, HttpSession session){

        String checkPass = passStore.get(username);
        if(checkPass == null){
            return "/login/login";
        }
        if(StringUtil.equals(password,checkPass) == false){
            return "/login/login";
        }
        User agent = userStore.get(username);
        if(agent == null){
            return "/login/login";
        }

        String userext = "这是一段存储在本地redis中的session数据："+uuidGenerator.gain();

        modelMap.put("agent",agent);
        modelMap.put("userext", userext);

        session.setAttribute("_user",agent);
        session.setAttribute("_userext",userext);

        return "redirect:/login/user_main.htm";
    }

    @RequestMapping("user_main")
    public void userMain(ModelMap modelMap , HttpSession session, User user){
        //User agent = (User)session.getAttribute("_user");
        User agent = user;
        String userext = (String)session.getAttribute("_userext");

        modelMap.put("agent",agent);
        modelMap.put("userext",userext);



    }


    @RequestMapping(value = "register", method = RequestMethod.GET)
    public void registerPage( RegisterForm registerForm, ModelMap modelMap){
        if(registerForm == null){
            registerForm = new RegisterForm();
        }
        modelMap.put("user",registerForm);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(RegisterForm registerForm, ModelMap modelMap){

        if(StringUtil.isEmpty(registerForm.getUsername()) == true || StringUtil.isEmpty(registerForm.getPassword()) == true){
            return "/login/register";
        }
        if(passStore.get(registerForm.getUsername()) != null){
            return "/login/register";
        }
        User user = new User();
        user.setName(registerForm.getUsername());
        user.setAddress(registerForm.getAddress());
        user.setAge(registerForm.getAge());
        user.setGender(registerForm.getGender());
        user.setUuid(uuidGenerator.gain());
        passStore.put(registerForm.getUsername(),registerForm.getPassword());
        userStore.put(user.getName(),user);
        return "/login/login";
    }

    @RequestMapping("introduce")
    public void introducePage(){}


    @RequestMapping("logout")
    public String logout(ModelMap modelMap, HttpSession session){
        session.setAttribute("_user",null);
        session.setAttribute("_userext",null);
        return "redirect:/login/login.htm";
    }



}

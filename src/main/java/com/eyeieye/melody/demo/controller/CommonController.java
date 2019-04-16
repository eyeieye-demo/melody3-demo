package com.eyeieye.melody.demo.controller;

import com.eyeieye.melody.demo.domain.User;
import com.eyeieye.melos.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommonController {
/**
    @RequestMapping(value = "new_feature/introduce",method = RequestMethod.GET)
    public void newFeature(ModelMap modelMap){
        modelMap.put("selected","new_feature");
    }
    @RequestMapping(value = "config/introduce",method = RequestMethod.GET)
    public void configIntro(ModelMap modelMap){
        modelMap.put("selected","config");
    }
    @RequestMapping(value = "dir_url/introduce",method = RequestMethod.GET)
    public void dirUrlIntro(ModelMap modelMap){
        modelMap.put("selected","dir_url");
    }
    @RequestMapping(value = "webpages/introduce",method = RequestMethod.GET)
    public void webpagesIntro(ModelMap modelMap){
        modelMap.put("selected","webpages");
    }
    @RequestMapping(value = "annotation/introduce",method = RequestMethod.GET)
    public void annotationIntro(ModelMap modelMap){
        modelMap.put("selected","annotation");
    }
    @RequestMapping(value = "nosession/introduce",method = RequestMethod.GET)
    public void nosessionIntro(ModelMap modelMap){
        modelMap.put("selected","nosession");
    }
    @RequestMapping(value = "utils/introduce",method = RequestMethod.GET)
    public void utilsIntro(ModelMap modelMap){
        modelMap.put("selected","utils");
    }
    @RequestMapping(value = "interceptor/introduce",method = RequestMethod.GET)
    public void interceptorIntro(ModelMap modelMap){
        modelMap.put("selected","interceptor");
    }
    @RequestMapping(value = "others/introduce",method = RequestMethod.GET)
    public void othersIntro(ModelMap modelMap){
        modelMap.put("selected","others");
    }
    @RequestMapping(value = "spring/introduce",method = RequestMethod.GET)
    public void springIntro(ModelMap modelMap){
        modelMap.put("selected","spring");
    }
**/
    @RequestMapping("contain/header")
    public void header(ModelMap modelMap, HttpSession session){
        User user = (User) session.getAttribute("_user");
        if(user == null || StringUtil.isEmpty(user.getName())){
            modelMap.put("logined",false);
        }else{
            modelMap.put("logined",true);
        }
    }
    @RequestMapping("contain/htmlhead")
    public void html(String title , ModelMap modelMap){
        modelMap.put("title",title);
    }
    @RequestMapping("contain/left")
    public void left(){

    }
    @RequestMapping("contain/footer")
    public void footer(){

    }

}

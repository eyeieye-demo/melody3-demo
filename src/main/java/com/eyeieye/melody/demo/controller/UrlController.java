package com.eyeieye.melody.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("broker")
public class UrlController {

    @RequestMapping("urlbroker")
    public void urlbroker(){

    }
    @RequestMapping("params")
    @ResponseBody
    public String params(String name , ModelMap modelMap){

      return name;
    }
}

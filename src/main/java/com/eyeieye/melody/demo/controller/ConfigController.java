package com.eyeieye.melody.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("config")

public class ConfigController {
    @RequestMapping("introduce")
    public void introduce(ModelMap modelMap){
    }

    @RequestMapping("list")
    public void list(ModelMap modelMap){}
}

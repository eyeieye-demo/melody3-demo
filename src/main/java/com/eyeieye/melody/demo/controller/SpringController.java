package com.eyeieye.melody.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("spring")
public class SpringController {
    @RequestMapping("introduce")
    public void introducePage(ModelMap modelMap){
        modelMap.put("selected","spring");
    }
}

package com.eyeieye.melody.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("dir_url")
@Controller
public class DirUrlController {
    @RequestMapping("introduce")
    public void introduce(ModelMap modelMap) {
        modelMap.put("selected","dir_url");
    }
    @RequestMapping("directory")
    public void directory(ModelMap modelMap) {
    }
    @RequestMapping("url_broker")
    public void urlBroker(ModelMap modelMap) {
    }
    @RequestMapping("jsppage")
    public void jsppage(ModelMap modelMap){

    }
}

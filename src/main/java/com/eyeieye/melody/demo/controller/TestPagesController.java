package com.eyeieye.melody.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common_pages")
public class TestPagesController {

    @RequestMapping("/index")
    public void index(ModelMap modelMap) {
        modelMap.put("logined",true);
    }
}

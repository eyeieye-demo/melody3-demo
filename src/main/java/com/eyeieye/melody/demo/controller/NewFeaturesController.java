package com.eyeieye.melody.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("new_features")
public class NewFeaturesController {

    @RequestMapping("introduce")
    public void introduce(ModelMap modelMap){

    }
}

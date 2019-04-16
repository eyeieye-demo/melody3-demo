package com.eyeieye.melody.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("interceptor")
public class InterceptorController {

    @RequestMapping("path/interceptor")
    public void interceptor(){

    }
    @RequestMapping("path/exclude")
    public void exclude(){

    }
    @RequestMapping("outofpath")
    public void outofpath(){

    }
    @RequestMapping("introduce")
    public void introduce(){

    }
    @RequestMapping("automatic")
    public void automatic(){

    }
    @RequestMapping("nonautomatic")
    public void nonautomatic(){

    }
}

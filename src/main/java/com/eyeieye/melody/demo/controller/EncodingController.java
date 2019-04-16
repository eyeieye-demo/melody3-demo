package com.eyeieye.melody.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("encoding")
public class EncodingController {


    @RequestMapping("utf8")
    public void utf8(){

    }
    @RequestMapping("gbk")
    public void gbk(){

    }
}

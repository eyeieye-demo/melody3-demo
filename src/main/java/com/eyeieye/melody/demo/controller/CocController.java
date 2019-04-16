package com.eyeieye.melody.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("coc")
public class CocController {

    @RequestMapping("nolayout/nolayout")
    private void noLayout(){

    }

    @RequestMapping("layout/use_default.htm")
    private void usedefault(){

    }
    @RequestMapping("layout/custom.htm")
    private void custom(){

    }
    @RequestMapping("layout/samepath.htm")
    private void samepath(){

    }
    @RequestMapping("hasdefault/use_local_default.htm")
    private void use_default(){

    }
}


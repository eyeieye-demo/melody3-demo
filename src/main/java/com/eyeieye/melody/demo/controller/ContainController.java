package com.eyeieye.melody.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@RequestMapping("contain")
public class ContainController {

    @RequestMapping("contain_htm")
    private void containHtm(){

    }

    @RequestMapping("content")
    private void content(){

    }
    @RequestMapping("copyright")
    private void copyright(){

    }
    @RequestMapping("remote")
    private void remote(ModelMap modelMap){

        modelMap.put("date",format.format( Calendar.getInstance().getTime() ));
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ms");
    @RequestMapping("lazy")
    private void lazy(ModelMap modelMap){

        modelMap.put("date",format.format( Calendar.getInstance().getTime() ));
    }
    @RequestMapping("sleep")
    @ResponseBody
    private String sleep(int sec) throws InterruptedException {
        Thread.sleep(sec*1000);
        return sec+" im back!";
    }

}

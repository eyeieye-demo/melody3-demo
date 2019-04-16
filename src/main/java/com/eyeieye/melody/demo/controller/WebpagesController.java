package com.eyeieye.melody.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eyeieye.melos.util.uuid.RandomShortUUID;
import com.eyeieye.melos.util.uuid.UUIDGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("webpages")
public class WebpagesController {

    @RequestMapping("thymeleaf")
    public void thymeleaf(){

    }
    @RequestMapping("contain")
    public void contain(){

    }
    @RequestMapping("reload")
    public void reload(){

    }
    @RequestMapping("lazy")
    public void lazy(){

    }
    @RequestMapping("vpage")
    public void vpage(){

    }
    @RequestMapping("widget/base")
    public void base(){

    }
    @RequestMapping("widget/set_param")
    public void set_param(){

    }

    @Autowired
    UUIDGenerator uuidGenerator;
    @RequestMapping("widget/block")
    public void block(ModelMap modelMap){
        modelMap.put("id",uuidGenerator.gain());
    }
    @RequestMapping("widget/block/a")
    public void a(ModelMap modelMap){
        modelMap.put("id",uuidGenerator.gain());
    }
    @RequestMapping("widget/block/b")
    public void b(ModelMap modelMap){
        modelMap.put("id",uuidGenerator.gain());
    }
    @RequestMapping("widget/block/c")
    public void c(ModelMap modelMap){
        modelMap.put("id",uuidGenerator.gain());
    }
    @RequestMapping("widget/block/d")
    public void d(ModelMap modelMap){
        modelMap.put("id",uuidGenerator.gain());
    }
    @RequestMapping(value = "introduce_blank",method = RequestMethod.GET)
    public void webpagesBlankIntro(ModelMap modelMap){
        modelMap.put("selected","webpages");
    }
    @RequestMapping(value = "introduce",method = RequestMethod.GET)
    public void webpagesIntro(ModelMap modelMap){
        modelMap.put("selected","webpages");
    }

    @RequestMapping("widget/show_name")
    public void  show_name(String my_name, ModelMap modelMap){
        modelMap.put("my_name",my_name);
    }


    @RequestMapping("widget/reload.htm")
    public void show_name(ModelMap modelMap){

        modelMap.put("time", RandomShortUUID.get());
    }


    /** FIXME: 待改进 **/
    @RequestMapping("demo/local_*")
    public void localDemo(ModelMap modelMap){

    }
    @RequestMapping("demo/remote_*")
    public void remoteDemo(ModelMap modelMap){

    }

    @RequestMapping("demo/contained_*")
    public void containedPage(ModelMap modelMap){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SS");
        String loadTime = formatter.format(new Date());
        modelMap.put("loadTime",loadTime);
    }

}

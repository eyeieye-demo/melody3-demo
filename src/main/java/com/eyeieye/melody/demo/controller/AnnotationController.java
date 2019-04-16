package com.eyeieye.melody.demo.controller;


import com.eyeieye.melos.web.url.URLBroker;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author fish
 *
 */
@Controller
@RequestMapping("/spring/annotation")
public class AnnotationController {

    @Autowired
    private URLBroker appServer;


    @RequestMapping("/return/main_page.htm")
    public void main_page(ModelMap model) {
        model.addAttribute("currentTime", new Date());
    }

    @RequestMapping("/param/main_page.htm")
    public void param_page(ModelMap model) {
        model.addAttribute("currentTime", new Date());
    }



    /**
     * 方法返回void,则根据url寻找视图，此例子中，寻找名称为"annotation/return/void"的 view
     */
    @RequestMapping("/return/void.htm")
    public void annotationVoid(ModelMap model) {
        model.addAttribute("currentTime", new Date());
    }

    /**
     * 方法返回String,则根据返回值寻找视图，此例子中，寻找名称为"annotation/return/forward"的 view
     */
    @RequestMapping("/return/string_forward.htm")
    public String annotationString(ModelMap modelMap) {
        //map.put("currentTime", new Date());
        modelMap.put("currentTime",new Date());
        return "/spring/annotation/return/forward";
    }

    @RequestMapping("/return/string_redirect.htm")
    public String annotationStringRedirect(ModelMap map) {
        map.put("currentTime", new Date());
        return "redirect:"+appServer.get("/spring/annotation/return/redirect.htm");
        /**不用完整URL同样可行**/
        //return "redirect:/spring/annotation/return/redirect.htm");
    }

    @RequestMapping("/return/redirect")
    public void redirectPage(){}


    /**
     * 方法返回view,则根据url寻找视图，此例子中，寻找名称为"annotation/return/model_view"的 view
     */
    @RequestMapping("/return/view.htm")
    public ModelAndView annotationModelAndView() {
        ModelAndView mv = new ModelAndView("/spring/annotation/return/model_view");
        mv.addObject("currentTime", new Date());
        return mv;
    }

    /**
     * 方法返回Object(非String,int,long等),则把返回对象解析成json返回
     */
    @RequestMapping("/return/json")
    public @ResponseBody
    West annotationJson() {
        West w = new West();
        w.setAge(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())) - 1867);
        w.setName("DIO BRANDO");
        w.setNick("DIO");
        return w;
    }

    @RequestMapping("return/response_body")
    public void responseBodyPage(){}

    @RequestMapping("/param/base.htm")
    public void annotationBase(HttpServletRequest request,
                               @RequestParam String name,
                               @RequestParam(required = false, defaultValue = "1") int size,
                               Model model) {
        String ip = request.getRemoteAddr();
        model.addAttribute("ip", ip);
        model.addAttribute("name", name);
        model.addAttribute("size", size);
    }

    public static class WestJsonSerializer extends JsonSerializer<Integer> {

        @Override
        public void serialize(Integer west, JsonGenerator jg,
                              SerializerProvider sp) throws IOException,
                JsonProcessingException {
            jg.writeNumber(west + 19);
        }
    }

    public static class West implements Serializable {

        private String name;
        private String nick;

        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        @JsonSerialize(using = WestJsonSerializer.class)
        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    @RequestMapping("/param/simple_object_bind.htm")
    public void annotationObjectBind(West obj, Model model) {
        model.addAttribute("obj", obj);
    }

    @RequestMapping("/param/spring_object_bind.htm")
    public String springObjectBind(@ModelAttribute("west") West west, Model model) {
        model.addAttribute("west", west);
        return "/spring/annotation/param/spring_object_bind";
    }
    @RequestMapping("/param/json")
    public @ResponseBody
    West annotationJsonBind(@RequestBody West west) {
        west.name = west.name;
        west.nick = west.nick;
        west.age = west.age += 250;
        return west;
    }

    @RequestMapping("/param/json_object_bind")
    public void annotationJsonBindPage(ModelMap modelMap){}

    @RequestMapping("/param/RESTful")
    public void restfulPage(){}

    @RequestMapping("/movie/{movieName}/{shipId}.htm")
    public String annotationRestBind(
            @PathVariable("movieName") String moveName,
            @PathVariable("shipId") long shipId, Model model) {
        model.addAttribute("movieName", moveName);
        model.addAttribute("shipId", shipId);
        return "spring/annotation/param/RESTful";
    }

    @RequestMapping("introduce")
    public void introducePage(){}
}

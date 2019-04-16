package com.eyeieye.melody.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("introduce/nosession")
public class IntroduceNosessionController {

	@RequestMapping("*")
	public void main(){

	}

}

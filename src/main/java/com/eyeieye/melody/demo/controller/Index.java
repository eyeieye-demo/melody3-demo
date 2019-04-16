package com.eyeieye.melody.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {

	@RequestMapping("/index")
	public void index(ModelMap modelMap) {
		modelMap.put("url", "test.htm");
	}

	@RequestMapping("/test")
	public void test(ModelMap modelMap) {
		modelMap.put("abc", "abc");
		System.out.println("test");
	}

	@RequestMapping("/test1")
	public void test1(ModelMap modelMap) {
		modelMap.put("abc", "abc");
		System.out.println("test1");
	}
	@RequestMapping("/demo/test")
	public void demotest(ModelMap modelMap) {
		modelMap.put("abc", "abc");
		System.out.println("test1");
	}

	@RequestMapping("/")
	public String home(ModelMap modelMap) {
		return "index";
	}


}

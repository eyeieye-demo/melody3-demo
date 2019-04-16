package com.eyeieye.melody.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eyeieye.melody.demo.domain.User;
import com.eyeieye.melody.demo.domain.UserGenderEnum;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/{uuid}")
	public String getUser(@PathVariable String uuid, Model model) {

		model.addAttribute("user", new User(uuid, "张三", 20, UserGenderEnum.MALE,
				"中国广州"));
		return "/user/detail";
	}

	@RequestMapping("/list")
	public String listUser(Model model) {
		List<User> userList = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			userList.add(new User(String.valueOf(i), "张三" + i, 20 + i, UserGenderEnum.MALE, "中国广州"));
		}

		model.addAttribute("users", userList);
		return "/user/list";
	}
}
package com.eyeieye.melody.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author fish
 *
 */
@Controller
@RequestMapping("/upload/")
public class UploadAction {

	@RequestMapping(value = "simple.htm", method = RequestMethod.GET)
	public void show() {

	}

	@RequestMapping(value = "simple.htm", method = RequestMethod.POST)
	public void simple(@RequestParam("upfile") MultipartFile upfile,
			ModelMap model) {
		String originalFilename = upfile.getOriginalFilename();
		long length = upfile.getSize();
		model.addAttribute("originalFilename", originalFilename);
		model.addAttribute("length", length);
	}
}

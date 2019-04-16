package com.eyeieye.melody.demo.service;

import org.springframework.stereotype.Component;

@Component
public class TestImpl implements Test {

	@Override
	public String a() {
		return "abc";
	}

}

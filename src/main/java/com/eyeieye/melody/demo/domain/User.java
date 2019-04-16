package com.eyeieye.melody.demo.domain;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7137773844519796164L;

	private String uuid;
	private String name;
	private int age;
	private UserGenderEnum gender;
	private String address;

	public User() {

	}

	public User(String uuid, String name, int age, UserGenderEnum gender, String address) {
		this.uuid = uuid;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
	}
	
	public UserGenderEnum getGender() {
		return gender;
	}

	public void setGender(UserGenderEnum gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String id) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

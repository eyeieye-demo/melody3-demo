package com.eyeieye.melody.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.eyeieye.melody.demo.domain.User;
import com.eyeieye.melody.demo.domain.UserGenderEnum;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM users")
	@Results({
			@Result(property = "sex", column = "user_sex", javaType = UserGenderEnum.class),
			@Result(property = "name", column = "name") })
	List<User> getAll();

	@Insert("INSERT INTO users(id,name,age,user_sex) VALUES(#{id}, #{name},#{age}, #{sex})")
	void insert(User user);

}

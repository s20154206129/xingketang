package com.chz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chz.entity.Teacher;
import com.chz.entity.User;


public interface UserService {
	
	public void addUser(User user);

	public User getUser(String userName, String password);

	public User checkUserByName(String userName);
    
	//增加用户信息
	public void addTeacher(Teacher teacher);

	public Teacher getTeacherByName(String tname);

	//查找用户详细信息  名字懒的改
	public Teacher getTeacherById(int id);

	public void updateTeacher(Teacher userInformation);

	
}

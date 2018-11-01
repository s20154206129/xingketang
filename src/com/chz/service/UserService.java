package com.chz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chz.entity.Teacher;
import com.chz.entity.User;


public interface UserService {
	
	public void addUser(User user);

	public User getUser(String userName, String password);

	public User checkUserByName(String userName);
    
	//�����û���Ϣ
	public void addTeacher(Teacher teacher);

	public Teacher getTeacherByName(String tname);

	//�����û���ϸ��Ϣ  �������ĸ�
	public Teacher getTeacherById(int id);

	public void updateTeacher(Teacher userInformation);

	
}

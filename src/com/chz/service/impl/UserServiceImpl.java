package com.chz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chz.dao.UserDao;
import com.chz.entity.Teacher;
import com.chz.entity.User;
import com.chz.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	public UserDao dao;

     @Override
    public void addUser(User user) {
    	 //用户登录注册表
    	dao.saveUser(user);
     	System.out.println("---------------------------");
    	int  id=dao.getLastId();
    	System.out.println("最新的Id 是："+id);
    	
    	 //向用户详细表插入新用户的id 
    	dao.addUserInformationId(id);
    	
    }

	@Override
	public User getUser(String userName, String password) {
		 return   dao.findUser(userName,password);
	}

    @Override
    public User checkUserByName(String userName) {
    	 return     dao.checkUserByName(userName);
    }
    
    
    
    /**
     * 添加教师
     */
    @Override
    public void addTeacher(Teacher teacher) {
    	dao.addTeacher(teacher);
    }
    
    /**
     * 查找教师
     */
    @Override
    public Teacher getTeacherByName(String tname) {
    	return dao.findTeacherByName(tname);
    }
    
    
    
    @Override
    public Teacher getTeacherById(int id) {
    	return dao.getTeacherById(id);
    }
    
    //完善用户信息
    @Override
    public void updateTeacher(Teacher userInformation) {
    	dao.updateTeacher(userInformation);
    }
    
    
    
    
    
}

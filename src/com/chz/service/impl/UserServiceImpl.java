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
    	 //�û���¼ע���
    	dao.saveUser(user);
     	System.out.println("---------------------------");
    	int  id=dao.getLastId();
    	System.out.println("���µ�Id �ǣ�"+id);
    	
    	 //���û���ϸ��������û���id 
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
     * ��ӽ�ʦ
     */
    @Override
    public void addTeacher(Teacher teacher) {
    	dao.addTeacher(teacher);
    }
    
    /**
     * ���ҽ�ʦ
     */
    @Override
    public Teacher getTeacherByName(String tname) {
    	return dao.findTeacherByName(tname);
    }
    
    
    
    @Override
    public Teacher getTeacherById(int id) {
    	return dao.getTeacherById(id);
    }
    
    //�����û���Ϣ
    @Override
    public void updateTeacher(Teacher userInformation) {
    	dao.updateTeacher(userInformation);
    }
    
    
    
    
    
}

package com.chz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.chz.entity.Teacher;
import com.chz.entity.User;
@Repository
public interface UserDao {
	
	public List<User> getUserAll(User user);

	public User getUserByUser(User user);

	public void saveUser(@Param("user")User user);

	public User findUser(@Param("userName")String userName,@Param("password") String password);

	public User checkUserByName(@Param("userName")String userName);

	public void addTeacher(@Param("t")Teacher teacher);

	public Teacher findTeacherByName(@Param("tname")String tname);

	public Teacher getTeacherById(@Param("tid")int id);

	public void  updateTeacher(@Param("t")Teacher userInformation);

	public int getLastId();

	public void addUserInformationId(@Param("id")int id);

	
	
}

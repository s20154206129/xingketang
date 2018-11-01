package com.chz.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.chz.entity.Courses;
import com.chz.entity.Teacher;
import com.chz.entity.User;
import com.chz.service.CourseService;
import com.chz.service.UserService;

/**
 * struts2 最讨厌的是     乱映射
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller
public class RegisterAction extends  BaseAction  {
	@Autowired
	private  UserService  userService;
    
	private User user;
    
	//实际上是   用户信息
	private  Teacher   userInformation;
	
	private File userPhoto;
	private String   userPhotoFileName;
	
	
	
	public String getUserPhotoFileName() {
		return userPhotoFileName;
	}
	public void setUserPhotoFileName(String userPhotoFileName) {
		this.userPhotoFileName = userPhotoFileName;
	}
	public File getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(File userPhoto) {
		this.userPhoto = userPhoto;
	}
	public Teacher getUserInformation() {
		return userInformation;
	}
	public void setUserInformation(Teacher userInformation) {
		this.userInformation = userInformation;
	}




	@Autowired
	private  CourseService courseService;
    private  List<Courses>   courseList=new ArrayList<>();
    
    
	public List<Courses> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Courses> courseList) {
		this.courseList = courseList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * 注册
	 * register.action
	 * @return
	 * @throws Exception
	 */
	
	
 @Action(value ="register", results = {
				@Result(name = "success", type = "redirect", location = "/index.jsp") })
	public String  register()  throws Exception {
        System.out.println("注册");
		userService.addUser(user);
		return SUCCESS;
	}
	
    public String  login()  throws Exception {
		  User user2=userService.getUser(user.getUserName(),user.getPassword());
		  System.out.println(user2);
		if(user2!=null){   //登录成功
			 System.out.println("登录成功");
			 user=user2;
			 getHttpSession().put("user", user);
			 int  currentPage=0;
			 int  pageSize=8;
			 courseList=courseService.getCoursesInfomation(currentPage, pageSize);
		}else{
			 getHttpRequest().setAttribute("msg", "账号或密码不正确");
			 System.out.println("登录失败");
			 return ERROR;
		}
		return INPUT;
	}
    /**
    * 注销
    */
    public String  loginOut()  throws Exception {
    	System.out.println("进入了注销");
		getHttpSession().remove("user");
		return SUCCESS;
	}
    
    /**
     *  点击了用户后  跳转到用户信息界面
     *  userInformaction.action
     */
    @Action(value ="userInformaction", results = {
			@Result(name = "userInformation", type = "dispatcher", location = "/WEB-INF/jsp/user/userInformation.jsp") })
    public String goUserInformation(){
    	  //通过用户id  查找用户的具体信息
    	  int id=Integer.parseInt(getHttpRequest().getParameter("id"));
    	  System.out.println("用户id： "+id);
    	  userInformation= userService.getTeacherById(id);
		  return "userInformation";
    }
    
    
    
    
    /**
     *  用户信息完善
     *  
     */
    @Action(value ="userInfo", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/user/userInformation.jsp"), 
			@Result(name = "input", type = "dispatcher", location = "/index.jsp") 
    })
    public String writeUserInformation(){
		System.out.println("文件名称 "+userPhotoFileName);
		User user=(User) getHttpSession().get("user");
		if(user==null){
			return  "input";
		}
		//增加用户信息
		//判断是否刷新  
		//内容不为空
		
		if(!userInformation.getTname().equals(" ")){
			//删除之前的用户图片
			//更换头像
			if(userPhoto!=null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/img");//不要把图片 保存在临时 目录下 
	        File file2 = new File(realPath);
	        File local = new File("D:\\DevCloud2\\xing\\WebContent\\img");
	        if(!file2.exists())
	        	file2.mkdirs();
	        if(!local.exists())
	        	local.mkdirs();
	        try {
	            //保存文件 服务器的临时目录
	            FileUtils.copyFile(userPhoto, new File(file2,userPhotoFileName));
	            //保存在项目中
	            FileUtils.copyFile(userPhoto, new File(local,userPhotoFileName));
	          } catch (IOException e) {
	            e.printStackTrace();
	          }
	           userInformation.setTeacherimg("img/"+userPhotoFileName);
			}else{   //没有上传头像
				//再次从数据库中  取出 头像
				Teacher teacher=userService.getTeacherById(user.getId());
				userInformation.setTeacherimg(teacher.getTeacherimg());
			}
			userInformation.setId(user.getId());
			userService.updateTeacher(userInformation);
		}
    	return SUCCESS;
    }
}  

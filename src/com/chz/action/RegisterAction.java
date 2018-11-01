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
 * struts2 ���������     ��ӳ��
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller
public class RegisterAction extends  BaseAction  {
	@Autowired
	private  UserService  userService;
    
	private User user;
    
	//ʵ������   �û���Ϣ
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
	 * ע��
	 * register.action
	 * @return
	 * @throws Exception
	 */
	
	
 @Action(value ="register", results = {
				@Result(name = "success", type = "redirect", location = "/index.jsp") })
	public String  register()  throws Exception {
        System.out.println("ע��");
		userService.addUser(user);
		return SUCCESS;
	}
	
    public String  login()  throws Exception {
		  User user2=userService.getUser(user.getUserName(),user.getPassword());
		  System.out.println(user2);
		if(user2!=null){   //��¼�ɹ�
			 System.out.println("��¼�ɹ�");
			 user=user2;
			 getHttpSession().put("user", user);
			 int  currentPage=0;
			 int  pageSize=8;
			 courseList=courseService.getCoursesInfomation(currentPage, pageSize);
		}else{
			 getHttpRequest().setAttribute("msg", "�˺Ż����벻��ȷ");
			 System.out.println("��¼ʧ��");
			 return ERROR;
		}
		return INPUT;
	}
    /**
    * ע��
    */
    public String  loginOut()  throws Exception {
    	System.out.println("������ע��");
		getHttpSession().remove("user");
		return SUCCESS;
	}
    
    /**
     *  ������û���  ��ת���û���Ϣ����
     *  userInformaction.action
     */
    @Action(value ="userInformaction", results = {
			@Result(name = "userInformation", type = "dispatcher", location = "/WEB-INF/jsp/user/userInformation.jsp") })
    public String goUserInformation(){
    	  //ͨ���û�id  �����û��ľ�����Ϣ
    	  int id=Integer.parseInt(getHttpRequest().getParameter("id"));
    	  System.out.println("�û�id�� "+id);
    	  userInformation= userService.getTeacherById(id);
		  return "userInformation";
    }
    
    
    
    
    /**
     *  �û���Ϣ����
     *  
     */
    @Action(value ="userInfo", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/user/userInformation.jsp"), 
			@Result(name = "input", type = "dispatcher", location = "/index.jsp") 
    })
    public String writeUserInformation(){
		System.out.println("�ļ����� "+userPhotoFileName);
		User user=(User) getHttpSession().get("user");
		if(user==null){
			return  "input";
		}
		//�����û���Ϣ
		//�ж��Ƿ�ˢ��  
		//���ݲ�Ϊ��
		
		if(!userInformation.getTname().equals(" ")){
			//ɾ��֮ǰ���û�ͼƬ
			//����ͷ��
			if(userPhoto!=null){
			String realPath=ServletActionContext.getServletContext().getRealPath("/img");//��Ҫ��ͼƬ ��������ʱ Ŀ¼�� 
	        File file2 = new File(realPath);
	        File local = new File("D:\\DevCloud2\\xing\\WebContent\\img");
	        if(!file2.exists())
	        	file2.mkdirs();
	        if(!local.exists())
	        	local.mkdirs();
	        try {
	            //�����ļ� ����������ʱĿ¼
	            FileUtils.copyFile(userPhoto, new File(file2,userPhotoFileName));
	            //��������Ŀ��
	            FileUtils.copyFile(userPhoto, new File(local,userPhotoFileName));
	          } catch (IOException e) {
	            e.printStackTrace();
	          }
	           userInformation.setTeacherimg("img/"+userPhotoFileName);
			}else{   //û���ϴ�ͷ��
				//�ٴδ����ݿ���  ȡ�� ͷ��
				Teacher teacher=userService.getTeacherById(user.getId());
				userInformation.setTeacherimg(teacher.getTeacherimg());
			}
			userInformation.setId(user.getId());
			userService.updateTeacher(userInformation);
		}
    	return SUCCESS;
    }
}  

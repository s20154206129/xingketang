package com.chz.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.chz.entity.CourseGrade2;
import com.chz.entity.Courses;
import com.chz.entity.Teacher;
import com.chz.entity.User;
import com.chz.service.Course2Service;
import com.chz.service.CourseService;
import com.chz.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 上传课程
 * 
 * @author yyf
 *
 */
@Namespace("/upload")
@ParentPackage("struts-default")
@Controller
public class UploadAction extends BaseAction {

	@Autowired
	private  CourseService courseService;
	
	@Autowired
	private  UserService    userService;
	
	@Autowired
	private  Course2Service    course2Service;
	
	private File file;

    //上传的文件名。上传字段名称+FileName  注意大小写
	private String  fileFileName;
	//课程对象  
	private  Courses  coures;
   
	public Courses getCoures() {
		return coures;
	}

	public void setCoures(Courses coures) {
		this.coures = coures;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	/**
	 *   增加
	 *   课程信息
	 *   upload/add-coure
	 */

	@Action(value = "add-coure", results = {
			@Result(name = "success", type = "dispatcher",location="/WEB-INF/jsp/vedio/upload.jsp") })
	public String addCourse() throws Exception {
		  User  user=(User) getHttpSession().get("user");
		  if(user==null){
			  throw  new  Exception();
		  }
		
		 //添加课程
	     //判断是否是已经上传的课程
	     Courses  c=new Courses();
	    /**
	     *  浏览器  禁止加载本地路径   所以使用相对路径
	     *    D：//default.jpeg改成https://localhost:10104/default.jpg
	     *    相对路径也可以加载临时  目录下 照片
	     */
//	     String realPath=ServletActionContext.getServletContext().getRealPath("/img");
	     String rootPath="img/";
	     coures.setCourseimg(rootPath+coures.getCourseimg());
	     c=courseService.getCourseByImg(coures.getCourseimg());
	     
	     if(c==null){   //新课程添加
	       Teacher teacher=userService.getTeacherById(user.getId());
	       //课程设置教师信息
	       coures.setTeacher(teacher);
	       
	       //设置 2级别信息
	       CourseGrade2   courseGrade2=new  CourseGrade2();
	       courseGrade2=course2Service.getCourseGrade2ById(coures.getCourseGrade2().getId());
	       coures.setCourseGrade2(courseGrade2);
	       System.out.println("表单上交的内容： "+coures);
	       courseService.addCourse(coures);
	     }
		 return SUCCESS;
	}
	/*
	 * upload/uploadPhoto.action
	 *  异步提交上传数据
	 * */
	@Action(value = "uploadPhoto")
	public void getUploadPhoto() throws Exception { 
		//因为 eclipse 默认是临时目录下  一旦去掉部署 就全部消失
		String realPath=ServletActionContext.getServletContext().getRealPath("/img");//不要把图片 保存在临时 目录下 
        File file2 = new File(realPath);
        File local = new File("D:\\DevCloud2\\xing\\WebContent\\img");
        if(!file2.exists())
        	file2.mkdirs();
        if(!local.exists())
        	local.mkdirs();
        System.out.println("文件名称 fileFileName:"+ fileFileName);
        try {
            //保存文件 服务器的临时目录
            FileUtils.copyFile(file, new File(file2,fileFileName));
            //保存在项目中
            FileUtils.copyFile(file, new File(local,fileFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        
        
	}
	
	
}

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
 * �ϴ��γ�
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

    //�ϴ����ļ������ϴ��ֶ�����+FileName  ע���Сд
	private String  fileFileName;
	//�γ̶���  
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
	 *   ����
	 *   �γ���Ϣ
	 *   upload/add-coure
	 */

	@Action(value = "add-coure", results = {
			@Result(name = "success", type = "dispatcher",location="/WEB-INF/jsp/vedio/upload.jsp") })
	public String addCourse() throws Exception {
		  User  user=(User) getHttpSession().get("user");
		  if(user==null){
			  throw  new  Exception();
		  }
		
		 //��ӿγ�
	     //�ж��Ƿ����Ѿ��ϴ��Ŀγ�
	     Courses  c=new Courses();
	    /**
	     *  �����  ��ֹ���ر���·��   ����ʹ�����·��
	     *    D��//default.jpeg�ĳ�https://localhost:10104/default.jpg
	     *    ���·��Ҳ���Լ�����ʱ  Ŀ¼�� ��Ƭ
	     */
//	     String realPath=ServletActionContext.getServletContext().getRealPath("/img");
	     String rootPath="img/";
	     coures.setCourseimg(rootPath+coures.getCourseimg());
	     c=courseService.getCourseByImg(coures.getCourseimg());
	     
	     if(c==null){   //�¿γ����
	       Teacher teacher=userService.getTeacherById(user.getId());
	       //�γ����ý�ʦ��Ϣ
	       coures.setTeacher(teacher);
	       
	       //���� 2������Ϣ
	       CourseGrade2   courseGrade2=new  CourseGrade2();
	       courseGrade2=course2Service.getCourseGrade2ById(coures.getCourseGrade2().getId());
	       coures.setCourseGrade2(courseGrade2);
	       System.out.println("���Ͻ������ݣ� "+coures);
	       courseService.addCourse(coures);
	     }
		 return SUCCESS;
	}
	/*
	 * upload/uploadPhoto.action
	 *  �첽�ύ�ϴ�����
	 * */
	@Action(value = "uploadPhoto")
	public void getUploadPhoto() throws Exception { 
		//��Ϊ eclipse Ĭ������ʱĿ¼��  һ��ȥ������ ��ȫ����ʧ
		String realPath=ServletActionContext.getServletContext().getRealPath("/img");//��Ҫ��ͼƬ ��������ʱ Ŀ¼�� 
        File file2 = new File(realPath);
        File local = new File("D:\\DevCloud2\\xing\\WebContent\\img");
        if(!file2.exists())
        	file2.mkdirs();
        if(!local.exists())
        	local.mkdirs();
        System.out.println("�ļ����� fileFileName:"+ fileFileName);
        try {
            //�����ļ� ����������ʱĿ¼
            FileUtils.copyFile(file, new File(file2,fileFileName));
            //��������Ŀ��
            FileUtils.copyFile(file, new File(local,fileFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        
        
	}
	
	
}

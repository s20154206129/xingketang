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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.chz.entity.Chapter;
import com.chz.entity.Courses;
import com.chz.entity.Section;
import com.chz.entity.User;
import com.chz.service.CourseService;
import com.chz.service.VedioService;
import com.chz.utils.AliuyunUpload;

@Namespace("/vedio")
@ParentPackage("struts-default")
@Controller
public class VedioAction  extends   BaseAction{

	    @Autowired
	    private  VedioService  vedioService;
	    @Autowired
	    private  CourseService  courseService;
	    
	    private  File file;
	    private  String fileFileName;
	    private  List<Chapter>  chapterList=new ArrayList<>();
	    private  List<Courses>  courseList=new ArrayList<>();
	    private  int courseid;
	   
	    
	    
	    
	   public String getFileFileName() {
			return fileFileName;
		}
		public void setFileFileName(String fileFileName) {
			this.fileFileName = fileFileName;
		}
	public File getFile() {
			return file;
		}
		public void setFile(File file) {
			this.file = file;
		}
	public List<Courses> getCourseList() {
			return courseList;
		}
		public void setCourseList(List<Courses> courseList) {
			this.courseList = courseList;
		}
	/*���ݵ�����ҳ�濴�Ƿ���get set����*/
	   public List<Chapter> getChapterList() {
			return chapterList;
		}
		public void setChapterList(List<Chapter> chapterList) {
			this.chapterList = chapterList;
		}
	   public int getCourseid() {
			return courseid;
		}
		public void setCourseid(int courseid) {
			this.courseid = courseid;
		}




	    /**
	    * vedio/look.action
	    * ȥ����Ƶ
	    */
	    @Action(value = "look", results = {
				@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/vedio/vedio.jsp") })
		public  String goVedio(){
			System.out.println("����Ƶ  �γ�idΪ:"+courseid);
			//��øÿγ̵������½�
			chapterList=vedioService.getChapter(courseid);
			
		
			
			
			
			
			return SUCCESS;
		}
	    
	    
	    /**
	     * vedio/comment.action
	     * �Ը���Ƶ������
	     */
	    @Action(value = "comment", results = {
				@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/vedio/comment.jsp") })
	    public String  getComment(){
	    	//�����������
			return SUCCESS;
	    	
	    }
	    
	    
	    
	    /**
	     * ��ת���ϴ�������  ҳ��
	     * redirect
	     * vedio/goUpload.action
	     * web-inf ��ȫ�Կ��� ����  ת�� ֱ�ӵ�  
	     * �ض���  �� ��������ȡ��ҳ��  ��Ӧ��ȥ
	     * 
	     */
	    @Action(value = "goUpload", results = {
				@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/vedio/upload.jsp") })
		public  String goUpload(){
	    	System.out.println("��ת�ϴ��γ�ҳ��");
	    	return SUCCESS;
		}
	    
	    /**
	     * vedio/setupCourse.action
	     * ��ʦ���贴���γ�Ŀ¼
	     */
	    @Action(value = "setupCourse", results = {
				@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/vedio/createDirectories.jsp"),
				@Result(name = "input", type = "redirect", location = "/index.jsp")
	    })
		public  String createCourseDirectories(){
	        System.out.println("�γ�Ŀ¼");
	        User  user= (User) getHttpSession().get("user");
	        if(user==null){
	        	System.out.println("�û�SessionʧЧ");
	        	return "input";
	        }
		    courseList=courseService.getCourseByTid(user.getId());
	    	return SUCCESS;
		}
	    /**
	     *  vedio/myEsblishCourse.action
	     */
	    @Action(value = "myEsblishCourse", results = {
				@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/vedio/myEstablishCourse.jsp") })
		public  String myEsblishCourse(){
	        User  user= (User) getHttpSession().get("user");
	       courseList=courseService.getCourseByTid(user.getId());
	       System.out.println("��ʦ����Ŀγ�:" +courseList);
	        
	      return SUCCESS;
		}
	    
	    
	    /**
	     * vedio/uploadVedio.action
	     * ��ת��Ƶ�ϴ�
	     */
	    @Action(value = "uploadVedio", results = {
				@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/vedio/uploadVedio.jsp") })
		public  String uploadVedio(){
	    	User  user= (User) getHttpSession().get("user");
	        if(user==null){
	        	System.out.println("�û�SessionʧЧ");
	        	return "input";
	        }
		    courseList=courseService.getCourseByTid(user.getId()); //��ÿγ�
		    return SUCCESS;
		}
	    
	    /**
	     * ViedoText.action
	     * �ϴ���Ƶ
	     */
	    @Action(value = "ViedoText", results = {
				@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/vedio/uploadVedio.jsp") })
	    public String ViedoText() {
	    int cid=Integer.parseInt(getHttpRequest().getParameter("cid"));
	    int zid=Integer.parseInt(getHttpRequest().getParameter("zid"));	
	    int sid=Integer.parseInt(getHttpRequest().getParameter("sid"));	
	    System.out.println(cid+","+zid+","+sid);
	    	String realPath=ServletActionContext.getServletContext().getRealPath("/vedio");//��Ҫ��ͼƬ ��������ʱ Ŀ¼�� 
	    	File file2 = new File(realPath);
	        String localAddress="D:\\DevCloud2\\xing\\WebContent\\vedio";
	        File local = new File(localAddress);
	        
	        System.out.println("�ϴ������Ƶ���Ƶ��ַ :"+localAddress+"\\"+fileFileName);
	        //�ϴ��������Ʒ�����      ���ص�ַ  ����   cid  zid sid 
            AliuyunUpload.uploadAliyun(localAddress+"\\"+fileFileName,fileFileName,cid,zid,sid);
	        if(!file2.exists())
	        	file2.mkdirs();
	        if(!local.exists())
	        	local.mkdirs();
	        try {
	        	 //��������Ŀ��
	            FileUtils.copyFile(file, new File(local,fileFileName));
	            //�����ļ� ����������ʱĿ¼
	            // FileUtils.copyFile(file, new File(file2,fileFileName));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       
	    	return  SUCCESS;
        }
}

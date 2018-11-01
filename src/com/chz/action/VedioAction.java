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
	/*数据到不了页面看是否有get set方法*/
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
	    * 去看视频
	    */
	    @Action(value = "look", results = {
				@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/vedio/vedio.jsp") })
		public  String goVedio(){
			System.out.println("看视频  课程id为:"+courseid);
			//获得该课程的所有章节
			chapterList=vedioService.getChapter(courseid);
			
		
			
			
			
			
			return SUCCESS;
		}
	    
	    
	    /**
	     * vedio/comment.action
	     * 对该视频的评论
	     */
	    @Action(value = "comment", results = {
				@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/vedio/comment.jsp") })
	    public String  getComment(){
	    	//获得所有评论
			return SUCCESS;
	    	
	    }
	    
	    
	    
	    /**
	     * 跳转到上传视屏的  页面
	     * redirect
	     * vedio/goUpload.action
	     * web-inf 安全性考虑 不能  转发 直接到  
	     * 重定向  是 服务器读取了页面  响应回去
	     * 
	     */
	    @Action(value = "goUpload", results = {
				@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/vedio/upload.jsp") })
		public  String goUpload(){
	    	System.out.println("跳转上传课程页面");
	    	return SUCCESS;
		}
	    
	    /**
	     * vedio/setupCourse.action
	     * 教师开设创建课程目录
	     */
	    @Action(value = "setupCourse", results = {
				@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/vedio/createDirectories.jsp"),
				@Result(name = "input", type = "redirect", location = "/index.jsp")
	    })
		public  String createCourseDirectories(){
	        System.out.println("课程目录");
	        User  user= (User) getHttpSession().get("user");
	        if(user==null){
	        	System.out.println("用户Session失效");
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
	       System.out.println("教师开设的课程:" +courseList);
	        
	      return SUCCESS;
		}
	    
	    
	    /**
	     * vedio/uploadVedio.action
	     * 跳转视频上传
	     */
	    @Action(value = "uploadVedio", results = {
				@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/vedio/uploadVedio.jsp") })
		public  String uploadVedio(){
	    	User  user= (User) getHttpSession().get("user");
	        if(user==null){
	        	System.out.println("用户Session失效");
	        	return "input";
	        }
		    courseList=courseService.getCourseByTid(user.getId()); //获得课程
		    return SUCCESS;
		}
	    
	    /**
	     * ViedoText.action
	     * 上传视频
	     */
	    @Action(value = "ViedoText", results = {
				@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/vedio/uploadVedio.jsp") })
	    public String ViedoText() {
	    int cid=Integer.parseInt(getHttpRequest().getParameter("cid"));
	    int zid=Integer.parseInt(getHttpRequest().getParameter("zid"));	
	    int sid=Integer.parseInt(getHttpRequest().getParameter("sid"));	
	    System.out.println(cid+","+zid+","+sid);
	    	String realPath=ServletActionContext.getServletContext().getRealPath("/vedio");//不要把图片 保存在临时 目录下 
	    	File file2 = new File(realPath);
	        String localAddress="D:\\DevCloud2\\xing\\WebContent\\vedio";
	        File local = new File(localAddress);
	        
	        System.out.println("上传阿里云的视频地址 :"+localAddress+"\\"+fileFileName);
	        //上传到阿里云服务器      本地地址  标题   cid  zid sid 
            AliuyunUpload.uploadAliyun(localAddress+"\\"+fileFileName,fileFileName,cid,zid,sid);
	        if(!file2.exists())
	        	file2.mkdirs();
	        if(!local.exists())
	        	local.mkdirs();
	        try {
	        	 //保存在项目中
	            FileUtils.copyFile(file, new File(local,fileFileName));
	            //保存文件 服务器的临时目录
	            // FileUtils.copyFile(file, new File(file2,fileFileName));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       
	    	return  SUCCESS;
        }
}

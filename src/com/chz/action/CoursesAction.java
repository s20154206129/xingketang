package com.chz.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.chz.entity.Courses;
import com.chz.entity.PageBean;
import com.chz.entity.Teacher;
import com.chz.entity.User;
import com.chz.service.CourseService;
import com.chz.service.VedioService;

/**
 * 
 * @author 0000 course/information.action
 */

@Namespace("/course")
@ParentPackage("struts-default")
@Controller
public class CoursesAction extends BaseAction {

	private static final int DEFAULT_PAGESIZE = 5;

	@Autowired
	private CourseService courseService;

	@Autowired
	private VedioService vedioService;

	private List<Courses> courseList = new ArrayList<>();
	// ����ǰ̨ ������ courseId
	private int courseId;
	private Teacher teacher;
	private Courses courses;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	public List<Courses> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Courses> courseList) {
		this.courseList = courseList;
	}

	/**
	 * ������� �γ�����ҳ�� course/content.action courseIntroduction.jsp
	 * 
	 * @return
	 */

	@Action(value = "content", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/course/courseIntroduction.jsp") })
	public String goCourseContent() {
		System.out.println("�����˿γ��ڲ�   �γ���ϢΪ��");
		courses = courseService.getCourseById(courseId);
		return SUCCESS;
	}

	/**
	 * ������ҳ �γ���Ϣ ���� �Ƽ��İ˸��γ���Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */

	@Action(value = "information", results = {
			@Result(name = "success", type = "dispatcher", location = "/first.jsp") })
	public String getCoursesInfomation() throws Exception {
		System.out.println("������ҳ");
		// ������� ҳ�� ҳ��С limit 0,8 ȡͷ8������
		int currentPage = 0;
		int pageSize = 8;
		courseList = courseService.getCoursesInfomation(currentPage, pageSize);
		return SUCCESS;
	}

	/**
	 * course/search.action �γ�����
	 */
	@Action(value = "search", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/course/search-result.jsp"),
			@Result(name = "input", type = "dispatcher", location = "/first.jsp") })
	public String seacherInfo() throws Exception {
		String searchInfo = getHttpRequest().getParameter("searchInfo");
		courseList = courseService.getSearchResult(searchInfo);
		return SUCCESS;
	}

	/**
	 * ����ȫ�ļ��� ��ѯ�� �γ� ������� course/seacher-content.action
	 */
	@Action(value = "seacher-content", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/course/courseIntroduction.jsp"),
			@Result(name = "input", type = "dispatcher", location = "/first.jsp") })
	public String seacherCourseContent() throws Exception {
		System.out.println("seacher-content");
		String couresImg = getHttpRequest().getParameter("courseimg");
		courses = courseService.getCourseByImg(couresImg);
		return SUCCESS;
	}

	/**
	 * ������ҳ course/goMain.action course/information.action
	 */
	@Action(value = "goMain", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/main/main.jsp"),
			@Result(name = "input", type = "dispatcher", location = "/first.jsp") })
	public String goMain() throws Exception {
		int currentPage = 0;
		int pageSize = 8;
		courseList = courseService.getCoursesInfomation(currentPage, pageSize);
		return SUCCESS;
	}

	/**
	 * course/manage.action �γ̹���
	 */
	@Action(value = "manage", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/course/courseManager.jsp") })
	public String goCourseManager() throws Exception {
		int pageSize = DEFAULT_PAGESIZE;// Ĭ�ϴ�С
		int currentPage = Integer.parseInt(getHttpRequest().getParameter("currentPage"));
		// ��ҳ����
		PageBean pageBean = courseService.getCoursePageList(currentPage, pageSize);

		getHttpRequest().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}

	/**
	 * ɾ���γ� ת�������� course/delete.action?coureId ת���� action �Ѿ��� �������ռ� ����chain
	 * manage ���ü� ��׺
	 */
	@Action(value = "delete", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/course/courseManager.jsp") })
	public String deleteCourse() throws Exception {
		System.out.println("ɾ��id ��" + courseId);
		courseService.delete(courseId);
		getHttpRequest().setAttribute("currentPage", 1);

		int pageSize = DEFAULT_PAGESIZE;// Ĭ�ϴ�С
		int currentPage = 1;
		PageBean pageBean = courseService.getCoursePageList(currentPage, pageSize);
		getHttpRequest().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}

	/**
	 * �޸Ŀγ�Ȩ�� course/update.action?courseId
	 */
	@Action(value = "update", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/course/courseManager.jsp") })
	public String uploadCourse() throws Exception {
		int authority = Integer.parseInt(getHttpRequest().getParameter("authority"));
		System.out.println("id  ��" + courseId + ",Ȩ��" + authority);
		courseService.update(courseId, authority);
		getHttpRequest().setAttribute("currentPage", 1);
		int pageSize = DEFAULT_PAGESIZE;// Ĭ�ϴ�С
		int currentPage = 1;
		PageBean pageBean = courseService.getCoursePageList(currentPage, pageSize);
		getHttpRequest().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}

	/**
	 *   �ϴ��γ��½�
	 *   course/createSection.action
	 *   �ϴ��ɹ� ��ת������һ��action  ����vedio�����ռ�  �� setUpCourse
	 *   param������ת����ͬ�����ռ� 
	 *   type = "redirectAction"   Ĭ��һ��������һ��Action
	 */
	@Action(value ="createSection", results = {
		    @Result(name = "success",  type = "chain", params={"namespace","/vedio","actionName","setupCourse"} )
	  })
    public String createSection() throws Exception {
	    
	System.out.println("courseId:"+courseId);	
    //������
	String[] zitems=getHttpRequest().getParameterValues("zitem");
	
	//bug --- ���Լ�����ͬ���½�
	
	//  ��ȡ ���ݿ�  ���ڸÿγ����е� �� ��   ��Ȼ    ��������½�     zid��ͬ
	Integer  chapterId=vedioService.getChapterId(courseId);
	int  id=1;
	//���ݿ�û�������½�  Ĭ��Ϊ1
		if(chapterId!=null){
			id=chapterId.intValue()+1;
		}
		   System.out.println("id��"+id);
		    for(int i=1;i<=zitems.length;i++){
			String[] sitems=getHttpRequest().getParameterValues("sitem"+i);
			System.out.println("������ :"+zitems[i-1]);
			for(int j=0;j<sitems.length;j++)
			System.out.println(j+1+"�� :"+sitems[j]);
			//Ϊ�γ̲�����--�ַ���     ��---����    
			vedioService.addChapterSection(courseId,id,zitems[i-1],sitems);
			id++;
	}
		return SUCCESS;
	}

}

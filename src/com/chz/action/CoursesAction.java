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
	// 接受前台 传来的 courseId
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
	 * 点击进入 课程内容页面 course/content.action courseIntroduction.jsp
	 * 
	 * @return
	 */

	@Action(value = "content", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/course/courseIntroduction.jsp") })
	public String goCourseContent() {
		System.out.println("进入了课程内部   课程信息为：");
		courses = courseService.getCourseById(courseId);
		return SUCCESS;
	}

	/**
	 * 返回首页 课程信息 返回 推荐的八个课程信息
	 * 
	 * @return
	 * @throws Exception
	 */

	@Action(value = "information", results = {
			@Result(name = "success", type = "dispatcher", location = "/first.jsp") })
	public String getCoursesInfomation() throws Exception {
		System.out.println("进入首页");
		// 传入参数 页数 页大小 limit 0,8 取头8条数据
		int currentPage = 0;
		int pageSize = 8;
		courseList = courseService.getCoursesInfomation(currentPage, pageSize);
		return SUCCESS;
	}

	/**
	 * course/search.action 课程搜索
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
	 * 利用全文检索 查询的 课程 点击进入 course/seacher-content.action
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
	 * 跳到首页 course/goMain.action course/information.action
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
	 * course/manage.action 课程管理
	 */
	@Action(value = "manage", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/course/courseManager.jsp") })
	public String goCourseManager() throws Exception {
		int pageSize = DEFAULT_PAGESIZE;// 默认大小
		int currentPage = Integer.parseInt(getHttpRequest().getParameter("currentPage"));
		// 分页数据
		PageBean pageBean = courseService.getCoursePageList(currentPage, pageSize);

		getHttpRequest().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}

	/**
	 * 删除课程 转发到数据 course/delete.action?coureId 转发到 action 已经是 该命名空间 利用chain
	 * manage 不用加 后缀
	 */
	@Action(value = "delete", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/course/courseManager.jsp") })
	public String deleteCourse() throws Exception {
		System.out.println("删除id ：" + courseId);
		courseService.delete(courseId);
		getHttpRequest().setAttribute("currentPage", 1);

		int pageSize = DEFAULT_PAGESIZE;// 默认大小
		int currentPage = 1;
		PageBean pageBean = courseService.getCoursePageList(currentPage, pageSize);
		getHttpRequest().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}

	/**
	 * 修改课程权限 course/update.action?courseId
	 */
	@Action(value = "update", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/jsp/course/courseManager.jsp") })
	public String uploadCourse() throws Exception {
		int authority = Integer.parseInt(getHttpRequest().getParameter("authority"));
		System.out.println("id  ：" + courseId + ",权限" + authority);
		courseService.update(courseId, authority);
		getHttpRequest().setAttribute("currentPage", 1);
		int pageSize = DEFAULT_PAGESIZE;// 默认大小
		int currentPage = 1;
		PageBean pageBean = courseService.getCoursePageList(currentPage, pageSize);
		getHttpRequest().setAttribute("pageBean", pageBean);
		return SUCCESS;
	}

	/**
	 *   上传课程章节
	 *   course/createSection.action
	 *   上传成功 跳转到另外一个action  跳到vedio命名空间  下 setUpCourse
	 *   param参数跳转到不同命名空间 
	 *   type = "redirectAction"   默认一个跳到另一个Action
	 */
	@Action(value ="createSection", results = {
		    @Result(name = "success",  type = "chain", params={"namespace","/vedio","actionName","setupCourse"} )
	  })
    public String createSection() throws Exception {
	    
	System.out.println("courseId:"+courseId);	
    //所有章
	String[] zitems=getHttpRequest().getParameterValues("zitem");
	
	//bug --- 可以加入相同的章节
	
	//  获取 数据库  对于该课程已有的 章 数   不然    后面添加章节     zid相同
	Integer  chapterId=vedioService.getChapterId(courseId);
	int  id=1;
	//数据库没有已有章节  默认为1
		if(chapterId!=null){
			id=chapterId.intValue()+1;
		}
		   System.out.println("id："+id);
		    for(int i=1;i<=zitems.length;i++){
			String[] sitems=getHttpRequest().getParameterValues("sitem"+i);
			System.out.println("该章是 :"+zitems[i-1]);
			for(int j=0;j<sitems.length;j++)
			System.out.println(j+1+"节 :"+sitems[j]);
			//为课程插入章--字符串     节---数组    
			vedioService.addChapterSection(courseId,id,zitems[i-1],sitems);
			id++;
	}
		return SUCCESS;
	}

}

package com.chz.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.chz.entity.Chapter;
import com.chz.entity.Courses;
import com.chz.entity.User;
import com.chz.service.CourseService;
import com.chz.service.UserService;
import com.chz.service.VedioService;
import com.chz.utils.AliyunPlay;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 返回 json 字符串 即返回json数据
 * 
 * @author 0000
 */
@Namespace("/json")
@ParentPackage("json-default")
@Results({ @Result(name = "json", type = "json", params = { "root", "dataMap" }) })
@Controller
public class JsonAction extends BaseAction {

	// 接受前台传来的 用户名
	private String userName;

	private int courseId;

	private Map<String, Object> dataMap = new HashMap<String, Object>();

	private List<Chapter> chapterList = new ArrayList<>();

	private List<Courses> courseList = new ArrayList<>();

	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private VedioService vedioService;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Courses> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Courses> courseList) {
		this.courseList = courseList;
	}

	public List<Chapter> getChapterList() {
		return chapterList;
	}

	public void setChapterList(List<Chapter> chapterList) {
		this.chapterList = chapterList;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	/**
	 * 检查用户名唯一
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "check")
	public String userCheck() throws Exception {
		System.out.println("用户名唯一检查");
		System.out.println("------------------------------" + userName);
		User user = userService.checkUserByName(userName);
		System.out.println("----------" + user);
		if (user != null) {
			System.out.println("用户名已存在");
			dataMap.put("msg", "用户名已存在");
		} else {
			System.out.println("用户名可用");
			dataMap.put("msg", "用户名可用");
		}
		return "json";
	}

	/**
	 * 异步请求 课程章节 json/courseChater
	 */
	@Action(value = "courseChater")
	public String getCourseChater() throws Exception {
		// 这是获得章节
		chapterList = vedioService.getChapter(courseId);
		dataMap.put("chapterList", chapterList); // 自动转化为 strut2 json格式
		return "json";
	}

	// 获取阿里云 id 地址 和 凭证
	/**
	 * "json/vedioId.action"
	 */
	@Action(value = "vedioId")
	public String getVedioId() throws Exception {
		int cid = Integer.parseInt(getHttpRequest().getParameter("cid"));
		int zid = Integer.parseInt(getHttpRequest().getParameter("zid"));
		int sid = Integer.parseInt(getHttpRequest().getParameter("sid"));
		
		//调用阿里云 播放工具类   
	 String playAuth=AliyunPlay.getPlayAuth(cid, zid, sid);//播放凭证
	 String playURL= AliyunPlay.play(cid, zid, sid);  //播放地址
	 String vedioId=vedioService.getChapterAddress(cid, zid, sid) ;     //视频ID 从数据库
	
	 System.out.println("凭证 :"+playAuth);
	 System.out.println(",地址:"+playURL);
	 System.out.println(",voteID:"+vedioId);
	 dataMap.put("playAuth", playAuth);
	dataMap.put("playURL", playURL);
	dataMap.put("vedioId", vedioId);
	 return "json";
	}
}

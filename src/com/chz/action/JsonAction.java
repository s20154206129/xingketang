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
 * ���� json �ַ��� ������json����
 * 
 * @author 0000
 */
@Namespace("/json")
@ParentPackage("json-default")
@Results({ @Result(name = "json", type = "json", params = { "root", "dataMap" }) })
@Controller
public class JsonAction extends BaseAction {

	// ����ǰ̨������ �û���
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
	 * ����û���Ψһ
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "check")
	public String userCheck() throws Exception {
		System.out.println("�û���Ψһ���");
		System.out.println("------------------------------" + userName);
		User user = userService.checkUserByName(userName);
		System.out.println("----------" + user);
		if (user != null) {
			System.out.println("�û����Ѵ���");
			dataMap.put("msg", "�û����Ѵ���");
		} else {
			System.out.println("�û�������");
			dataMap.put("msg", "�û�������");
		}
		return "json";
	}

	/**
	 * �첽���� �γ��½� json/courseChater
	 */
	@Action(value = "courseChater")
	public String getCourseChater() throws Exception {
		// ���ǻ���½�
		chapterList = vedioService.getChapter(courseId);
		dataMap.put("chapterList", chapterList); // �Զ�ת��Ϊ strut2 json��ʽ
		return "json";
	}

	// ��ȡ������ id ��ַ �� ƾ֤
	/**
	 * "json/vedioId.action"
	 */
	@Action(value = "vedioId")
	public String getVedioId() throws Exception {
		int cid = Integer.parseInt(getHttpRequest().getParameter("cid"));
		int zid = Integer.parseInt(getHttpRequest().getParameter("zid"));
		int sid = Integer.parseInt(getHttpRequest().getParameter("sid"));
		
		//���ð����� ���Ź�����   
	 String playAuth=AliyunPlay.getPlayAuth(cid, zid, sid);//����ƾ֤
	 String playURL= AliyunPlay.play(cid, zid, sid);  //���ŵ�ַ
	 String vedioId=vedioService.getChapterAddress(cid, zid, sid) ;     //��ƵID �����ݿ�
	
	 System.out.println("ƾ֤ :"+playAuth);
	 System.out.println(",��ַ:"+playURL);
	 System.out.println(",voteID:"+vedioId);
	 dataMap.put("playAuth", playAuth);
	dataMap.put("playURL", playURL);
	dataMap.put("vedioId", vedioId);
	 return "json";
	}
}

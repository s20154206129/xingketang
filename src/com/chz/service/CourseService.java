package com.chz.service;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import com.chz.entity.Courses;
import com.chz.entity.PageBean;

public interface CourseService {

	public List<Courses> getCoursesInfomation(int  currentPage,int  pageSize);

	/**
	 * 根据id  找到课程
	 * @param courseId
	 * @return
	 */
	public Courses getCourseById(int courseId);

	public void addCourse(Courses coures)  throws IOException;

	/**
	 *  判断是否 已经上传过同样课程
	 * @param courseimg
	 * @return
	 */
	public Courses getCourseByImg(String courseimg);


	public List<Courses> getSearchResult(String searchInfo) throws IOException, ParseException ;

	public  PageBean  getCoursePageList(int currentPage, int pageSize);

	public void delete(int courseId)  throws Exception;

	public void update(int courseId,int authority);

	//查找教师开设的课程
	public List<Courses> getCourseByTid(int id);

}

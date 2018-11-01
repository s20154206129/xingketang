package com.chz.service;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import com.chz.entity.Courses;
import com.chz.entity.PageBean;

public interface CourseService {

	public List<Courses> getCoursesInfomation(int  currentPage,int  pageSize);

	/**
	 * ����id  �ҵ��γ�
	 * @param courseId
	 * @return
	 */
	public Courses getCourseById(int courseId);

	public void addCourse(Courses coures)  throws IOException;

	/**
	 *  �ж��Ƿ� �Ѿ��ϴ���ͬ���γ�
	 * @param courseimg
	 * @return
	 */
	public Courses getCourseByImg(String courseimg);


	public List<Courses> getSearchResult(String searchInfo) throws IOException, ParseException ;

	public  PageBean  getCoursePageList(int currentPage, int pageSize);

	public void delete(int courseId)  throws Exception;

	public void update(int courseId,int authority);

	//���ҽ�ʦ����Ŀγ�
	public List<Courses> getCourseByTid(int id);

}

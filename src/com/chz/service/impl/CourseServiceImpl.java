package com.chz.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chz.entity.Courses;
import com.chz.entity.PageBean;
import com.chz.lucene.CreateIndex;
import com.chz.dao.CoursesDao;
import com.chz.service.CourseService;
import com.chz.utils.FileUtil;


@Service("courseService")
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CoursesDao courseDao;

	private CreateIndex ci = new CreateIndex();
	@Override
	public List<Courses> getCoursesInfomation(int currentPage, int pageSize) {

		return courseDao.findCourseInformation(currentPage, pageSize);
	}

	@Override
	public Courses getCourseById(int courseId) {

		return courseDao.findCourseById(courseId);
	}

	/**
	 * ��ӿγ�
	 * @throws IOException 
	 */
	@Override
	public void addCourse(Courses coures) throws IOException {
	 
		courseDao.saveCourse(coures);
		//���������ӵ�����  �� id
		int id=courseDao.getLastId();
		coures.setId(id);
		ci.addIndex(coures); 
	}

	@Override
	public Courses getCourseByImg(String courseimg) {
		return courseDao.findCourseByImg(courseimg);
	}

	/**
	 * ��ѯ���
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@Override
	public List<Courses> getSearchResult(String searchInfo) throws IOException, ParseException {
		return ci.getDocSearchList(searchInfo);
	}
	
	/**
	 * �γ̹���
	 * 
	 */
	@Override
	public  PageBean getCoursePageList(int currentPage, int pageSize) {
		//���ݿ� ����
		int count = courseDao.getCount();
		int totalPage = (int) Math.ceil(count * 1.0 / pageSize);// 
		
		//һҳ����
		List<Courses> courseList = courseDao.findCoursePageList(
				(currentPage - 1) * pageSize, pageSize);
		PageBean pageBean = new PageBean(currentPage, pageSize, count,
				totalPage, courseList);
		return pageBean;
	}

	@Override
	public void delete(int courseId) throws Exception {
		//ɾ���γ�      ��ȻҲҪɾ����Ӧ��ͼƬ
		Courses doc= courseDao.findCourseById(courseId);
			File file = new File(FileUtil.RootPath+doc.getCourseimg());
			FileUtil.delete(file);
	    //ɾ���γ�   ɾ���γ̵�����
	    ci.deleteById(courseId);
	    courseDao.delete(courseId);
	}
	
	
	@Override
	public void update(int courseId,int authority) {
		   courseDao.update(courseId,authority);
	}
	
	
	@Override
	public List<Courses> getCourseByTid(int id) {
		return courseDao.findCourseByTid(id);
	}

	
}

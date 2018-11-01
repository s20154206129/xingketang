package com.chz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chz.dao.CourseGrade2Dao;
import com.chz.dao.CoursesDao;
import com.chz.entity.CourseGrade2;
import com.chz.service.Course2Service;

@Service("course2Service")
public class Course2ServiceImpl implements Course2Service {
	
	@Autowired
	private   CourseGrade2Dao course2Dao;
	
	@Override
	public CourseGrade2 getCourseGrade2ById(int id) {
		return course2Dao.getCourseGrade2ById(id);
	}
}

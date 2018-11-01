package com.chz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chz.entity.Courses;
import com.chz.entity.PageBean;

public interface CoursesDao {
   
	
	public List<Courses> findCourseInformation(@Param("currentPage")int  currentPage,@Param("pageSize")int pageSize);

	public Courses findCourseById(@Param("courseId")int courseId);

	public void saveCourse(@Param("coures")Courses coures);

	public Courses findCourseByImg(@Param("courseimg")String courseimg);

	public  List<Courses> findCoursePageList(@Param("currentPage")int currentPage,@Param("pageSize") int pageSize);
  
	 
	public int getCount();

	public void delete(@Param("courseId")int courseId);

	public int getLastId();

	public void update(@Param("courseId")int courseId,@Param("authority")int authority);

	public List<Courses> findCourseByTid(@Param("tid")int id);

}

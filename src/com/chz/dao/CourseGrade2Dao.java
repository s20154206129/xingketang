package com.chz.dao;

import org.apache.ibatis.annotations.Param;

import com.chz.entity.CourseGrade2;

public interface CourseGrade2Dao {

	CourseGrade2 getCourseGrade2ById(@Param("secondid")int id);

}

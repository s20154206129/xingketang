package com.chz.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Courses {
      private int id;
      private String  coursename;
      private String  coursedesc;
      private String  courseimg;
      //课程权限
      private  String  coureAuthority;
      
      
       //与其难转格式    直接定义成String 最好
      private  Date    starttime;
      private  String  couretime;
      private  int  studynum;
      private  String  level;
    //课程老师
      private  Teacher   teacher;
      private  CourseGrade2  courseGrade2; //课程   第2分类
      
      
      
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public String getCouretime() {
		return couretime;
	}
	public void setCouretime(String couretime) {
		this.couretime = couretime;
	}
	public int getStudynum() {
		return studynum;
	}
	public void setStudynum(int studynum) {
		this.studynum = studynum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCoureAuthority() {
		return coureAuthority;
	}
	public void setCoureAuthority(String coureAuthority) {
		this.coureAuthority = coureAuthority;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getCoursedesc() {
		return coursedesc;
	}
	public void setCoursedesc(String coursedesc) {
		this.coursedesc = coursedesc;
	}
	public String getCourseimg() {
		return courseimg;
	}
	public void setCourseimg(String courseimg) {
		this.courseimg = courseimg;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public CourseGrade2 getCourseGrade2() {
		return courseGrade2;
	}
	public void setCourseGrade2(CourseGrade2 courseGrade2) {
		this.courseGrade2 = courseGrade2;
	}
	@Override
	public String toString() {
		return "Courses [id=" + id + ", coursename=" + coursename + ", coursedesc=" + coursedesc + ", courseimg="
				+ courseimg + ", coureAuthority=" + coureAuthority + ", starttime="
				+ starttime + ", couretime=" + couretime + ", studynum=" + studynum + ", level=" + level + ", teacher="
				+ teacher + ", courseGrade2=" + courseGrade2 + "]";
	}
	
	
	
}

package com.chz.entity;

public class CourseGrade2 {
	private  int id;
    private  String  name;
    private  CourseGrade  courseGrade;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CourseGrade getCourseGrade() {
		return courseGrade;
	}
	public void setCourseGrade(CourseGrade courseGrade) {
		this.courseGrade = courseGrade;
	}
	@Override
	public String toString() {
		return "CourseGrade2 [id=" + id + ", name=" + name + ", courseGrade=" + courseGrade + "]";
	}
    
}

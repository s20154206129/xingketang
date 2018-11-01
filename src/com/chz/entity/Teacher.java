package com.chz.entity;

public class Teacher {
         private  int id;
         private  String teacherDesc;
         private  String  tname;
         private String  teacherimg;
         
         private String sex;
         private String  educationLevel;
         private String  birthday;
         private String email;
         
         //×éÖ¯
         private String 	organization;
         
       
		public String getOrganization() {
			return organization;
		}
		public void setOrganization(String organization) {
			this.organization = organization;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getEducationLevel() {
			return educationLevel;
		}
		public void setEducationLevel(String educationLevel) {
			this.educationLevel = educationLevel;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTeacherDesc() {
			return teacherDesc;
		}
		public void setTeacherDesc(String teacherDesc) {
			this.teacherDesc = teacherDesc;
		}
		public String getTname() {
			return tname;
		}
		public void setTname(String tname) {
			this.tname = tname;
		}
		public String getTeacherimg() {
			return teacherimg;
		}
		public void setTeacherimg(String teacherimg) {
			this.teacherimg = teacherimg;
		}
		@Override
		public String toString() {
			return "Teacher [id=" + id + ", teacherDesc=" + teacherDesc + ", tname=" + tname + ", teacherimg="
					+ teacherimg + ", sex=" + sex + ", educationLevel=" + educationLevel + ", birthday=" + birthday
					+ ", email=" + email + ", organization=" + organization + "]";
		}
		
		
		
}

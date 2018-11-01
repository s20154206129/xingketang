package com.chz.entity;

public class Role<T> {
       private  int  rid;
       private  String rname;
       private  T   pojo;  //角色可能是 多种
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public T getPojo() {
		return pojo;
	}
	public void setPojo(T pojo) {
		this.pojo = pojo;
	}
	@Override
	public String toString() {
		return "Role [rid=" + rid + ", rname=" + rname + ", pojo=" + pojo + "]";
	} 
       
}

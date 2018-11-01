package com.chz.entity;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 章
 * @author 0000
 *
 */
public class Chapter {
	 private  int   cid;
     private  int   zid;//第几章
     private  String  zContent;
     
     //对象   一章  对应多  节
    private Set<Section>    sectionList = new LinkedHashSet<Section>();
     

	public Set<Section> getSectionList() {
		return sectionList;
	}
	public void setSectionList(Set<Section> sectionList) {
		this.sectionList = sectionList;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getZid() {
		return zid;
	}
	public void setZid(int zid) {
		this.zid = zid;
	}
	public String getzContent() {
		return zContent;
	}
	public void setzContent(String zContent) {
		this.zContent = zContent;
	}
	@Override
	public String toString() {
		return "Chapter [cid=" + cid + ", zid=" + zid + ", zContent=" + zContent + ", sectionList=" + sectionList + "]";
	}
	
	
     
}

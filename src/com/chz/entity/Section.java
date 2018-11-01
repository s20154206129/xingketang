package com.chz.entity;

/**
 * 课程   每个章节  内容 
 * @author 0000
 *
 */
public class Section {
       private  int   cid;
       private  int   zid;//第几章
       private  int    sid; //第几节
       private  String sectionContent;
       private  String videoaddress;
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
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSectionContent() {
		return sectionContent;
	}
	public void setSectionContent(String sectionContent) {
		this.sectionContent = sectionContent;
	}
	public String getVideoaddress() {
		return videoaddress;
	}
	public void setVideoaddress(String videoaddress) {
		this.videoaddress = videoaddress;
	}

	@Override
	public String toString() {
		return "Section [cid=" + cid + ", zid=" + zid + ", sid=" + sid + ", sectionContent=" + sectionContent
				+ ", videoaddress=" + videoaddress  + "]";
	}
       
}

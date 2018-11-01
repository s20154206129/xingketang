package com.chz.service;

import java.util.List;

import com.chz.entity.Chapter;
import com.chz.entity.Section;

public interface VedioService {

	public List<Section> getSection(int courseId);
     
	//获取章节
	public List<Chapter> getChapter(int courseid);

	
	public void addChapterSection(int couseId,int chapter,String string, String[] sitems);
     
	//获得最新章节
	public Integer getChapterId(int courseId);

	// 将阿里云的 自动生成的 视频id 存入 数据库
	public void insertVideoId(String videoId,int cid,int zid,int  sid);

	public String getChapterAddress(int cid, int zid, int sid);

}

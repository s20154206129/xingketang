package com.chz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chz.dao.ChapterDao;
import com.chz.dao.SectionDao;
import com.chz.dao.UserDao;
import com.chz.entity.Chapter;
import com.chz.entity.Section;
import com.chz.service.VedioService;

@Service("vedioService")
public class VedioServiceImpl  implements  VedioService{
      
	@Autowired
	private  SectionDao dao;
	
	@Autowired
	private  ChapterDao  chapterDao;
	
	
	 @Override
	public List<Section> getSection(int courseid) {
		return dao.findAllSectionById(courseid);
	}
	 
	 
	 
	 @Override
	public List<Chapter> getChapter(int courseid) {
		return chapterDao.findChapterById(courseid);
	}
	 
	 
	 /**
	  * 增加章节
	  */
	 @Override
	public void addChapterSection(int courseId,int  charpter,String chapterContent, String[] sitems) {
		
		 System.out.println("课程id:"+courseId+",章:"+charpter);
		 //向td_zvedio 表插入  cid  zid zcontent
		 chapterDao.saveChapter(courseId, charpter, chapterContent);
		 for(int i=0;i<sitems.length;i++){
			 //向td_sectionvedio 插入  cid  zid  sid   sectionContent 
			 dao.saveChapterSection(courseId,charpter,i+1,sitems[i]);
		 }
	}
	 
	  @Override
		public Integer getChapterId(int courseId) {
			return chapterDao.findChapterZidById(courseId);
		}
	 
	
	  
	@Override
	public void insertVideoId(String videoId,int cid,int zid,int sid) {
		System.out.println("数据库存入上传视频de id");
		dao.insertVideoId(videoId,cid,zid,sid);
	}
	
	
	//获取 点播视频的id
	@Override
	public String getChapterAddress(int cid, int zid, int sid) {
	     return dao.findChapterAddress(cid,zid,sid);
	}
}

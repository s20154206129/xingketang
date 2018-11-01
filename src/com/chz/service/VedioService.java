package com.chz.service;

import java.util.List;

import com.chz.entity.Chapter;
import com.chz.entity.Section;

public interface VedioService {

	public List<Section> getSection(int courseId);
     
	//��ȡ�½�
	public List<Chapter> getChapter(int courseid);

	
	public void addChapterSection(int couseId,int chapter,String string, String[] sitems);
     
	//��������½�
	public Integer getChapterId(int courseId);

	// �������Ƶ� �Զ����ɵ� ��Ƶid ���� ���ݿ�
	public void insertVideoId(String videoId,int cid,int zid,int  sid);

	public String getChapterAddress(int cid, int zid, int sid);

}

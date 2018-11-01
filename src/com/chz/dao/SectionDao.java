package com.chz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chz.entity.Chapter;
import com.chz.entity.Section;

public interface SectionDao {
	public List<Section> findAllSectionById(@Param("courseId")int courseId);
         
	public void saveChapterSection(@Param("courseId")int couseId,@Param("charpter")int charpter, @Param("sid")int sid, @Param("sectionContent") String sectionContent);

	public void insertVideoId(@Param("videoId")String videoId,@Param("cid")int cid,@Param("zid")int zid,@Param("sid")int sid);

	public String findChapterAddress(@Param("cid")int cid, @Param("zid")int zid, @Param("sid")int sid);
}



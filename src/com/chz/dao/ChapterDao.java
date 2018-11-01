package com.chz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chz.entity.Chapter;

public interface ChapterDao   {
	public List<Chapter> findChapterById(@Param("courseId")int courseId);

	public void saveChapter(@Param("courseId")int courseId, @Param("charpter")int charpter,@Param("chapterContent") String chapterContent);

	public Integer findChapterZidById(@Param("cid")int courseId);
}

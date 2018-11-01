package com.chz.lucene;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.chz.action.BaseAction;
import com.chz.entity.CourseGrade2;
import com.chz.entity.Courses;
import com.chz.entity.Teacher;
public class CreateIndex {
	private List<Courses> list;
	private Analyzer analyzer = new SmartChineseAnalyzer();
	private Directory directory;
	private IndexWriter iwriter;
	private Query query;

	public void addIndex(Courses upDoc) throws IOException {
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		directory = FSDirectory.open(new File(BaseAction.RootPath + "index/").toPath());
		iwriter = new IndexWriter(directory, config);
		try {
			Document doc = new Document();
			//增加索引    尽量还是加上id 的索引
 			doc.add(new Field("id", upDoc.getId()+"", TextField.TYPE_STORED));
			doc.add(new Field("coursename", upDoc.getCoursename(), TextField.TYPE_STORED));
			doc.add(new Field("coursedesc", upDoc.getCoursedesc(), TextField.TYPE_STORED));
			doc.add(new Field("coursetype", upDoc.getCourseGrade2().getName(), TextField.TYPE_STORED));
			doc.add(new Field("courseimg", upDoc.getCourseimg(), TextField.TYPE_STORED));
			doc.add(new Field("tname", upDoc.getTeacher().getTname(), TextField.TYPE_STORED));
			iwriter.addDocument(doc);
		} catch (Exception e) {
			e.printStackTrace();
			iwriter.close();
			directory.close();
		} finally {
			iwriter.close();
			directory.close();
		}
	}
	public void deleteAll() throws IOException {
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		directory = FSDirectory.open(new File(BaseAction.RootPath + "index/").toPath());
		iwriter = new IndexWriter(directory, config);
		iwriter.deleteAll();
		iwriter.close();
		directory.close();
	}
	public void deleteById(int  id) throws IOException, ParseException {
		
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		directory = FSDirectory.open(new File(BaseAction.RootPath + "index/").toPath());
		iwriter = new IndexWriter(directory, config);
		QueryParser parser = new QueryParser("id", analyzer);
		Query query = parser.parse(id + "");
		iwriter.deleteDocuments(query);
		iwriter.close();
		directory.close();
	}

	// 根据索引查询    建立索引之后才能  查询  不能报异常
	public List<Courses> getDocSearchList(String key) throws IOException, ParseException {
		list = new ArrayList<Courses>();
		directory = FSDirectory.open(new File(BaseAction.RootPath + "index/").toPath());
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		//这里是通过哪些方面  进行检索  
		String[] fields = { "coursename", "coursedesc", "coursetype", "tname" };
		MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
		query = parser.parse(key);
		@SuppressWarnings("deprecation")
		ScoreDoc[] hits = isearcher.search(query, null, 20).scoreDocs;// searchAfter(page*10,
		
	
		// Iterate through the results:
		Document hitDoc = null;
		Courses resDoc;
		for (int i = 0; i < hits.length; i++) {
			resDoc = new Courses();
			hitDoc = isearcher.doc(hits[i].doc);
			resDoc.setCoursename((hitDoc.get("coursename")));
			resDoc.setCoursedesc(hitDoc.get("coursedesc"));
			CourseGrade2   courseGrade2=new CourseGrade2();
			courseGrade2.setName(hitDoc.get("coursetype"));
			resDoc.setCourseGrade2(courseGrade2);
			resDoc.setCourseimg(hitDoc.get("courseimg"));
			Teacher teacher = new Teacher();
		    teacher.setTname(hitDoc.get("tname"));
			resDoc.setTeacher(teacher);
			list.add(resDoc);
		}
		ireader.close();
		directory.close();
		return list;
	}

}

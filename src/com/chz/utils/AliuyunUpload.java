package com.chz.utils;

import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.chz.service.VedioService;

/**
 * 阿里云上传太慢
 * @author 0000
 *
 */
//1 Component  
@Component 
public class AliuyunUpload {
	     //工具类 静态注入Service
	    @Autowired
	    private  VedioService  vedioService;
	    //2 静态的本类
	    private  static AliuyunUpload  aliuyunUpload;
	    
	    //3 初始化
	    @PostConstruct
	    public void init(){
	    	  aliuyunUpload=this;
	    	  aliuyunUpload.vedioService=this.vedioService;
	    }
	    
	    
	    //账号AK信息请填写(必选)  accesskEy
	    private static final String accessKeyId = "LTAIzuuVVdEUc4Gf";
	    //账号AK信息请填写(必选)
	    private static final String accessKeySecret = "0z6Lwvqvz9wlV1tSHUhWv8MabQWPSB";
	    public   static  void uploadAliyun(String fileName,String name,int cid,int zid,int sid){
	    	
	    	System.out.println("进入上传阿里云");
	    	//视频标题(必选)
	        String title =name ;
	        //1.本地文件上传和文件流上传，文件名称为上传文件绝对路径，如:/User/sample/文件名称.mp4 (必选)
	        //2.网络流上传，文件名称为源文件名，如文件名称.mp4(必选)。
	        //3.流式上传，文件名称为源文件名，如文件名称.mp4(必选)。
	        //4.任何上传方式文件名必须包含扩展名
	         //fileName = "D:\\first.mp4";
	        //本地文件上传
	        testUploadVideo(accessKeyId, accessKeySecret, title, fileName,cid,zid,sid);
	        //待上传视频的网络流地址
//	        String url = "http://video.sample.com/sample.mp4";
	        //网络流上传
//	        testUploadURLStream(accessKeyId, accessKeySecret, title, fileName, url);
	        //文件流上传
//	        testUploadFileStream(accessKeyId, accessKeySecret, title, fileName);
	        //4.流式上传，如文件流和网络流等
//	        InputStream inputStream = null;
	        //4.1 文件流
//	        try {
//	            inputStream = new FileInputStream(fileName);
//	        } catch (FileNotFoundException e) {
//	            e.printStackTrace();
//	        }
	        //4.2 网络流
//	        try {
//	            inputStream = new URL(url).openStream();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	        testUploadStream(accessKeyId, accessKeySecret, title, fileName, inputStream);
	    }
	    /**
	     * 本地文件上传接口
	     * @param accessKeyId
	     * @param accessKeySecret
	     * @param title
	     * @param fileName
	     */
	    private static void testUploadVideo(String accessKeyId, String accessKeySecret, String title, String fileName,int cid,int zid,int sid) {
	        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
	        /* 可指定分片上传时每个分片的大小，默认为1M字节 */
	        request.setPartSize(1 * 1024 * 1024L);
	        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
	        request.setTaskNum(1);
	        /* 是否开启断点续传, 默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。
	        注意: 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启*/
	        request.setEnableCheckpoint(false);
	        /* OSS慢请求日志打印超时时间，是指每个分片上传时间超过该阈值时会打印debug日志，如果想屏蔽此日志，请调整该阈值。单位: 毫秒，默认为300000毫秒*/
	        //request.setSlowRequestsThreshold(300000L);
	        /* 可指定每个分片慢请求时打印日志的时间阈值，默认为300s*/
	        //request.setSlowRequestsThreshold(300000L);
	        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印*/
	        //request.setIsShowWaterMark(true);
	        /* 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档 https://help.aliyun.com/document_detail/57029.html */
	        //request.setCallback("http://callback.sample.com");
	        /* 视频分类ID(可选) */
	        //request.setCateId(0);
	        /* 视频标签,多个用逗号分隔(可选) */
	        //request.setTags("标签1,标签2");
	        /* 视频描述(可选) */
	        //request.setDescription("视频描述");
	        /* 封面图片(可选) */
	        //request.setCoverURL("http://cover.sample.com/sample.jpg");
	        /* 模板组ID(可选) */
	        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
	        /* 存储区域(可选) */
	        //request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
	       
	        UploadVideoImpl uploader = new UploadVideoImpl();
	        UploadVideoResponse response = uploader.uploadVideo(request);
	        
	        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
	        if (response.isSuccess()) {
	        	//将视频  阿里云自动生成的 id 保存入数据库
	        	System.out.println("cid :"+cid+","+zid+","+sid);
	        	aliuyunUpload.vedioService.insertVideoId(response.getVideoId(),cid,zid,sid);
	            System.out.print("VideoId=" + response.getVideoId() + "\n");
	        } else {
	            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
	            System.out.print("VideoId=" + response.getVideoId() + "\n");
	            System.out.print("ErrorCode=" + response.getCode() + "\n");
	            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
	        }
	    }
}

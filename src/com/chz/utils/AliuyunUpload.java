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
 * �������ϴ�̫��
 * @author 0000
 *
 */
//1 Component  
@Component 
public class AliuyunUpload {
	     //������ ��̬ע��Service
	    @Autowired
	    private  VedioService  vedioService;
	    //2 ��̬�ı���
	    private  static AliuyunUpload  aliuyunUpload;
	    
	    //3 ��ʼ��
	    @PostConstruct
	    public void init(){
	    	  aliuyunUpload=this;
	    	  aliuyunUpload.vedioService=this.vedioService;
	    }
	    
	    
	    //�˺�AK��Ϣ����д(��ѡ)  accesskEy
	    private static final String accessKeyId = "LTAIzuuVVdEUc4Gf";
	    //�˺�AK��Ϣ����д(��ѡ)
	    private static final String accessKeySecret = "0z6Lwvqvz9wlV1tSHUhWv8MabQWPSB";
	    public   static  void uploadAliyun(String fileName,String name,int cid,int zid,int sid){
	    	
	    	System.out.println("�����ϴ�������");
	    	//��Ƶ����(��ѡ)
	        String title =name ;
	        //1.�����ļ��ϴ����ļ����ϴ����ļ�����Ϊ�ϴ��ļ�����·������:/User/sample/�ļ�����.mp4 (��ѡ)
	        //2.�������ϴ����ļ�����ΪԴ�ļ��������ļ�����.mp4(��ѡ)��
	        //3.��ʽ�ϴ����ļ�����ΪԴ�ļ��������ļ�����.mp4(��ѡ)��
	        //4.�κ��ϴ���ʽ�ļ������������չ��
	         //fileName = "D:\\first.mp4";
	        //�����ļ��ϴ�
	        testUploadVideo(accessKeyId, accessKeySecret, title, fileName,cid,zid,sid);
	        //���ϴ���Ƶ����������ַ
//	        String url = "http://video.sample.com/sample.mp4";
	        //�������ϴ�
//	        testUploadURLStream(accessKeyId, accessKeySecret, title, fileName, url);
	        //�ļ����ϴ�
//	        testUploadFileStream(accessKeyId, accessKeySecret, title, fileName);
	        //4.��ʽ�ϴ������ļ�������������
//	        InputStream inputStream = null;
	        //4.1 �ļ���
//	        try {
//	            inputStream = new FileInputStream(fileName);
//	        } catch (FileNotFoundException e) {
//	            e.printStackTrace();
//	        }
	        //4.2 ������
//	        try {
//	            inputStream = new URL(url).openStream();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	        testUploadStream(accessKeyId, accessKeySecret, title, fileName, inputStream);
	    }
	    /**
	     * �����ļ��ϴ��ӿ�
	     * @param accessKeyId
	     * @param accessKeySecret
	     * @param title
	     * @param fileName
	     */
	    private static void testUploadVideo(String accessKeyId, String accessKeySecret, String title, String fileName,int cid,int zid,int sid) {
	        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
	        /* ��ָ����Ƭ�ϴ�ʱÿ����Ƭ�Ĵ�С��Ĭ��Ϊ1M�ֽ� */
	        request.setPartSize(1 * 1024 * 1024L);
	        /* ��ָ����Ƭ�ϴ�ʱ�Ĳ����߳�����Ĭ��Ϊ1��(ע�������û�ռ�÷�����CPU��Դ������ݷ��������ָ����*/
	        request.setTaskNum(1);
	        /* �Ƿ����ϵ�����, Ĭ�϶ϵ��������ܹرա������粻�ȶ����߳������ʱ���ٴη�����ͬ�ϴ����󣬿��Լ���δ��ɵ��ϴ����������ڳ�ʱ3000���Բ����ϴ���ɵĴ��ļ���
	        ע��: �ϵ����������󣬻����ϴ������н��ϴ�λ��д�뱾�ش����ļ���Ӱ���ļ��ϴ��ٶȣ���������ʵ�����ѡ���Ƿ���*/
	        request.setEnableCheckpoint(false);
	        /* OSS��������־��ӡ��ʱʱ�䣬��ָÿ����Ƭ�ϴ�ʱ�䳬������ֵʱ���ӡdebug��־����������δ���־�����������ֵ����λ: ���룬Ĭ��Ϊ300000����*/
	        //request.setSlowRequestsThreshold(300000L);
	        /* ��ָ��ÿ����Ƭ������ʱ��ӡ��־��ʱ����ֵ��Ĭ��Ϊ300s*/
	        //request.setSlowRequestsThreshold(300000L);
	        /* �Ƿ�ʹ��Ĭ��ˮӡ(��ѡ)��ָ��ģ����IDʱ������ģ��������ȷ���Ƿ�ʹ��Ĭ��ˮӡ*/
	        //request.setIsShowWaterMark(true);
	        /* �����ϴ���ɺ�Ļص�URL(��ѡ)������ͨ���㲥����̨������Ϣ�����¼����μ��ĵ� https://help.aliyun.com/document_detail/57029.html */
	        //request.setCallback("http://callback.sample.com");
	        /* ��Ƶ����ID(��ѡ) */
	        //request.setCateId(0);
	        /* ��Ƶ��ǩ,����ö��ŷָ�(��ѡ) */
	        //request.setTags("��ǩ1,��ǩ2");
	        /* ��Ƶ����(��ѡ) */
	        //request.setDescription("��Ƶ����");
	        /* ����ͼƬ(��ѡ) */
	        //request.setCoverURL("http://cover.sample.com/sample.jpg");
	        /* ģ����ID(��ѡ) */
	        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
	        /* �洢����(��ѡ) */
	        //request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
	       
	        UploadVideoImpl uploader = new UploadVideoImpl();
	        UploadVideoResponse response = uploader.uploadVideo(request);
	        
	        System.out.print("RequestId=" + response.getRequestId() + "\n");  //������Ƶ�㲥���������ID
	        if (response.isSuccess()) {
	        	//����Ƶ  �������Զ����ɵ� id ���������ݿ�
	        	System.out.println("cid :"+cid+","+zid+","+sid);
	        	aliuyunUpload.vedioService.insertVideoId(response.getVideoId(),cid,zid,sid);
	            System.out.print("VideoId=" + response.getVideoId() + "\n");
	        } else {
	            /* ������ûص�URL��Ч����Ӱ����Ƶ�ϴ������Է���VideoIdͬʱ�᷵�ش����롣��������ϴ�ʧ��ʱ��VideoIdΪ�գ���ʱ��Ҫ���ݷ��ش���������������ԭ�� */
	            System.out.print("VideoId=" + response.getVideoId() + "\n");
	            System.out.print("ErrorCode=" + response.getCode() + "\n");
	            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
	        }
	    }
}

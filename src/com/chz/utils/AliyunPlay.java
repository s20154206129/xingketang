package com.chz.utils;
import com.aliyuncs.profile.DefaultProfile;


import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.chz.service.VedioService;
@Component 
public class AliyunPlay {
	
	    @Autowired
	    private  VedioService  vedioService;
	    //2 ��̬�ı���
	    private  static AliyunPlay  aliyunPlay;
	    
	    //3 ��ʼ��
	    @PostConstruct
	    public void init(){
	    	aliyunPlay=this;
	    	aliyunPlay.vedioService=this.vedioService;
	    }
	//��ʼ���ͻ���
	public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
	    //�㲥�������ڵ�Region����������cn-shanghai����Ҫ��д�������
	    String regionId = "cn-shanghai";
	    DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
	    DefaultAcsClient client = new DefaultAcsClient(profile);
	    return client;
	}
	
	/*��ȡ���ŵ�ַ����*/
	public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client,int cid,int zid,int sid) throws Exception {
	    GetPlayInfoRequest request = new GetPlayInfoRequest();
	   /* System.out.println("��Ƶ id�� "+response.getVideoId());*/
	    //�����ݿ���ȡ��    id
	    String vedioId=aliyunPlay.vedioService.getChapterAddress(cid,zid,sid);
	    System.out.println("�㲥��Ƶ:"+vedioId);
	    request.setVideoId(vedioId);
	    return client.getAcsResponse(request);
	}
	
	//��
	public static   String  play(int cid,int zid,int sid) {
		//��ʼ���ͻ���
	    DefaultAcsClient client = initVodClient("LTAIzuuVVdEUc4Gf","0z6Lwvqvz9wlV1tSHUhWv8MabQWPSB");
	     //���ź���
	    GetPlayInfoResponse response = new GetPlayInfoResponse();
	    try {
	    	//getPlay����
	        response = getPlayInfo(client,cid,zid,sid);
	        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
	        //���ŵ�ַ
	        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
	            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
	            return  playInfo.getPlayURL();
	        }
	        //Base��Ϣ
	        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
	    } catch (Exception e) {
	        System.out.print("ErrorMessage = " + e.getLocalizedMessage());
	    }
	    System.out.print("RequestId = " + response.getRequestId() + "\n");
		return null;
	}
	
	/*��ȡ����ƾ֤����*/
	public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client,int cid,int zid,int sid) throws Exception {
	    GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
	    //�����ݿ���ȡ��    id
	    String vedioId=aliyunPlay.vedioService.getChapterAddress(cid,zid,sid);
	    request.setVideoId(vedioId);
	    return client.getAcsResponse(request);
	}
	
	
	
	//���ƾ֤  ��
	public static String getPlayAuth(int cid,int zid,int sid) {
	    DefaultAcsClient client = initVodClient("LTAIzuuVVdEUc4Gf", "0z6Lwvqvz9wlV1tSHUhWv8MabQWPSB");
	    GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
	    try {
	        response = getVideoPlayAuth(client,cid,zid,sid);
	        //����ƾ֤
	        System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
	        //VideoMeta��Ϣ
	        System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
	        return   response.getPlayAuth();
	    } catch (Exception e) {
	        System.out.print("ErrorMessage = " + e.getLocalizedMessage());
	    }
	    System.out.print("RequestId = " + response.getRequestId() + "\n");
	    return null;
	}
	
}

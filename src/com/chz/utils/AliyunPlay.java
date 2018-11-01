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
	    //2 静态的本类
	    private  static AliyunPlay  aliyunPlay;
	    
	    //3 初始化
	    @PostConstruct
	    public void init(){
	    	aliyunPlay=this;
	    	aliyunPlay.vedioService=this.vedioService;
	    }
	//初始化客户端
	public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
	    //点播服务所在的Region，国内请填cn-shanghai，不要填写别的区域
	    String regionId = "cn-shanghai";
	    DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
	    DefaultAcsClient client = new DefaultAcsClient(profile);
	    return client;
	}
	
	/*获取播放地址函数*/
	public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client,int cid,int zid,int sid) throws Exception {
	    GetPlayInfoRequest request = new GetPlayInfoRequest();
	   /* System.out.println("视频 id： "+response.getVideoId());*/
	    //从数据库中取出    id
	    String vedioId=aliyunPlay.vedioService.getChapterAddress(cid,zid,sid);
	    System.out.println("点播视频:"+vedioId);
	    request.setVideoId(vedioId);
	    return client.getAcsResponse(request);
	}
	
	//主
	public static   String  play(int cid,int zid,int sid) {
		//初始化客户端
	    DefaultAcsClient client = initVodClient("LTAIzuuVVdEUc4Gf","0z6Lwvqvz9wlV1tSHUhWv8MabQWPSB");
	     //播放函数
	    GetPlayInfoResponse response = new GetPlayInfoResponse();
	    try {
	    	//getPlay方法
	        response = getPlayInfo(client,cid,zid,sid);
	        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
	        //播放地址
	        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
	            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
	            return  playInfo.getPlayURL();
	        }
	        //Base信息
	        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
	    } catch (Exception e) {
	        System.out.print("ErrorMessage = " + e.getLocalizedMessage());
	    }
	    System.out.print("RequestId = " + response.getRequestId() + "\n");
		return null;
	}
	
	/*获取播放凭证函数*/
	public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client,int cid,int zid,int sid) throws Exception {
	    GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
	    //从数据库中取出    id
	    String vedioId=aliyunPlay.vedioService.getChapterAddress(cid,zid,sid);
	    request.setVideoId(vedioId);
	    return client.getAcsResponse(request);
	}
	
	
	
	//获得凭证  主
	public static String getPlayAuth(int cid,int zid,int sid) {
	    DefaultAcsClient client = initVodClient("LTAIzuuVVdEUc4Gf", "0z6Lwvqvz9wlV1tSHUhWv8MabQWPSB");
	    GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
	    try {
	        response = getVideoPlayAuth(client,cid,zid,sid);
	        //播放凭证
	        System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
	        //VideoMeta信息
	        System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
	        return   response.getPlayAuth();
	    } catch (Exception e) {
	        System.out.print("ErrorMessage = " + e.getLocalizedMessage());
	    }
	    System.out.print("RequestId = " + response.getRequestId() + "\n");
	    return null;
	}
	
}

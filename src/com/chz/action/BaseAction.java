package com.chz.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * baseAction
 * @author 0000
 *
 */
public class BaseAction extends ActionSupport  {
	
	
	public  static  final  String RootPath="D:/xing/";
	
    protected HttpServletRequest request;

    protected HttpServletResponse response;
    
    protected Map<String,Object> session;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3012485993546819990L;

	protected HttpServletRequest getHttpRequest() {
        request = ServletActionContext.getRequest();
        return request;
    }
	protected  Map<String,Object>  getHttpSession() {
		session = ActionContext.getContext().getSession() ;
        return session;
    }
	/**
	 * String --¡·String
	 * @param date
	 * @return
	 */
	public static String conversionTo(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//date->StringµÄ¸ñÊ½
		String d = null;
		Date parse =null;
		
		try {
			d = sdf.format(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(date));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		return d;
	}
	
	public static Date stringToDate(Date date) {
		
		return null;
	}
	
}

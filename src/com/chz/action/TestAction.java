package com.chz.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

@Namespace("/test")
@ParentPackage("struts-default")
@Controller
public class TestAction extends  BaseAction{
	@Action(value ="t", results = {
			@Result(name = "success", type = "redirect", location = "/success.jsp") })
	@Override
	public String execute() throws Exception {
		System.out.println("×¢½â²âÊÔ³É¹¦");
		System.out.println("------------------------------");
		return SUCCESS;
	}
	 
}

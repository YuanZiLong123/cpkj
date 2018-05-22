package com.cpkj.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public enum SessionUtil {
	
	instance;
	
	public  String getSession(HttpServletRequest request){
		String userId = "";
		if (null!=request.getHeader("userId")) {
			userId = request.getHeader("userId");
		}else if (null!=request.getSession()) {
			userId = (String) request.getSession().getAttribute("userId");
		} 
		return StringUtils.isNotBlank(userId)?userId:"";
	}
	
}

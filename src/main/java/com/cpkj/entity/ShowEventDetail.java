package com.cpkj.entity;

import java.util.Date;


public class ShowEventDetail {
	
	private String userName;//用户名
	
	private String eventContent;//事件的内容
	
	private Date eventCreateDate;//事件创建日期

	private Double dayNumber;//事件的时长

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	public Date getEventCreateDate() {
		return eventCreateDate;
	}

	public void setEventCreateDate(Date eventCreateDate) {
		this.eventCreateDate = eventCreateDate;
	}

	public Double getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(Double dayNumber) {
		this.dayNumber = dayNumber;
	}
	
	
	
	
}

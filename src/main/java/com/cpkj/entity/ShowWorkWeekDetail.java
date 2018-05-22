package com.cpkj.entity;

import java.util.List;

public class ShowWorkWeekDetail {
	
	private String userName;
	
	private List<WorkWeekDetailList> workWeekDetailLists;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<WorkWeekDetailList> getWorkWeekDetailLists() {
		return workWeekDetailLists;
	}

	public void setWorkWeekDetailLists(List<WorkWeekDetailList> workWeekDetailLists) {
		this.workWeekDetailLists = workWeekDetailLists;
	}
	
	
	
}

package com.cpkj.entity;

import java.util.List;


public class ShowWorkDayDeatail {
	
	private String userName;
	
	private List<WorkDayDetailList> workDayDetailLists;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<WorkDayDetailList> getWorkDayDetailLists() {
		return workDayDetailLists;
	}

	public void setWorkDayDetailLists(List<WorkDayDetailList> workDayDetailLists) {
		this.workDayDetailLists = workDayDetailLists;
	}

	@Override
	public String toString() {
		return "ShowWorkDayDeatail [userName=" + userName
				+ ", workDayDetailLists=" + workDayDetailLists + "]";
	}
	
	
	
}

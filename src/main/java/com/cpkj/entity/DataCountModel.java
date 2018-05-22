package com.cpkj.entity;

public class DataCountModel {
	
	private Long allNumber;//所有
	
	private Long subNumber;//提交
	
	private Long leaveNumber;//请假
	
	private Long overtimeNumber;//加班
	
	private Double subRate;//提交率

	public Long getAllNumber() {
		return allNumber;
	}

	public void setAllNumber(Long allNumber) {
		this.allNumber = allNumber;
	}

	public Long getSubNumber() {
		return subNumber;
	}

	public void setSubNumber(Long subNumber) {
		this.subNumber = subNumber;
	}

	public Long getLeaveNumber() {
		return leaveNumber;
	}

	public void setLeaveNumber(Long leaveNumber) {
		this.leaveNumber = leaveNumber;
	}

	public Long getOvertimeNumber() {
		return overtimeNumber;
	}

	public void setOvertimeNumber(Long overtimeNumber) {
		this.overtimeNumber = overtimeNumber;
	}

	public Double getSubRate() {
		return subRate;
	}

	public void setSubRate(Double subRate) {
		this.subRate = subRate;
	}
	
	
}

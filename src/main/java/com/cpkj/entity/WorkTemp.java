package com.cpkj.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="work_temp")
public class WorkTemp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="work_temp_detail_id")
	private Long workTempDetailId;
	
	@Column(name="work_temp_content")
	private String workContent;
	
	@Column(name="work_temp_success")
	private Double workSuccess;
	
	@Column(name="create_user_id")
	private String  userId;
	
	@Column(name="on_day")
	private Date onDay;

	
	public Long getWorkTempDetailId() {
		return workTempDetailId;
	}

	public void setWorkTempDetailId(Long workTempDetailId) {
		this.workTempDetailId = workTempDetailId;
	}


	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public Double getWorkSuccess() {
		return workSuccess;
	}

	public void setWorkSuccess(Double workSuccess) {
		this.workSuccess = workSuccess;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getOnDay() {
		return onDay;
	}

	public void setOnDay(Date onDay) {
		this.onDay = onDay;
	}

	
	
}

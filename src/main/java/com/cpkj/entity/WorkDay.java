package com.cpkj.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="work_day")
public class WorkDay implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="work_day_id")
	private Long workDayId;
	
	@Column(name="create_user_id")
	private String userId;

	@Column(name="sub_day")
	private Date subDay;
	
	@Column(name="create_day")
	private Date createDay;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="work_day_state")
	private Integer workDayState;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="workDay",fetch=FetchType.LAZY)
	 private List<WorkDayDetailList> workDayDetailLists;

	public Long getWorkDayId() {
		return workDayId;
	}

	public void setWorkDayId(Long workDayId) {
		this.workDayId = workDayId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateDay() {
		return createDay;
	}

	public void setCreateDay(Date createDay) {
		this.createDay = createDay;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getWorkDayState() {
		return workDayState;
	}

	public void setWorkDayState(Integer workDayState) {
		this.workDayState = workDayState;
	}

	 public List<WorkDayDetailList> getWorkDayDetailLists() {
		return workDayDetailLists;
	}

	public void setWorkDayDetailLists(List<WorkDayDetailList> workDayDetailLists) {
		this.workDayDetailLists = workDayDetailLists;
	}

	public Date getSubDay() {
		return subDay;
	}

	public void setSubDay(Date subDay) {
		this.subDay = subDay;
	}

	
	
}

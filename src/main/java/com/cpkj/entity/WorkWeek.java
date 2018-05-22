package com.cpkj.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="work_week")
public class WorkWeek implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="work_week_id")
	private Long workweekId;
	
	@Column(name="create_user_id")
	private String userId;

	@Column(name="sub_time")
	private Date subTime;
	
	@Column(name="create_week")
	private Date workweek;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="week_number")
	private Integer weekNumber;
	
	
	@Column(name="work_week_state")
	private Integer workWeekState;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="workWeek")
	private List<WorkWeekDetailList> workWeekDetailLists;

	public Long getWorkweekId() {
		return workweekId;
	}

	public void setWorkweekId(Long workweekId) {
		this.workweekId = workweekId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getWorkweek() {
		return workweek;
	}

	public void setWorkweek(Date workweek) {
		this.workweek = workweek;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public List<WorkWeekDetailList> getWorkWeekDetailLists() {
		return workWeekDetailLists;
	}

	public void setWorkWeekDetailLists(List<WorkWeekDetailList> workWeekDetailLists) {
		this.workWeekDetailLists = workWeekDetailLists;
	}

	public Integer getWorkWeekState() {
		return workWeekState;
	}

	public void setWorkWeekState(Integer workWeekState) {
		this.workWeekState = workWeekState;
	}

	public Integer getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(Integer weekNumber) {
		this.weekNumber = weekNumber;
	}

	public Date getSubTime() {
		return subTime;
	}

	public void setSubTime(Date subTime) {
		this.subTime = subTime;
	}
	
	
}

package com.cpkj.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="work_week_detail")
@JsonIgnoreProperties(value={"workWeek"})
public class WorkWeekDetailList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="work_week_detail_id")
	private Long workweekDetailId;
	
	@Column(name="work_week_content")
	private String workweekContent;
	
	@Column(name="work_week_success")
	private Double workweekSuccess;
	
	@ManyToOne
	@JoinColumn(name="workweekId")
	private WorkWeek workWeek;

	public Long getWorkweekDetailId() {
		return workweekDetailId;
	}

	public void setWorkweekDetailId(Long workweekDetailId) {
		this.workweekDetailId = workweekDetailId;
	}

	public String getWorkweekContent() {
		return workweekContent;
	}

	public void setWorkweekContent(String workweekContent) {
		this.workweekContent = workweekContent;
	}

	public Double getWorkweekSuccess() {
		return workweekSuccess;
	}

	public void setWorkweekSuccess(Double workweekSuccess) {
		this.workweekSuccess = workweekSuccess;
	}

	public WorkWeek getWorkWeek() {
		return workWeek;
	}

	public void setWorkWeek(WorkWeek workWeek) {
		this.workWeek = workWeek;
	}
	
	
}

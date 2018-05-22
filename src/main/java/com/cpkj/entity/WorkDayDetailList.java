package com.cpkj.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name="work_day_detail_list")
@JsonIgnoreProperties(value={"workDay"})
public class WorkDayDetailList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="work_day_detail_id")
	private Long workDayDetailId;
	
	@Column(name="work_day_content")
	private String workDayContent;
	
	@Column(name="work_day_success")
	private Double workDaySuccess=0d;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=true)
	@JoinColumn(name="work_day_id")
	private WorkDay workDay;

	public Long getWorkDayDetailId() {
		return workDayDetailId;
	}

	public void setWorkDayDetailId(Long workDayDetailId) {
		this.workDayDetailId = workDayDetailId;
	}

	public String getWorkDayContent() {
		return workDayContent;
	}

	public void setWorkDayContent(String workDayContent) {
		this.workDayContent = workDayContent;
	}

	public Double getWorkDaySuccess() {
		return workDaySuccess;
	}

	public void setWorkDaySuccess(Double workDaySuccess) {
		this.workDaySuccess = workDaySuccess;
	}

	public WorkDay getWorkDay() {
		return workDay;
	}

	public void setWorkDay(WorkDay workDay) {
		this.workDay = workDay;
	}
	
	
}

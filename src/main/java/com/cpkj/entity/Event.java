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
@Table(name="event")
public class Event implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="event_id")
	private Long eventId;//事件的id
	
	@Column(name="create_user_id")
	private String createUserId;//事件创建者id
	
	@Column(name="event_content")
	private String eventContent;//事件的内容
	
	@Column(name="event_create_date")
	private Date eventCreateDate;//事件创建日期
	
	@Column(name="event_time")
	private Date eventTime;//事件时间
	
	@Column(name="event_type")
	private Integer eventType;//事件的分类：1为请假 2 为加班
	
	@Column(name="event_state")
	private Integer eventState = 0;//事件的状态：0为待审批 1为同意 2为驳回

	@Column(name="day_number")
	private Double dayNumber;//事件的时长
	
	@Column(name="deal_content")
	private String dealContent;
	
	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public Integer getEventState() {
		return eventState;
	}

	public void setEventState(Integer eventState) {
		this.eventState = eventState;
	}

	public Double getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(Double dayNumber) {
		this.dayNumber = dayNumber;
	}

	public String getDealContent() {
		return dealContent;
	}

	public void setDealContent(String dealContent) {
		this.dealContent = dealContent;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	
	
	
}

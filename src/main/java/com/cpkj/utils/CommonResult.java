package com.cpkj.utils;

import java.io.Serializable;

public class CommonResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Object data;
	
	private Boolean state;
	
	private Long total;
	
	private Integer totalPages;
	
	private Integer pageIndex;
	
	private String message;

	public CommonResult() {
	
		this.state = true;
	}
	

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public Long getTotal() {
		return total;
	}


	public void setTotal(Long total) {
		this.total = total;
	}


	public Integer getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	
	
}

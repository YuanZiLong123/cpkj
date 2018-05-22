package com.cpkj.dao;

import org.springframework.stereotype.Repository;

import com.cpkj.entity.WorkTemp;

@Repository
public interface WorkTempDao extends BaseDao<WorkTemp> {

	
	public WorkTemp getWorkTempByWorkContent(String  content);
}

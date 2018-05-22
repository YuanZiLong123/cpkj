package com.cpkj.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cpkj.entity.WorkDay;

@Repository
public interface WorkDayDao extends BaseDao<WorkDay>{

	@Query("SELECT workDay FROM WorkDay workDay JOIN  workDay.workDayDetailLists d WHERE workDay.userId = ?1")
	public List<WorkDay> getWorkDays(String userId);
	
	
}

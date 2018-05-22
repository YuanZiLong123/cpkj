package com.cpkj.dao;


import org.springframework.stereotype.Repository;

import com.cpkj.entity.WorkDay;
import com.cpkj.entity.WorkDayDetailList;

@Repository
public interface WorkDayDetailDao extends BaseDao<WorkDayDetailList> {

	public Integer deleteByWorkDay(WorkDay workDay );
}

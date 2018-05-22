package com.cpkj.service;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpkj.dao.WorkTempDao;
import com.cpkj.entity.QWorkTemp;
import com.cpkj.entity.WorkDayTemp;
import com.cpkj.entity.WorkTemp;
import com.cpkj.entity.WorkWeekTemp;
import com.cpkj.utils.SessionUtil;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;

@Service
@Transactional
public class WorkTempService extends BaseService{

	@Autowired
	private WorkTempDao workTempDao;
	
	@Autowired
	private HttpServletRequest request;
	
	
	/**
	 * 
	 * 获取预生成的日报工作量
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * 2018年5月10日
	 */
	public List<WorkDayTemp> getWorkDayTemps(){
		String userId = SessionUtil.instance.getSession(request);
		QWorkTemp qWorkTemp = QWorkTemp.workTemp;
		Calendar calendar = Calendar.getInstance();
		Predicate predicate = qWorkTemp.userId.eq(userId).and(qWorkTemp.onDay.week().eq(calendar.get(Calendar.WEEK_OF_YEAR)-1)).and(qWorkTemp.workSuccess.between(0, 1));
		return jpaQueryFactory.select(Projections.bean(WorkDayTemp.class, qWorkTemp.workContent.as("workDayContent"),qWorkTemp.workSuccess.as("workDaySuccess"))).from(qWorkTemp).where(predicate).fetch();
	}
	/**
	 * 
	 * 获取预生成的周报
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * 2018年5月10日
	 */
	public List<WorkWeekTemp> getWorkWeekTemps(){
		String userId = SessionUtil.instance.getSession(request);
		QWorkTemp qWorkTemp = QWorkTemp.workTemp;
		Calendar calendar = Calendar.getInstance();
		Predicate predicate = qWorkTemp.userId.eq(userId).and(qWorkTemp.onDay.week().eq(calendar.get(Calendar.WEEK_OF_YEAR)-1));
		return jpaQueryFactory.select(Projections.bean(WorkWeekTemp.class, qWorkTemp.workContent.as("workweekContent"),qWorkTemp.workSuccess.as("workweekSuccess"))).from(qWorkTemp).where(predicate).fetch();
	}
	
	
}

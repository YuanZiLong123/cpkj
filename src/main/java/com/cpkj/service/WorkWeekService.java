package com.cpkj.service;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cpkj.cmd.Cmd;
import com.cpkj.constant.PageAttribute;
import com.cpkj.dao.WorkWeekDao;
import com.cpkj.dao.WorkWeekDetailDao;
import com.cpkj.entity.QWorkWeek;
import com.cpkj.entity.WorkWeek;
import com.cpkj.entity.WorkWeekDetailList;
import com.cpkj.utils.SessionUtil;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
public class WorkWeekService {
	
	@Autowired
	private WorkWeekDao workWeekDao;
	
	@Autowired
	private WorkWeekDetailDao workWeekDetailDao;
	
	@Autowired
	private HttpServletRequest request;
	
	public Boolean check(WorkWeek workWeek){
		Date date  = null!=workWeek.getSubTime()?workWeek.getSubTime():new Date();
		String userId =SessionUtil.instance.getSession(request);
		QWorkWeek qWorkWeek = QWorkWeek.workWeek;
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(date);
		Predicate predicate =  qWorkWeek.subTime.week().eq(calendar.get(Calendar.WEEK_OF_YEAR)).and(qWorkWeek.userId.eq(userId));
		if (null!=workWeek.getWorkweekId()) {
			predicate = ExpressionUtils.and(predicate, qWorkWeek.workWeekState.eq(1));
		}
		return null!=workWeekDao.findOne(predicate)?true:false;
		
		
	}
	
	
	
	/**
	 * 
	 * 保存周报
	 * @author 袁子龙（15071746320）
	 * @param workweek 工作周报
	 * @return workWeek 工作周报的保存类
	 * 2018年5月11日
	 */
	public WorkWeek saveWorkWeek(WorkWeek workWeek){
		String userId = SessionUtil.instance.getSession(request);
		for (WorkWeekDetailList workWeekDetail:workWeek.getWorkWeekDetailLists()) {
			workWeekDetail.setWorkWeek(workWeek);
		}
		workWeek.setUserId(userId);
		Date time = null!=workWeek.getSubTime()?workWeek.getSubTime():new Date();
		workWeek.setSubTime(time);
		workWeek.setWorkweek(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		workWeek.setWeekNumber(calendar.get(Calendar.WEEK_OF_YEAR));
		workWeekDetailDao.save(workWeek.getWorkWeekDetailLists());
		return workWeekDao.save(workWeek);
	}
	
	/**
	 * 
	 * 获取周报
	 * @author 袁子龙（15071746320）
	 * @param pageIndex 分页的页码
	 * @return workWeek 周报列表的页面封装
	 * 2018年5月11日
	 */
	public Page<WorkWeek> getWrokWeekList(Integer pageIndex){
		String userId = SessionUtil.instance.getSession(request);
		QWorkWeek qWorkWeek = QWorkWeek.workWeek;
		Predicate predicate = qWorkWeek.userId.eq(userId);
		Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"subTime"));
		PageRequest pageRequest = new PageRequest(pageIndex-1, PageAttribute.PAGE_SIZE, sort);
		return workWeekDao.findAll(predicate, pageRequest);
	}
	
	
	/**
	 * 
	 * 管理员获取周报列表
	 * @author 袁子龙（15071746320）
	 * @param cmd  添加 pageIndex 分页页码
	 * @return workweek workweek列表的页面包装
	 * 2018年5月11日
	 */
	public Page<WorkWeek> getWorkWeekByCondition(Cmd cmd,Integer pageIndex){
		QWorkWeek qWorkWeek =QWorkWeek.workWeek;
		Predicate predicate = qWorkWeek.workweekId.isNotNull();
		//分页排序
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"subTimes"));
        PageRequest pageRequest = new PageRequest(pageIndex-1,PageAttribute.PAGE_SIZE,sort);
		if (cmd.getStartTime()!=null) {
			predicate = ExpressionUtils.and(predicate, qWorkWeek.workweek.after(cmd.getStartTime()));
		}
		if (cmd.getEndTime()!=null) {
			predicate = ExpressionUtils.and(predicate, qWorkWeek.workweek.before(cmd.getEndTime()));
		}
		if (cmd.getUserId()!=null) {
			predicate = ExpressionUtils.and(predicate, qWorkWeek.userId.eq(cmd.getUserId()));
		}
		return workWeekDao.findAll(predicate, pageRequest);
	}
	
	/**
	 * 
	 * 根据id获取周报
	 * @author 袁子龙（15071746320）
	 * @param workId 周报id
	 * @return workWeek 周报
	 * @date 2018年5月17日
	 */
	public WorkWeek getWorkWeek(Long workId){
		return workWeekDao.findOne(workId);
	}
}

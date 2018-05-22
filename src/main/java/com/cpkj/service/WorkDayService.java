package com.cpkj.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cpkj.cmd.Cmd;
import com.cpkj.constant.PageAttribute;
import com.cpkj.dao.WorkDayDao;
import com.cpkj.dao.WorkDayDetailDao;
import com.cpkj.dao.WorkTempDao;
import com.cpkj.entity.QWorkDay;
import com.cpkj.entity.WorkDay;
import com.cpkj.entity.WorkDayDetailList;
import com.cpkj.entity.WorkTemp;
import com.cpkj.utils.SessionUtil;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
public class WorkDayService extends BaseService{

	@Autowired
	private WorkDayDao workDayDao;
	
	@Autowired
	private WorkDayDetailDao workDayDetailDao;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private WorkTempDao workTempDao;
	
	public Boolean check(WorkDay workDay){
		Date date  = null!=workDay.getSubDay()?workDay.getSubDay():new Date();
		String userId =SessionUtil.instance.getSession(request);
		QWorkDay qWorkDay = QWorkDay.workDay;
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(date);
		Predicate predicate =  qWorkDay.subDay.dayOfYear().eq(calendar.get(Calendar.DAY_OF_YEAR)).and(qWorkDay.userId.eq(userId));
		if (null!=workDay.getWorkDayId()) {
			predicate = ExpressionUtils.and(predicate, qWorkDay.workDayState.eq(1));
		}
		return null!=workDayDao.findOne(predicate)?true:false;
		
		
	}
	
	
	/**
	 * 
	 * 保存日报
	 * @author 袁子龙（15071746320）
	 * @param workDay 日报属性
	 * @return 保存后的日报
	 * 2018年5月10日
	 */
	public WorkDay saveWorkDay(WorkDay workDay){
		String userId =SessionUtil.instance.getSession(request);
		List<WorkDayDetailList> workDayDetailLists = workDay.getWorkDayDetailLists();
		if (null==workDayDetailLists) {
			workDayDetailDao.deleteByWorkDay(workDay);
		}else {
			//workDayDetailDao.deleteByWorkDay(workDay);
			for (WorkDayDetailList workDayDetail : workDayDetailLists) {
				
				WorkDay oldWorkDay = null!=workDay.getWorkDayId()?workDayDao.getOne(workDay.getWorkDayId()):workDay;
				workDayDetail.setWorkDay(oldWorkDay);
				if (workDay.getWorkDayState()==1) {
					WorkTemp oldwWorkTemp = workTempDao.getWorkTempByWorkContent(workDayDetail.getWorkDayContent());
					WorkTemp workTemp = (null==oldwWorkTemp)?new WorkTemp():oldwWorkTemp;
					workTemp.setOnDay(new Date());
					workTemp.setUserId(userId);
					workTemp.setWorkContent(workDayDetail.getWorkDayContent());
					workTemp.setWorkSuccess(workDayDetail.getWorkDaySuccess());
					workTempDao.save(workTemp);
				}
				workDayDetailDao.save(workDayDetail);
			}
			
		}
		workDay.setUserId(userId);
		workDay.setCreateDay(new Date());
		if (null==workDay.getSubDay()) {
			workDay.setSubDay(new Date());
		}
		if (null!=workDay.getWorkDayId()) {
			workDay.setWorkDayDetailLists(null);
		}
		return workDayDao.save(workDay);
	}
	
	
	/**
	 * 
	 * 登录的用户获取日报
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * 2018年5月10日
	 */
	public Page<WorkDay> getWorkDay(Integer pageIndex){
		String userId = SessionUtil.instance.getSession(request);
		QWorkDay qWorkDay =QWorkDay.workDay;
	    Predicate predicate =  qWorkDay.userId.eq(userId);
	  //分页排序
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"subDay"));
        PageRequest pageRequest = new PageRequest(pageIndex-1,PageAttribute.PAGE_SIZE,sort);

	    return  workDayDao.findAll(predicate,pageRequest);
		//return workDayDao.getWorkDays(userId);
	}
	/**
	 * 
	 * 根据id获取日报
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * 2018年5月10日
	 */
	public WorkDay getWorkDayById(Long workDayId){
		
		return  workDayDao.findOne(workDayId);
		//return workDayDao.getWorkDays(userId);
	}
	
	
	/**
	 * 
	 * 根据条件获取日报
	 * @author 袁子龙（15071746320）
	 * @param cmd  条件
	 * @return
	 * 2018年5月10日
	 */
	public Page<WorkDay> getWorkDayByCondition(Cmd cmd,Integer pageIndex){
		QWorkDay qWorkDay = QWorkDay.workDay;
		Predicate predicate = qWorkDay.workDayId.isNotNull();
		//分页排序
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"subDay"));
        PageRequest pageRequest = new PageRequest(pageIndex-1,PageAttribute.PAGE_SIZE,sort);
		if (cmd.getStartTime()!=null) {
			predicate = ExpressionUtils.and(predicate, qWorkDay.createDay.after(cmd.getStartTime()));
		}
		if (cmd.getEndTime()!=null) {
			predicate = ExpressionUtils.and(predicate, qWorkDay.createDay.before(cmd.getEndTime()));
		}
		if (cmd.getUserId()!=null) {
			predicate = ExpressionUtils.and(predicate, qWorkDay.userId.eq(cmd.getUserId()));
		}
		return  workDayDao.findAll(predicate,pageRequest);
	}
}

package com.cpkj.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpkj.constant.PageAttribute;
import com.cpkj.dao.EventDao;
import com.cpkj.dao.UserDao;
import com.cpkj.dao.WorkDayDao;
import com.cpkj.entity.DataCountModel;
import com.cpkj.entity.QEvent;
import com.cpkj.entity.QUser;
import com.cpkj.entity.QWorkDay;
import com.cpkj.entity.QWorkWeek;
import com.cpkj.entity.ShowWork;
import com.cpkj.entity.ShowWorkDayDeatail;
import com.cpkj.entity.ShowWorkWeekDetail;
import com.cpkj.entity.User;
import com.cpkj.entity.WorkDay;
import com.cpkj.entity.WorkWeek;

@Service
@Transactional
public class DataCountService extends BaseService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WorkDayDao workDayDao;
	
	@Autowired
	private EventDao eventDao;
	
	/**
	 * 
	 * 获取数据统计
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return DataCountModel 数据统计
	 * 2018年5月16日
	 */
	public DataCountModel getCountModel(){
		QUser qUser = QUser.user;
	 	Long allNumber =  jpaQueryFactory.select(qUser.userId.count()).from(qUser).fetchOne();
	 	QWorkDay qWorkDay = QWorkDay.workDay;
	 	Long subNumber =  jpaQueryFactory.select(qWorkDay.workDayId.count()).from(qWorkDay).where(qWorkDay.workDayState.eq(1).and(qWorkDay.createDay.dayOfMonth().eq(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)))).fetchOne();
		QEvent qEvent = QEvent.event;
		Long leaveNumber = jpaQueryFactory.select(qEvent.eventId.count()).from(qEvent).where(qEvent.eventState.eq(0).and(qEvent.eventType.eq(1))).fetchOne();
		Long overtimeNumber = jpaQueryFactory.select(qEvent.eventId.count()).from(qEvent).where(qEvent.eventState.eq(0).and(qEvent.eventType.eq(2))).fetchOne();
		DataCountModel countModel = new DataCountModel();
		countModel.setAllNumber(allNumber);
		countModel.setSubNumber(subNumber);
		countModel.setLeaveNumber(leaveNumber);
		countModel.setOvertimeNumber(overtimeNumber);
		Double subRate =  ((double)subNumber/(double)allNumber);
		 DecimalFormat df = new DecimalFormat("#.00"); 
		countModel.setSubRate(Double.valueOf(df.format(subRate)));
		return countModel;
	}
	
	
	public Integer getCountWorkDay(){
		Query query = entityManager.createNativeQuery("SELECT COUNT(*) from ( SELECT DAYOFYEAR(create_day) 				FROM					work_day				GROUP BY					DAYOFYEAR(create_day)	) as allTable");
		return query.getFirstResult();
	}
	
	/**
	 * 
	 * 分页显示工作日报的提交状况
	 * @author 袁子龙（15071746320）
	 * @param pageIndex 页码
	 * @return list<ShowWorkDay> 工作日报显示类的集合
	 * 2018年5月16日
	 */
	public List<ShowWork> getShowWorkDay(Integer pageIndex){
		List<ShowWork> list = new ArrayList<ShowWork>();
//		QWorkDay qWorkDay = QWorkDay.workDay;
//		QUser qUser = QUser.user;
		//List<Integer> days = jpaQueryFactory.select(qWorkDay.createDay.dayOfYear()).from(qWorkDay).groupBy(qWorkDay.createDay.dayOfMonth()).orderBy(qWorkDay.createDay.desc()).fetch();
		//jpaQueryFactory.select(Projections.bean(ShowWorkDay.class, qUser.userId.count().as("subNumber"),qWorkDay.createDay.as("day"),((qUser.userId.count()-qWorkDay.userId.count())/qUser.userId.count()).as("noSubRate"))
		Query query =entityManager.createNativeQuery("SELECT	subTable.create_day,subTable.subNumber,(allTable.allNumber-subTable.subNumber)/allTable.allNumber as noSubRate,allTable.allNumber-subTable.subNumber as noSubNumber FROM	(		SELECT			dayTable.subNumber,			dayTable.create_day		FROM			(				SELECT					COUNT(work_day_state = 1 or null) AS subNumber,					create_day				FROM					work_day				GROUP BY					DAYOFYEAR(create_day)	ORDER BY create_day desc limit "+(pageIndex-1)*PageAttribute.PAGE_SIZE+","+PageAttribute.PAGE_SIZE+"		) AS dayTable 	) as subTable JOIN (	SELECT		COUNT(DISTINCT user_id) as allNumber	FROM		USER) AS allTable");
		List rows =  query.getResultList();
		for (Object row : rows) {
			Object[] cells = (Object[]) row;  
	       /* System.out.println("id = " + cells[0]);  
	        System.out.println("name = " + cells[1]);  
	        System.out.println("age = " + cells[2]);  */
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ShowWork showWork = new ShowWork();
			try {
				showWork.setDay(formatter.parse(cells[0].toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			showWork.setSubNumber(Integer.parseInt(cells[1].toString()));
			showWork.setNoSubRate(Double.valueOf(cells[2].toString()));
			showWork.setNoSubNumber(Integer.parseInt(cells[3].toString()));
			list.add(showWork);
		}
		
		return list;
	}
	
	
	/**
	 * 
	 * 根据时间获取当天的工作日报详情
	 * @author 袁子龙（15071746320）
	 * @param day 时间
	 * @return List<ShowWorkDayDeatail> 工作日报的详情显示集合
	 * 2018年5月16日
	 */
	public List<ShowWorkDayDeatail> getShowWorkDayDeatails(Date day){
		QWorkDay qWorkDay = QWorkDay.workDay;
		List<ShowWorkDayDeatail> showWorkDays = new ArrayList<ShowWorkDayDeatail>();
	//	Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"createDay"));
	  //  PageRequest pageRequest = new PageRequest(pageIndex-1,PageAttribute.PAGE_SIZE,sort);
		//Predicate predicate = qWorkDay.workDayState.eq(1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		//List<Long> workDayIds = jpaQueryFactory.select(qWorkDay.workDayId).from(qWorkDay).where(qWorkDay.createDay.dayOfYear().eq(calendar.get(Calendar.DAY_OF_YEAR))).orderBy(qWorkDay.createDay.desc()).fetch();
		List<WorkDay> workDays = jpaQueryFactory.select(qWorkDay).from(qWorkDay).where(qWorkDay.createDay.dayOfYear().eq(calendar.get(Calendar.DAY_OF_YEAR))).orderBy(qWorkDay.createDay.desc()).fetch();
		for (WorkDay workDay : workDays) {
			User user = userDao.getUserByUserId(workDay.getUserId());
			ShowWorkDayDeatail showWorkDayDeatail = new ShowWorkDayDeatail();
			showWorkDayDeatail.setUserName(user.getUserName());
			showWorkDayDeatail.setWorkDayDetailLists(workDay.getWorkDayDetailLists());
			showWorkDays.add(showWorkDayDeatail);
		}
		return showWorkDays;
	}
	
	
	/**
	 * 
	 * 根据时间获取当天未提交日报的人数
	 * @author 袁子龙（15071746320）
	 * @param day 时间
	 * @return List<String> 未提交日报的人名集合
	 * @date 2018年5月16日
	 */
	public List<String> getUserNameByDate(Date day){
		QWorkDay qWorkDay = QWorkDay.workDay;
		QUser qUser = QUser.user;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(day);
		return jpaQueryFactory.select(qUser.userName).from(qUser).where(qUser.userId.notIn(jpaQueryFactory.select(qWorkDay.userId).from(qWorkDay).where(qWorkDay.createDay.dayOfYear().eq(calendar.get(Calendar.DAY_OF_YEAR))))).fetch();
		
	}

	/**
	 * 
	 * 分页获取周报的信息列表
	 * @author 袁子龙（15071746320）
	 * @param pageIndex 分页页码
	 * @return List<ShowWork> 显示数据集合
	 * @date 2018年5月16日
	 */
	public List<ShowWork> getShowWorkWeek(Integer pageIndex){
		List<ShowWork> list = new ArrayList<ShowWork>();
		Query query =entityManager.createNativeQuery("SELECT	subTable.create_week,subTable.subNumber,(allTable.allNumber-subTable.subNumber)/allTable.allNumber as noSubRate,allTable.allNumber-subTable.subNumber as noSubNumber FROM	(		SELECT			dayTable.subNumber,			dayTable.create_week		FROM			(				SELECT					COUNT(work_week_state = 1 or null) AS subNumber,					create_week				FROM					work_week				GROUP BY					DAYOFYEAR(create_week)	ORDER BY create_week desc limit "+(pageIndex-1)*PageAttribute.PAGE_SIZE+","+PageAttribute.PAGE_SIZE+"		) AS dayTable 	) as subTable JOIN (	SELECT		COUNT(DISTINCT user_id) as allNumber	FROM		USER) AS allTable");
		List rows =  query.getResultList();
		for (Object row : rows) {
			Object[] cells = (Object[]) row;  
	       /* System.out.println("id = " + cells[0]);  
	        System.out.println("name = " + cells[1]);  
	        System.out.println("age = " + cells[2]);  */
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ShowWork showWork = new ShowWork();
			try {
				showWork.setDay(formatter.parse(cells[0].toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			showWork.setSubNumber(Integer.parseInt(cells[1].toString()));
			showWork.setNoSubRate(Double.valueOf(cells[2].toString()));
			showWork.setNoSubNumber(Integer.parseInt(cells[3].toString()));
			list.add(showWork);
		}
		
		return list;
	}
	
	
	public Integer getCountWorkWeek(){
		Query query = entityManager.createNativeQuery("SELECT COUNT(*) from ( SELECT WEEKOFYEAR(create_week) 				FROM					work_week				GROUP BY					WEEKOFYEAR(create_week)	) as allTable");
		return query.getFirstResult();
	}
	
	/**
	 * 
	 * 根据时间获取当周的工作周报详情
	 * @author 袁子龙（15071746320）
	 * @param day 时间
	 * @return List<ShowWorkWeekDetail> 工作周报的详情显示集合
	 * 2018年5月16日
	 */
	public List<ShowWorkWeekDetail> getShowWorkWeekDeatails(Integer week){
		List<ShowWorkWeekDetail> showWorkWeekDetails = new ArrayList<ShowWorkWeekDetail>();
		QWorkWeek qWorkWeek = QWorkWeek.workWeek;
		List<WorkWeek> weeks = jpaQueryFactory.select(qWorkWeek).from(qWorkWeek).where(qWorkWeek.workweek.week().eq(week)).fetch();
		for (WorkWeek workWeek : weeks) {
			ShowWorkWeekDetail showWorkWeekDetail = new ShowWorkWeekDetail();
			User user = userDao.getUserByUserId(workWeek.getUserId());
			showWorkWeekDetail.setUserName(user.getUserName());
			showWorkWeekDetail.setWorkWeekDetailLists(workWeek.getWorkWeekDetailLists());
			showWorkWeekDetails.add(showWorkWeekDetail);
		}
		return showWorkWeekDetails;
	}
	
	
	
	/**
	 * 
	 * 根据当前周数获取本周未提交周报的人数
	 * @author 袁子龙（15071746320）
	 * @param day 时间
	 * @return List<String> 未提交周报的人名集合
	 * @date 2018年5月16日
	 */
	public List<String> getUserNameByWeek(Integer week){
		QWorkWeek qWorkWeek = QWorkWeek.workWeek;
		QUser qUser = QUser.user;
		return jpaQueryFactory.select(qUser.userName).from(qUser).where(qUser.userId.notIn(jpaQueryFactory.select(qWorkWeek.userId).from(qWorkWeek).where(qWorkWeek.workweek.week().eq(week)))).fetch();
		
	}
}

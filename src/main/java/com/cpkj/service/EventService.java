package com.cpkj.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cpkj.cmd.EventCmd;
import com.cpkj.constant.PageAttribute;
import com.cpkj.dao.EventDao;
import com.cpkj.entity.Event;
import com.cpkj.entity.QEvent;
import com.cpkj.entity.QUser;
import com.cpkj.entity.ShowEvent;
import com.cpkj.entity.ShowEventDetail;
import com.cpkj.utils.SessionUtil;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;

@Service
@Transactional
public class EventService extends BaseService {

	@Autowired
	private EventDao eventDao;

	@Autowired
	private HttpServletRequest request;

	/**
	 * 
	 * 保存事件
	 * 
	 * @author 袁子龙（15071746320）
	 * @param
	 * @return 2018年5月11日
	 */
	public Boolean saveEvent(Event event) {
		String userId = SessionUtil.instance.getSession(request);
		event.setCreateUserId(userId);
		event.setEventCreateDate(new Date());
		return null != eventDao.save(event) ? true : false;
	}

	/**
	 * 
	 * 用户获取自己的事件
	 * 
	 * @author 袁子龙（15071746320）
	 * @param pageIndex
	 *            分页的页码
	 * @param type
	 *            查询的类型 1为请假 2为加班
	 * @return event的页面包装
	 * @date 2018年5月11日
	 */
	public Page<Event> getEvent(Integer pageIndex, Integer type) {
		String userId = SessionUtil.instance.getSession(request);
		QEvent qEvent = QEvent.event;
		Predicate predicate = qEvent.createUserId.eq(userId).and(
				qEvent.eventType.eq(type));
		Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,
				"eventCreateDate"));
		PageRequest pageRequest = new PageRequest(pageIndex - 1,
				PageAttribute.PAGE_SIZE, sort);
		return eventDao.findAll(predicate, pageRequest);
	}

	/**
	 * 
	 * 管理员获取事件
	 * 
	 * @author 袁子龙（15071746320）
	 * @param cmd
	 *            事件的条件类
	 * @param pageIndex
	 *            页码
	 * @return event的页面包装
	 * @date 2018年5月11日
	 */
	public Page<Event> getEventByCondition(EventCmd cmd, Integer pageIndex) {
		QEvent qEvent = QEvent.event;
		Predicate predicate = qEvent.eventId.isNotNull();
		if (null != cmd.getStartTime()) {
			predicate = ExpressionUtils.and(predicate,
					qEvent.eventCreateDate.after(cmd.getStartTime()));
		}
		if (null != cmd.getEndTime()) {
			predicate = ExpressionUtils.and(predicate,
					qEvent.eventCreateDate.before(cmd.getEndTime()));
		}
		if (null != cmd.getType()) {
			predicate = ExpressionUtils.and(predicate,
					qEvent.eventType.eq(cmd.getType()));
		}
		if (null != cmd.getUserId()) {
			predicate = ExpressionUtils.and(predicate,
					qEvent.createUserId.eq(cmd.getUserId()));
		}
		if (null != cmd.getState()) {
			predicate = ExpressionUtils.and(predicate,
					qEvent.eventState.eq(cmd.getState()));
		}
		Sort sort = new Sort(Sort.Direction.DESC, "eventCreateDate");
		PageRequest pageRequest = new PageRequest(pageIndex - 1,
				PageAttribute.PAGE_SIZE, sort);
		return eventDao.findAll(predicate, pageRequest);
	}

	/**
	 * 
	 * 管理员处理事件
	 * 
	 * @author 袁子龙（15071746320）
	 * @param eventId
	 *            事件的id
	 * @param HandleType
	 *            事件处理状态 1为同意 2为驳回
	 * @param dealContent
	 *            事件处理备注
	 * @return 保存后的事件 2018年5月11日
	 */
	public Event dealWithEvent(Long eventId, Integer handleType,
			String dealContent) {
		Event event = new Event();
		event.setEventId(eventId);
		event.setEventState(handleType);
		event.setDealContent(dealContent);
		return eventDao.save(event);
	}

	public List<ShowEvent> getShowEvents(Integer pageIndex, Integer type) {
		QEvent qEvent = QEvent.event;
		QUser qUser = QUser.user;
		Predicate predicate = qEvent.createUserId.eq(qUser.userId).and(
				qEvent.eventState.eq(0));
		if (null != type) {
			predicate = ExpressionUtils.and(predicate,
					qEvent.eventType.eq(type));
		}
		return jpaQueryFactory
				.select(Projections.bean(ShowEvent.class,
						qEvent.eventId,
						qEvent.eventCreateDate.as("date"), qUser.userName,
						qEvent.eventType.as("type"))).from(qEvent, qUser)
				.where(predicate).orderBy(qEvent.eventCreateDate.desc())
				.fetch();
	}

	public Long getEventCount(Integer type) {
		QEvent qEvent = QEvent.event;
		Predicate predicate = qEvent.eventState.eq(0);
		if (null != type) {
			predicate = ExpressionUtils.and(predicate,
					qEvent.eventType.eq(type));
		}
		return jpaQueryFactory.select(qEvent.eventId.count()).from(qEvent)
				.where(predicate).fetchCount();
	}

	public ShowEventDetail getEventDetail(Long eventId) {
		QEvent qEvent = QEvent.event;
		QUser qUser = QUser.user;
		return jpaQueryFactory
				.select(Projections.bean(ShowEventDetail.class,
						qEvent.eventContent, qEvent.eventCreateDate,
						qEvent.dayNumber, qUser.userName)).from(qEvent, qUser)
				.where(qEvent.createUserId.eq(qUser.userId).and(qEvent.eventId.eq(eventId))).fetchFirst();
	}

}

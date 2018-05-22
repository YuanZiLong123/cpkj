/*package com.cpkj.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cpkj.constant.PageAttribute;
import com.cpkj.entity.QWorkDay;
import com.cpkj.entity.ShowWork;
import com.cpkj.entity.WorkDay;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MainTest {

	@Autowired
	private EntityManager entityManager;
	
	private JPAQueryFactory jpaQueryFactory;

	@PostConstruct
	public void initFactory() {
		jpaQueryFactory = new JPAQueryFactory(entityManager);
	}
	
	
	@Test
	public void getDataCountTest(){
		Integer pageIndex = 1;
		QWorkDay qWorkDay = QWorkDay.workDay;
		List<ShowWork> showWorkDays = new ArrayList<ShowWork>();
		Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC,"createDay"));
	    PageRequest pageRequest = new PageRequest(pageIndex-1,PageAttribute.PAGE_SIZE,sort);
		Predicate predicate = qWorkDay.workDayState.eq(1);
		List<WorkDay> workDays = jpaQueryFactory.selectFrom(qWorkDay).where(qWorkDay.workDayId.in(jpaQueryFactory.select(qWorkDay.workDayId).from(qWorkDay).groupBy(qWorkDay.createDay.dayOfMonth().eq(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))).limit(10))).fetch();
		System.out.println(workDays.size());
	}
	
}
*/
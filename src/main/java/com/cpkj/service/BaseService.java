package com.cpkj.service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;


public class BaseService {
	@Autowired
	protected EntityManager entityManager;

	protected JPAQueryFactory jpaQueryFactory;

	@PostConstruct
	public void initFactory() {
		jpaQueryFactory = new JPAQueryFactory(entityManager);
	}
}

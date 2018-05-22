package com.cpkj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T, Long>,QueryDslPredicateExecutor<T>{

	
	
}

package com.cpkj.dao;

import org.springframework.stereotype.Repository;

import com.cpkj.entity.User;
@Repository
public interface UserDao extends BaseDao<User>{

	public User getUserByUserId(String userId);
	
	public User getUserByUserPhone(String userPhone);
	
	public User getUserByUserPhoneAndUserPwd(String userPhone,String userPwd);
	
}

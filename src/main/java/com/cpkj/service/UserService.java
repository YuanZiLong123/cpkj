package com.cpkj.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpkj.dao.UserDao;
import com.cpkj.entity.QUser;
import com.cpkj.entity.User;
import com.cpkj.utils.MD5;
import com.cpkj.utils.SessionUtil;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
public class UserService extends BaseService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 
	 * 检测帐号是否已经存在
	 * @author 袁子龙（15071746320）
	 * @param userPhone 用户帐号
	 * @return Boolean true 存在  false 不存在
	 * 2018年5月10日
	 */
	public Boolean checkUserPhone(String userPhone){
		return (null!=userDao.getUserByUserPhone(userPhone))?true:false;
	}
	
	
	/**
	 * 
	 * 登录
	 * @author 袁子龙（15071746320）
	 * @param userAccount 用户帐号  userPwd 用户密码 
	 * @return user 用户
	 * 2018年5月10日
	 */
	public User login(String userAccount,String userPwd){
		//密码md5加密
		userPwd= MD5.getMessageDigest(userPwd.getBytes());
		return userDao.getUserByUserPhoneAndUserPwd(userAccount, userPwd);
	}
	
	/**
	 * 
	 * 注册
	 * @author 袁子龙（15071746320）
	 * @param user 用户的属性
	 * @return user 注册返回的用户
	 * 2018年5月10日
	 */
	public User register(User user){
		user.setUserId(user.getUserPhone());
		user.setUserPwd(MD5.getMessageDigest("123456".getBytes()));
		return userDao.save(user);
	}

	
	/**
	 * 
	 * 修改用户信息
	 * @author 袁子龙（15071746320）
	 * @param user 用户信息
	 * @return user 保存后的用户
	 * 2018年5月10日
	 */
	public User updateUserInfo(User user){
		user.setUserId(SessionUtil.instance.getSession(request));
		return userDao.save(user);
	}
	
	
	public List<User> getUsers(){
		QUser qUser = QUser.user;
		Predicate predicate =  qUser.state.eq(1).and(qUser.role.eq(0));
		return (List<User>) userDao.findAll(predicate);
	}
}

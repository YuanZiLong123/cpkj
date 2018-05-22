package com.cpkj.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cpkj.entity.User;
import com.cpkj.service.UserService;
import com.cpkj.utils.CommonResult;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	/*
	 * 用户登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Object login(String userPhone,String userPwd,HttpServletRequest request){
		CommonResult commonResult = new CommonResult();
		try {
			User user = userService.login(userPhone, userPwd);
			if (null!=user) {
				request.getSession().setAttribute("userId", user.getUserId());
				commonResult.setData(user);
				commonResult.setMessage("登录成功");
			}else {
				commonResult.setMessage("用户名或密码错误");
				commonResult.setState(false);
			}
			
		} catch (Exception e) {
			commonResult.setMessage("登录异常");
			commonResult.setState(false);
		}
		return commonResult;
	}
	
	
	/*
	 * 验证用户电话
	 */
	@RequestMapping("/checkPhone")
	@ResponseBody
	public Object checkPhone(String userPhone){
		CommonResult commonResult = new CommonResult();
		try {
			if (userService.checkUserPhone(userPhone)) {
				commonResult.setState(false);
				commonResult.setMessage("该用户已存在");
			}else {
				commonResult.setMessage("该用户可以注册");
			}
		} catch (Exception e) {
			commonResult.setState(false);
			commonResult.setMessage("帐号验证异常");
		}
		return commonResult;
	}
	
	/*
	 * 用户注册
	 */
	@RequestMapping("/register")
	@ResponseBody
	public Object register(User user){
		CommonResult commonResult = new CommonResult();
		try {
			if (null!=userService.register(user)) {
				commonResult.setMessage("注册成功");
			}else {
				commonResult.setState(false);
				commonResult.setMessage("注册失败");
			}
			
		} catch (Exception e) {
			commonResult.setState(false);
			commonResult.setMessage("注册异常");
		}
		return commonResult;
	}
	
	
	/*
	 * 获取用户列表
	 */
	@RequestMapping("/getUser")
	@ResponseBody
	public Object getUser(){
		CommonResult commonResult = new CommonResult();
		try {
			commonResult.setData(userService.getUsers());
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setState(false);
			commonResult.setMessage("获取数据异常");
		}
		return commonResult;
	}
}

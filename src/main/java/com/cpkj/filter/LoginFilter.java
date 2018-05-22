package com.cpkj.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cpkj.utils.SessionUtil;

@Component
public class LoginFilter implements HandlerInterceptor{

	 @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	            throws Exception {
	        if (StringUtils.isBlank(SessionUtil.instance.getSession(request))) {
				return false;
			}
	        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
	    }

	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	            ModelAndView modelAndView) throws Exception {
	    }

	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
	    }

}

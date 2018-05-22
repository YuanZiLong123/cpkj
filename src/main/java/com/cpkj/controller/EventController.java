package com.cpkj.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cpkj.cmd.EventCmd;
import com.cpkj.entity.Event;
import com.cpkj.service.EventService;
import com.cpkj.utils.CommonResult;

@Controller
@RequestMapping("/event")
public class EventController {

	

	@Autowired
	private EventService eventService;
	
	@Autowired
	private HttpServletRequest request;
	
	/*
	 * 保存事件
	 */
	@RequestMapping("/saveEvent")
	@ResponseBody
	public Object svaeEvent(Event event){
		CommonResult commonResult = new CommonResult();
		try {
			if (null!=eventService.saveEvent(event)) {
				commonResult.setMessage("保存数据成功");
			}else {
				commonResult.setMessage("保存数据失败");
				commonResult.setState(false);
			}
		} catch (Exception e) {
			commonResult.setMessage("保存数据异常");
			commonResult.setState(false);
		}
		return commonResult;
	}

	/*
	 * 用户获取事件
	 */
	@RequestMapping("/getEvent")
	@ResponseBody
	public Object getEvent(@RequestParam(defaultValue="1",required=false)Integer pageIndex,Integer type){
		CommonResult  commonResult = new CommonResult();
		try {
			Page<Event> page = eventService.getEvent(pageIndex, type);
			commonResult.setData(page.getContent());
			commonResult.setTotal(page.getTotalElements());
			commonResult.setTotalPages(page.getTotalPages());
			commonResult.setPageIndex(pageIndex);
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setMessage("获取数据异常");
			commonResult.setState(false);
		}
		return commonResult;
	}
	
	/*
	 * 管理员获取事件
	 */
	@RequestMapping("/getEvenByCondition")
	@ResponseBody
	public Object getEvenByCondition(EventCmd cmd,@RequestParam(defaultValue="1",required=false)Integer pageIndex){
		CommonResult commonResult = new CommonResult();
		try {
			Page<Event> page = eventService.getEventByCondition(cmd, pageIndex);
			commonResult.setData(page.getContent());
			commonResult.setTotal(page.getTotalElements());
			commonResult.setTotalPages(page.getTotalPages());
			commonResult.setPageIndex(pageIndex);
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setMessage("获取数据异常");
			commonResult.setState(false);
		}
		return commonResult;
	}

	
	
}

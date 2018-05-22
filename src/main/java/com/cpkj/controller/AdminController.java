package com.cpkj.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cpkj.constant.PageAttribute;
import com.cpkj.service.DataCountService;
import com.cpkj.service.EventService;
import com.cpkj.utils.CommonResult;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private DataCountService dataCountService;
	
	@Autowired
	private EventService eventService;
	/*
	 * 获取数据统计
	 */
	@RequestMapping("/getDataCount")
	@ResponseBody
	public Object getDataCount(){
		CommonResult commonResult = new CommonResult();
		try {
			commonResult.setData(dataCountService.getCountModel());
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setMessage("获取数据异常");
			commonResult.setState(false);
		}
		return commonResult; 
	}
	
	/*
	 * 获取日报提交的列表
	 */
	@RequestMapping("/getShowWorkDay")
	@ResponseBody
	public Object getShowWorkDay(@RequestParam(defaultValue="1",required=false)Integer pageIndex){
		CommonResult commonResult = new CommonResult();
		try {
			Integer all = dataCountService.getCountWorkDay();
			commonResult.setData(dataCountService.getShowWorkDay(pageIndex));
			commonResult.setPageIndex(pageIndex);
			commonResult.setTotal(all.longValue());
			commonResult.setTotalPages((int)Math.round(all.doubleValue()/PageAttribute.PAGE_SIZE));
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setMessage("获取数据异常");
			commonResult.setState(false);
		}
		return commonResult; 
	}
	
	/*
	 * 根据时间获取日报的详情
	 */
	@RequestMapping("/getWorkDayDetail")
	@ResponseBody
	public Object getWorkDayDetail(Date day){
		CommonResult commonResult = new CommonResult();
		try {
			commonResult.setData(dataCountService.getShowWorkDayDeatails(day));
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setMessage("获取数据异常");
			commonResult.setState(false);
		}
		return commonResult; 
	}
	
	
	/*
	 * 根据时间获取当天未提交日报的人
	 */
	@RequestMapping("/getNoSubUserName")
	@ResponseBody
	public Object getNoSubUserName(Date day){
		CommonResult commonResult = new CommonResult();
		try {
			commonResult.setData(dataCountService.getUserNameByDate(day));
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setState(false);
			commonResult.setMessage("获取数据失败");
		}
		return commonResult;
	}
	
	
	
	/*
	 * 获取周报提交的列表
	 */
	@RequestMapping("/getShowWorkWeek")
	@ResponseBody
	public Object getShowWorkWeek(@RequestParam(defaultValue="1",required=false)Integer pageIndex){
		CommonResult commonResult = new CommonResult();
		try {
			commonResult.setData(dataCountService.getShowWorkWeek(pageIndex));
			Integer all = dataCountService.getCountWorkDay();
			commonResult.setPageIndex(pageIndex);
			commonResult.setTotal(all.longValue());
			commonResult.setTotalPages((int)Math.round(all.doubleValue()/PageAttribute.PAGE_SIZE));
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setMessage("获取数据异常");
			commonResult.setState(false);
		}
		return commonResult; 
	}
	
	/*
	 * 根据周获取周报的详情
	 */
	@RequestMapping("/getWorkWeekDetail")
	@ResponseBody
	public Object getWorkWeekDetail(Integer week){
		CommonResult commonResult = new CommonResult();
		try {
			commonResult.setData(dataCountService.getShowWorkWeek(week));
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setMessage("获取数据异常");
			commonResult.setState(false);
		}
		return commonResult; 
	}
	
	/*
	 * 根据周数获取当周未提交周报的人
	 */
	@RequestMapping("/getWeekNoSub")
	@ResponseBody
	public Object getWeekNoSub(Integer week){
		CommonResult commonResult = new CommonResult();
		try {
			commonResult.setData(dataCountService.getUserNameByWeek(week));
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setState(false);
			commonResult.setMessage("获取数据失败");
		}
		return commonResult;
	}

	
	@RequestMapping("/getShowEvent")
	@ResponseBody
	public Object getShowEvent(@RequestParam(defaultValue="1",required=false)Integer pageIndex,Integer type){
		CommonResult commonResult = new CommonResult();
		try {
			Long  total= eventService.getEventCount(type);
			commonResult.setPageIndex(pageIndex);
			commonResult.setTotal(total);
			commonResult.setTotalPages((int)Math.round(total.doubleValue()/PageAttribute.PAGE_SIZE));
			commonResult.setData(eventService.getShowEvents(pageIndex,type));
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setState(false);
			commonResult.setMessage("获取数据失败");
		}
		return commonResult;
	}

	@RequestMapping("/getEventById")
	@ResponseBody
	public Object getEventById(Long eventId){
		CommonResult commonResult = new CommonResult();
		try {
			commonResult.setData(eventService.getEventDetail(eventId));
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setState(false);
			commonResult.setMessage("获取数据失败");
		}
		return commonResult;
	}

	/*
	 * 管理员处理事件
	 */
	@RequestMapping("/dealWith")
	@ResponseBody
	public Object dealWith(Long eventId,Integer handleType,String dealContent){
		CommonResult commonResult = new CommonResult();
		try {
			if (null!=eventService.dealWithEvent(eventId, handleType,dealContent)) {
				commonResult.setMessage("处理事件成功");
			}else {
				commonResult.setState(false);
				commonResult.setMessage("处理事件失败");
			}
		} catch (Exception e) {
			commonResult.setState(false);
			commonResult.setMessage("处理事件异常");
		}
		return commonResult;
	}
}

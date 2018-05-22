package com.cpkj.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.cpkj.cmd.Cmd;
import com.cpkj.entity.WorkDay;
import com.cpkj.entity.WorkDayDetailList;
import com.cpkj.entity.WorkWeek;
import com.cpkj.entity.WorkWeekDetailList;
import com.cpkj.formatEntity.FormatWorkDayDetail;
import com.cpkj.formatEntity.FormatWorkWeekDetail;
import com.cpkj.service.WorkDayService;
import com.cpkj.service.WorkTempService;
import com.cpkj.service.WorkWeekService;
import com.cpkj.utils.CommonResult;

@Controller
@RequestMapping("/work")
public class WorkController {

	@Autowired
	private WorkDayService workDayService;
	
	@Autowired
	private WorkWeekService workWeekService;
	
	@Autowired
	private WorkTempService workTempService;
	
	

	@RequestMapping("/gowork")
	public String gowork(){
		return "work";
	}
	
	
	/*
	 * 保存或提交日报
	 */
	@RequestMapping(path="/saveWorkDay",method=RequestMethod.POST)
	@ResponseBody
	public Object saveWorkDday(WorkDay workDay,String json){
		CommonResult commonResult = new CommonResult();
		try {
				if (workDayService.check(workDay) ){
					commonResult.setMessage("你保存或提交了当天日报");
					commonResult.setState(false);
					return commonResult;
				}
			
			List<WorkDayDetailList> workDayDetailLists = JSONArray.parseArray(json, WorkDayDetailList.class);
			if (workDayDetailLists.size()>0) {
				workDay.setWorkDayDetailLists(workDayDetailLists);
			}
			if (null!=workDayService.saveWorkDay(workDay)) {
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
	 * 保存或提交日报
	 */
	@RequestMapping(path="/saveWorkDay2",method=RequestMethod.POST)
	@ResponseBody
	public Object saveWorkDday2(WorkDay workDay,FormatWorkDayDetail formatWorkDayDetail){
		CommonResult commonResult = new CommonResult();
		try {
			if (formatWorkDayDetail.getWorkDayDetailLists().size()>0) {
				workDay.setWorkDayDetailLists(formatWorkDayDetail.getWorkDayDetailLists());
			}
			if (null!=workDayService.saveWorkDay(workDay)) {
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
	 * 保存或提交周报
	 */
	@RequestMapping("/saveWorkWeek")
	@ResponseBody
	public Object saveWorkWeek(WorkWeek workWeek,String json){
		CommonResult commonResult = new CommonResult();
		try {
			if (workWeekService.check(workWeek) ){
				commonResult.setMessage("你保存或提交了本周周报");
				commonResult.setState(false);
				return commonResult;
			}
		
		List<WorkWeekDetailList> workWeekDetailLists = JSONArray.parseArray(json, WorkWeekDetailList.class);
			workWeek.setWorkWeekDetailLists(workWeekDetailLists);;
			if (null!=workWeekService.saveWorkWeek(workWeek)) {
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
	 * 获取日报列表
	 */
	@RequestMapping("/getWorkDay")
	@ResponseBody
	public Object getWorkDayList(@RequestParam(defaultValue="1",required=false)Integer pageIndex){
		CommonResult commonResult = new CommonResult();
		try {
			Page<WorkDay> page = workDayService.getWorkDay(pageIndex);
			commonResult.setData(page.getContent());
			commonResult.setTotal(page.getTotalElements());
			commonResult.setPageIndex(pageIndex);
			commonResult.setTotalPages(page.getTotalPages());
			commonResult.setMessage("获取日报成功");
		} catch (Exception e) {
			commonResult.setMessage("获取日报异常");
			commonResult.setState(false);
		}
		return commonResult;
	}
	
	/*
	 * 获取日报列表
	 */
	@RequestMapping("/getWorkDayById")
	@ResponseBody
	public Object getWorkDayById(Long workId){
		CommonResult commonResult = new CommonResult();
		try {
			commonResult.setData(workDayService.getWorkDayById(workId));
			commonResult.setMessage("获取日报成功");
		} catch (Exception e) {
			commonResult.setMessage("获取日报异常");
			commonResult.setState(false);
		}
		return commonResult;
	}
	
	/*
	 * 管理员获取日报
	 */
	@RequestMapping("/getWorkDayByCondition")
	@ResponseBody
	public Object getWorkDayListByCondition(Cmd cmd,@RequestParam(defaultValue="1",required=false)Integer pageIndex){
		CommonResult commonResult = new CommonResult();
		try {
			commonResult.setData(workDayService.getWorkDayByCondition(cmd,pageIndex));
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setState(false);
			commonResult.setMessage("获取数据异常");
		}
		return commonResult;
	}
	
	@RequestMapping("/getWorkWeek")
	@ResponseBody
	public Object getWorkWeek(@RequestParam(defaultValue="1",required=false)Integer pageIndex){
		CommonResult commonResult = new CommonResult();
		try {
			Page<WorkWeek> page = workWeekService.getWrokWeekList(pageIndex);
			commonResult.setData(page.getContent());
			commonResult.setTotal(page.getTotalElements());
			commonResult.setTotalPages(page.getTotalPages());
			commonResult.setPageIndex(pageIndex);
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setState(false);
			commonResult.setMessage("获取数据异常");
		}
		return commonResult;
	}
	
	@RequestMapping("/getWorkWeekByCondition")
	@ResponseBody
	public Object getWorkWeekByCondition(Cmd cmd,@RequestParam(defaultValue="1",required=false)Integer pageIndex){
		CommonResult commonResult = new CommonResult();
		try {
			Page<WorkWeek> page = workWeekService.getWorkWeekByCondition(cmd, pageIndex);
			commonResult.setData(page.getContent());
			commonResult.setTotal(page.getTotalElements());
			commonResult.setTotalPages(page.getTotalPages());
			commonResult.setPageIndex(pageIndex);
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setState(false);
			commonResult.setMessage("获取数据异常");
		}
		return commonResult;
	}
	
	/*
	 * 获取周报列表
	 */
	@RequestMapping("/getWorkWeekById")
	@ResponseBody
	public Object getWorkWeekById(Long workId){
		CommonResult commonResult = new CommonResult();
		try {
			commonResult.setData(workWeekService.getWorkWeek(workId));
			commonResult.setMessage("获取周报成功");
		} catch (Exception e) {
			commonResult.setMessage("获取周报异常");
			commonResult.setState(false);
		}
		return commonResult;
	}
	
	
	/*
	 * 获取即将生成日报
	 */
	@RequestMapping("/getWorkTemp")
	@ResponseBody
	public Object getWorkTemp(Integer type){
		CommonResult commonResult = new CommonResult();
		try {
			if (type==0) {
				commonResult.setData(workTempService.getWorkDayTemps());
			}else {
				commonResult.setData(workTempService.getWorkWeekTemps());
			}
			commonResult.setMessage("获取数据成功");
		} catch (Exception e) {
			commonResult.setState(false);
			commonResult.setMessage("获取数据异常");
		}
		return commonResult;
	}

	

}



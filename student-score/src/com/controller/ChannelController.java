package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.model.Academe;
import com.model.Channel;
import com.model.StuClass;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.ChannelService;
import com.util.AjaxObject;
import com.util.Const;


@Controller
/*
 * 该类用于跳转页面以及跳转成功后加载菜单
 * AjaxObject类是返回前端是带取的一些必要信息,包括代码运行状态(成功或失败),成功或者失败原因,返回前端的主要数据
 * 
 */


public class ChannelController {

	private ChannelService channelService;
	
	@Resource(name="channelService")
	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}
	

	@RequestMapping(value="/manage",method=RequestMethod.GET)
	public String ManageIndex() {
		return "manage";
	}
	



	@RequestMapping(value="/getChannels",method=RequestMethod.POST)
	@ResponseBody
	//获取菜单课程下各科目名
	public AjaxObject getChannels(){
		List<Channel> channels = null;
		try {
			channels = channelService.getChannels();
		} catch (Exception e) {
			
			return new AjaxObject(Const.STATUS400, "获取栏目失败", null);
		}
		Map<String, String> channelidsAndNames = new HashMap<String, String>();
		for(Channel t : channels){
			
			channelidsAndNames.put(t.getId()+"",t.getName());
		}
	
		return new AjaxObject(Const.STATUS200, "获取栏目成功", channelidsAndNames);
	}
	
	@RequestMapping(value="/getClasses",method=RequestMethod.POST)
	@ResponseBody
	//获取菜单班级下各班级信息
	public AjaxObject getClasses(){
		
		List<StuClass> classes = null;
		try {
			classes = channelService.getClasses();
		} catch (Exception e) {
			
			return new AjaxObject(Const.STATUS400, "获取栏目失败", null);
		}
		
		
		HashMap<String,List<StuClass>> academeMap = new HashMap<String, List<StuClass>>();
		for( int i=0;i<classes.size();i++){
			StuClass a = classes.get(i);
			if(academeMap.containsKey(a.getAcademeName())){
				academeMap.get(a.getAcademeName()).add(a);
			}
			else {
				List<StuClass> list = new ArrayList<StuClass>();
				list.add(a);
				academeMap.put(a.getAcademeName(), list);
			}
		}
	
		return new AjaxObject(Const.STATUS200, "获取班级栏目成功", academeMap);
	}
}

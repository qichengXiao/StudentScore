package com.service.impl;

import java.util.List;

import javax.annotation.Resource;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ChannelDao;
import com.model.StuClass;


import com.service.ChannelService;


@Service("channelService")
//service.impl的实现注解 请看service接口
public class ChannelServiceImpl implements ChannelService {
	
	private ChannelDao channelDao;
	
	@Resource(name="channelDao")
	public void setChannelDao(ChannelDao channelDao) 
	{
		this.channelDao = channelDao;
	}

	@Transactional
	public List getChannels()
	{
		return channelDao.getChannels();
	}
	
	@Transactional
	public List<StuClass> getClasses()
	{
		return channelDao.getClasses();
	}
	
}

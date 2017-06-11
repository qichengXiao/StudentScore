package com.service;

import java.util.ArrayList;
import java.util.List;

import com.model.Channel;
import com.model.StuClass;

public interface ChannelService {
	//获取科目名
	List<Channel> getChannels();
	//获取班级信息
	List<StuClass> getClasses();
}




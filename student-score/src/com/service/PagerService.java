package com.service;

import java.util.ArrayList;
import java.util.List;

import com.model.Channel;
import com.util.Pager;

public interface PagerService {
	//封装页数、主要是取得记录总数
	Pager getPager(int curpage, int pagesize, String string, String id);

}




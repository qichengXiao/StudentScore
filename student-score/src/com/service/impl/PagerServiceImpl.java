package com.service.impl;

import java.util.List;

import javax.annotation.Resource;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.model.Channel;
import com.service.*;
import com.util.Pager;
import com.dao.*;



@Service("pagerService")
//service.impl的实现注解 请看service接口
public class PagerServiceImpl implements PagerService {
	
	private CourseDao courseDao;
	private StudentDao studentDao;
	
	@Resource(name="courseDao")
	public void setCourseDao(CourseDao courseDao) 
	{
		this.courseDao = courseDao;
	}
	
	@Resource(name="studentDao")
	public void setStudentDao(StudentDao studentDao) 
	{
		this.studentDao = studentDao;
	}
	//计算记录条数和总页数
	@Transactional
	public Pager getPager(int curpage, int pagesize, String flag, String id) {
		Pager pager = new Pager(curpage, pagesize);
		switch (flag) {
		case "course":
			pager.setTotalCount(courseDao.getScoreCount(id));

			break;
		case "class":
			pager.setTotalCount(studentDao.getStudentCount(id));

			break;

		default:
			break;
		}
		//计算总页数
		pager.setStartItem((pager.getCurrPage() - 1) * pager.getPageSize());
		if (pager.getTotalCount() % pager.getPageSize() == 0) {
			pager.setTotalPage(pager.getTotalCount() / pager.getPageSize());
		} else {
			pager.setTotalPage(pager.getTotalCount() / pager.getPageSize() + 1);
		}

		return pager;
	}
	
	
}

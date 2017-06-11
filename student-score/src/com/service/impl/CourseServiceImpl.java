package com.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CourseDao;


import com.model.Score;
import com.service.CourseService;
import com.util.Pager;


@Service("courseService")
//service.impl的实现注解 请看service接口
public class CourseServiceImpl implements CourseService {
	
	private CourseDao courseDao;
	
	@Resource(name="courseDao")
	public void setCourseDao(CourseDao courseDao) 
	{
		this.courseDao = courseDao;
	}

	@Transactional
	public ArrayList<Score> getOneCourseScore(String courseId,Pager pager)
	{	
		   
		return courseDao.getOneCourseScore(courseId,pager);
	}
	
}

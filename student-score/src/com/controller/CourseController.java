package com.controller;

import java.util.ArrayList;

import javax.annotation.Resource;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.model.Score;
import com.model.Student;
import com.service.CourseService;
import com.service.PagerService;
import com.service.ScoreService;
import com.service.StudentService;

import com.util.AjaxObject;
import com.util.Const;
import com.util.PageAjaxObject;
import com.util.Pager;


@Controller
/*
 * 该Controller用于有关课程的跳转
 * PageAjaxObject类是返回前端是带取的一些必要信息,
 * 包括代码运行状态(成功或失败),成功或者失败原因,当前页，信息总数，总页数返回前端的主要数据
 * 
 */
public class CourseController {
	private CourseService courseService;
	private PagerService pagerService;
	private StudentService studentService;
	private ScoreService scoreService;
	
	@Resource(name="courseService")		
	public void setCourseService(CourseService courseService) 
	{
		this.courseService = courseService;
	}
	@Resource(name="pagerService")		
	public void setPagerService(PagerService pagerService) 
	{
		this.pagerService = pagerService;
	}
	@Resource(name="studentService")		
	public void setStudentService(StudentService studentService) 
	{
		this.studentService = studentService;
	}
	@Resource(name="scoreService")		
	public void setScoreService(ScoreService scoreService) 
	{
		this.scoreService = scoreService;
	}
	
	@RequestMapping(value="oneCourseScores",method=RequestMethod.POST)
	@ResponseBody
	//获取一个班级的各个学生的成绩。
	public PageAjaxObject getOneCourseScores(@RequestParam("channelId") String id,@RequestParam("currentPage") int  curpage,@RequestParam("pageSize") int pagesize){
	
		Pager pager=pagerService.getPager(curpage,pagesize,"course",id);
		ArrayList<Score> list = courseService.getOneCourseScore(id,pager);
		
		return new PageAjaxObject(Const.STATUS200, "成功获取科目成绩",  pager.getCurrPage(), pager.getTotalPage(),pager.getTotalCount(), list);
	
	}
	
	@RequestMapping(value="addOneCoureseScore")
	@ResponseBody
	//添加单科成绩
	public AjaxObject addOneCoureseScore(@RequestParam("courseName") String courseName,@RequestParam("stuId") String stuId,@RequestParam("mark") float mark,@RequestParam("stuName") String stuName){
		Student stu = studentService.getStudent(stuId,stuName);
		if(stu!=null){
			Score s  = new Score(null, courseName, mark, stu);
			if(!scoreService.addOneScore(s)){
				//查找到student，但是sesion.save出错 没有储存记录
				return new AjaxObject(Const.STATUS200, "添加成绩记录失败",  null);	
			}
			
				return new AjaxObject(Const.STATUS400, "成功添加成绩记录",  null);
		}
		else{
			
			return new AjaxObject(Const.STATUS200, "添加成绩记录失败,查找不到该学生",  null);	
		}
	}
	

}

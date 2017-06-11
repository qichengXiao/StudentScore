package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.model.Score;
import com.model.StuClass;
import com.model.Student;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.ChannelService;
import com.service.PagerService;
import com.service.ScoreService;
import com.service.StudentService;

import com.util.AjaxObject;
import com.util.Const;
import com.util.PageAjaxObject;
import com.util.Pager;

//这是一个关于学生操作的跳转的类
@Controller
public class StudentController {

	StudentService studentService;
	PagerService pagerService;
	ChannelService channelService;
	private ScoreService scoreService;
	
		
	@Resource(name="studentService")
	public void setStudentService(StudentService studentService) 
	{
		this.studentService = studentService;
	}
	@Resource(name="pagerService")
	public void setPagerService(PagerService pagerService) 
	{
		this.pagerService = pagerService;
	}
	@Resource(name="scoreService")
	public void setScoreService(ScoreService scoreService) 
	{
		this.scoreService = scoreService;
	}
	
	@Resource(name="channelService")
	public void setChannelService(ChannelService channelService) 
	{
		this.channelService = channelService;
	}

	@RequestMapping(value="/oneClassScores",method=RequestMethod.POST)
	@ResponseBody
	//获取一个班级的各个学生的成绩
	public PageAjaxObject getOneClassScores(@RequestParam("channelId") String id,
			@RequestParam("currentPage") int  curpage,@RequestParam("pageSize") int pagesize){
		Pager pager=pagerService.getPager(curpage,pagesize,"class",id);	
		List list = studentService.getOneClassScores(id,pager);
		return new PageAjaxObject(Const.STATUS200, "获取班级所有信息成功",  pager.getCurrPage(), pager.getTotalPage(),pager.getTotalCount(), list);
	}
	
	
	/* 例如data:
	*   classId='123456'
	*	stuId='333'
	*	ScoreMap=['语文':89.9,'英语':99.9,'数学':88]
	*	
	*	添加一个学生
	*/
	@RequestMapping(value="/addStudent",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObject addStudent(@RequestParam("classId") String classId,@RequestParam("name") String name,
			@RequestParam("stuId") String  stuId,@RequestParam("scores") String scores){
	System.out.println("进入addStudent"+stuId+name+classId+scores);

	Student stu = new Student();
	stu.setId(stuId);
	stu.setName(name);
	studentService.addStudent(classId,stu);
	scoreService.saveStringScores(stu,scores);
	return new AjaxObject(Const.STATUS200, "获取班级所有信息成功", null);
	}
	
	
	@RequestMapping(value="/upadateStudent",method=RequestMethod.POST)
	@ResponseBody
	//更新学生信息
	public AjaxObject upadateStudent(@RequestParam("classId") String classId,@RequestParam("name") String name,
			@RequestParam("stuId") String  stuId,@RequestParam("scores") String scores){
		System.out.println("进入updataStudent"+stuId+name+classId+scores);

		Student stu = new Student();	
		stu.setId(stuId);
		stu.setName(name);
		StuClass stuClass = studentService.findClass(classId);
		stu.setStuClass(stuClass);
		studentService.updateStudent(stu);
		scoreService.updateStringScores(stu,scores);
	
		
		return new AjaxObject(Const.STATUS200, "获取班级所有信息成功", null);
	}
	
	
	@RequestMapping(value="/addClass",method=RequestMethod.POST)
	@ResponseBody
	//添加班级
	public AjaxObject addClass(@RequestParam("classId") String classId,@RequestParam("className") String className,@RequestParam("academeName") String academeName){
		
		boolean flag = studentService.addClass(classId,className,academeName);
		if(flag){
			System.out.println("flag"+flag);
			return new AjaxObject(Const.STATUS200, "添加班级成功", null);
		}
		else
			return  new AjaxObject(Const.STATUS400, "获取班级失败, 请检查班级号和班级名称是否已有", null);
	}
	
	@RequestMapping(value="/deleteStudent",method=RequestMethod.POST)
	@ResponseBody
	//删除学生
	public AjaxObject deleteStudent(@RequestParam("stuId") String stuId){
		
		boolean flag = studentService.deleteStudent(stuId);
	
		if(flag){
			System.out.println("flag"+flag);
			return new AjaxObject(Const.STATUS200, "删除成功", null);
		}
		else
			return  new AjaxObject(Const.STATUS400, "删除失败，找不到该学号", null);

	}
	
	@RequestMapping(value="/searchStudentById",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObject searchStudentById(@RequestParam("stuId") String stuId){
		System.out.println("进入searchstudent");
		Student stu = studentService.searchStudenyById(stuId);
		
		if(stu==null)
			return new AjaxObject(Const.STATUS400, "找不到该学号", null);
 
		List list  = scoreService.getScores(stu);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("stu", stu);
		map.put("scoreList", list);
		System.out.println(map);
		return	new AjaxObject(Const.STATUS200, "查找成功", map);
	}
	
	
	@RequestMapping(value="/searchStudentByClassName",method=RequestMethod.POST)
	@ResponseBody
	//按班级名称搜索班级
	public AjaxObject searchStudentByClassName(@RequestParam("className") String className){
		StuClass c = studentService.findClassByName(className);
		System.out.println("class:"+c);
		if(c==null){
			return new AjaxObject(Const.STATUS400, "找不到该班级", null);
		}
		
		return new AjaxObject(Const.STATUS200, "查找成功", c.getId());

	}
	
	
	@RequestMapping(value="/oneClassScoresByOrder",method=RequestMethod.POST)
	@ResponseBody
	//将一个班级的成绩排序
	public PageAjaxObject oneClassScoresByOrder(@RequestParam("classId") String classId,
			@RequestParam("currentPage") int  curpage,@RequestParam("pageSize") int pagesize,@RequestParam("orderType") int orderType){
		Pager pager=pagerService.getPager(curpage,pagesize,"class",classId);
		
		List list = studentService.getOneClassScoresByOrder(classId,pager,orderType);
		System.out.println(list);
		return new PageAjaxObject(Const.STATUS200, "获取班级所有信息成功",  pager.getCurrPage(), pager.getTotalPage(),pager.getTotalCount(), list);
	}

	
	@RequestMapping(value="/class-condition",method=RequestMethod.POST)
	@ResponseBody
	//获取所有班级(优秀率、及格率)
	public AjaxObject classCondition(){
		
		List<StuClass> classes =  channelService.getClasses();
		List<String> result = new ArrayList<String>();
		for(int i =0;i<classes.size();i++){
			StuClass c =classes.get(i);
			String t =studentService.getClassCondition(c);
			System.out.println(t);
			result.add(t);
			
		}
				
		return new AjaxObject(Const.STATUS200, "获取班级所有信息成功",   result);
	}

	
	
	
}

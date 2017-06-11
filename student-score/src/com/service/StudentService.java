package com.service;

import java.util.HashMap;
import java.util.List;

import com.model.StuClass;
import com.model.Student;
import com.util.Pager;



public interface StudentService {
	//获取一个班级的各个学生的成绩
	List<Student> getOneClassScores(String id, Pager pager);
	//添加学生
	boolean addStudent(String classId, Student stu);
	//获取一个学生信息,并验证stuId和stuName是否匹配
	Student getStudent(String stuId, String stuName);
	//添加班级
	boolean addClass(String classId,String className, String academeName);
	//查找班级
	StuClass findClass(String classId);
	//更新学生信息
	void updateStudent(Student stu);
	//删除学生
	boolean deleteStudent(String stuId);
	//通过学生学号查找学生信息
	Student searchStudenyById(String stuId);
	//通过班级名称查找班级
	StuClass findClassByName(String className);
	//将一个班级的学生进行各种排序
	List getOneClassScoresByOrder(String classId, Pager pager, int orderType);
	//计算一个班级的优秀率和及格率
	String getClassCondition(StuClass stuClass);
}
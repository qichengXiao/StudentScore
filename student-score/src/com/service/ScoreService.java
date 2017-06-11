package com.service;


import java.util.ArrayList;
import java.util.List;

import com.model.Channel;
import com.model.Score;
import com.model.Student;
import com.util.Pager;

public interface ScoreService {
	//保存单科成绩
	public void saveScore(String courseName , Student stu , float mark);
	//保存多科成绩
	public void saveStringScores(Student stu, String scores);
	//添加单科成绩
	public boolean addOneScore(Score s);
	//更新多科成绩
	public void updateStringScores(Student stu, String scores);
	//获取一个学生的所有成绩
	public List getScores(Student stu);
	
}




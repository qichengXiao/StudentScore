package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.model.Score;
import com.model.Student;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ScoreDao;


import com.service.ScoreService;

@Service("scoreService")
//service.impl的实现注解 请看service接口
public class ScoreServiceImpl implements ScoreService {
	
	private ScoreDao scoreDao;
	
	@Resource(name="scoreDao")
	public void setScoreDao(ScoreDao scoreDao) 
	{
		this.scoreDao = scoreDao;
	}

	//保存特定学生的单科成绩
	public void saveScore(String courseName, Student stu, float mark) {
		Score s = new Score(null, courseName, mark, stu);
		
		scoreDao.saveScore(s);
	}
	
	@Transactional
	//保存特定学生的所有成绩,scores数据类似:语文,88,数据47，英语99
	
	public void saveStringScores(Student stu, String scores){
		String[] scoreArray=scores.split(",");
		for(int i=0;i<scoreArray.length-1;i=i+2){
			System.out.println("save:"+scoreArray[i]+""+scoreArray[i+1]);
			saveScore(scoreArray[i],stu,Float.parseFloat(	scoreArray[i+1]) ) ;
		}
	}

	//添加单科成绩
	@Transactional
	public boolean addOneScore(Score s) {
		
		return scoreDao.saveScore(s);
	}

	//更新学生各科成绩
	@Transactional
	public void updateStringScores(Student stu, String scores) {
		
		String[] scoreArray=scores.split(",");
		for(int i=0;i<scoreArray.length-1;i=i+2){
			System.out.println("update:"+scoreArray[i]+""+scoreArray[i+1]);
			scoreDao.updateScore(scoreArray[i],stu,Float.parseFloat(scoreArray[i+1]) ) ;
		}
	}

	//获取学生成绩
	@Transactional
	public List getScores(Student stu) {
		 return scoreDao.getScoresByStu(stu);
		
	}


	
	
}

package com.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.model.Score;
import com.model.Student;
@Repository("scoreDao")
//该持久层用与数据库操作与成绩记录有关的数据
public class ScoreDao {
	SessionFactory sessionFactory;

	//因为我们在applicationContext-db.xml文件里配置了sessionFactory这个bean，系统初始化时会实例化这个bean，所以这里可以自动注入到我们声明的变量
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}
	//保存一条成绩记录
	public boolean saveScore(Score s) {
		 Session session = sessionFactory.getCurrentSession();
		 Object o =  session.save(s);
		 if(o==null) 
			 return false;
		 else 
			 return true;
	}
	//更新一条成绩记录
	public void updateScore(String courseName, Student stu, float mark) {
		System.out.println(stu.getId()+courseName+mark);
		Session session = sessionFactory.getCurrentSession();
	//	List ScoreList =  session.createSQLQuery("select id from Score  where stu_id=?").setString(0,stu.getId()).list();
		@SuppressWarnings("unchecked")
		List<Score> ScoreList =	session.createQuery("from Score s where s.stu=?").setEntity(0, stu).list();
		for(int i=0;i<ScoreList.size();i++){
			if(ScoreList.get(i).getCourseName().equals(courseName)){
				ScoreList.get(i).setMark(mark);
			}
		}
	}
	//通过学生学号寻找该生成绩
	public List getScoresByStu(Student stu) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("From Score s where s.stu=?").setEntity(0, stu).list();
	}

	
}

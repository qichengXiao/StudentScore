package com.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.model.Score;
import com.model.Channel;
import com.util.Pager;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


@Repository("courseDao")
//该持久层用与数据库操作与课程有关的数据
public class CourseDao
{
	SessionFactory sessionFactory;

	//因为我们在applicationContext-db.xml文件里配置了sessionFactory这个bean，系统初始化时会实例化这个bean，所以这里可以自动注入到我们声明的变量
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}
	
	//获取一个课程中的所有学生的成绩
	public ArrayList<Score> getOneCourseScore(String courseId,Pager pager) {
		Session session=sessionFactory.getCurrentSession(); //拿到前的hibernate会话session	
		Channel  c= (Channel)session.get(Channel.class, Integer.parseInt(courseId));
		List<Score> oneCourseScoresList =(List<Score>) session.createQuery("from Score score where score.courseName=?")
				.setString(0, c.getName())
				.setMaxResults(pager.getPageSize())
				.setFirstResult((pager.getCurrPage()-1)*pager.getPageSize()).list();	
		System.out.println(oneCourseScoresList);
		return (ArrayList<Score>) oneCourseScoresList;
	}
	//计算该科目的总记录数
	public int getScoreCount(String courseId) {
	
		String hql = "select count(*) from Score  where courseName =(select name from  channel where id ="+Integer.parseInt(courseId)+")";  
		System.out.println(hql);
		Session session = sessionFactory.getCurrentSession();
	        
	        BigInteger count = (BigInteger)session.createSQLQuery(hql).list().listIterator().next();  
	      System.out.println(  count.intValue());
	        return  count.intValue();  
	    
	}	
	
}
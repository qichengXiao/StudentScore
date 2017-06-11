package com.dao;


import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.Query;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Channel;
import com.model.Score;
import com.model.StuClass;
import com.model.Student;
import com.util.Pager;





@Repository("studentDao")
//该持久层用与数据库操作与学生有关的数据
public class StudentDao
{
	SessionFactory sessionFactory;


	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}
	//获取一个班级的成绩
	public List<Student> getOneClassScores(String id, Pager pager)   {
		Session session=sessionFactory.getCurrentSession(); 
		//先找到所有学生
		List<Student> studentList  = session.createQuery("From Student s where stuClass_id=?").setString(0, id)
				.setFirstResult((pager.getCurrPage()-1)*pager.getPageSize()	)
				.setMaxResults(pager.getPageSize())
				.list();
		//将找出来的成绩记录封装到学生的scoreMap里面
		for(int i=0 ; i<studentList.size();i++){
			System.out.println("@stuId:"+studentList.get(i).getId());
			List ScoreList =  session.createSQLQuery("select courseName,mark from Score s where stu_id=?").setString(0,studentList.get(i).getId()).list();
			System.out.println("ScoreList:"+ScoreList);
			
			for(int j=0 ; j<ScoreList.size();j++){
				Object[] b =(Object[]) ScoreList.get(j);
				studentList.get(i).getScoreMap().put((String)b[0], (Float) b[1]);
			}
		}
		return studentList;
		
	}
	//查询学生记录条数
	public int getStudentCount(String classId) {
		String hql = "select count(*) from student where stuClass_id='"+classId+"'";  
		Session session = sessionFactory.getCurrentSession();
	    BigInteger count = (BigInteger)session.createSQLQuery(hql).list().listIterator().next();  
	    return  count.intValue();  
	}
	//通过班级di 找到该班级
	public StuClass findClass(String classId) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println(classId);
		return (StuClass) session.load(StuClass.class, classId);	
	}
	//添加学生
	public void saveStudent(Student stu) {
		Session session = sessionFactory.getCurrentSession();
		session.save(stu);
		
	}
	//通过学号找到学生
	public Student findStudent(String stuId) {
		return (Student) sessionFactory.getCurrentSession().get(Student.class, stuId);
	}
	//通过班级名找到班级
	public StuClass findClassByName(String className) {
		System.out.println(className+"classname");
		Session session = sessionFactory.getCurrentSession();
		 Object o  = session.createQuery("from StuClass c where c.className=?")
				.setString(0, className).uniqueResult();
		if(o==null) return null;
		else 
			return (StuClass)o;
		
		
	}
	//添加班级
	public boolean addClass(StuClass stuClass) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(stuClass);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//更新学生信息
	public void updateStudent(Student stu) {
		Session session = sessionFactory.getCurrentSession();
		session.update(stu);
	}
	//删除学生
	public boolean deleteStudent(String stuId) {
		Session session = sessionFactory.getCurrentSession();
		session.createSQLQuery("delete from score  where stu_id=?").setString(0, stuId).executeUpdate();
		int i = session.createQuery("delete from Student s where s.id=?").setString(0, stuId).executeUpdate();
		System.out.println("i:@@@"+i);
		if(i!=0){
			return true;
		}	
			else 
			return false;
	}
	//将一个班级的成绩进行排序
	public List getOneClassScoresByOrder(String id) {
		Session session=sessionFactory.getCurrentSession(); 
		List<Student> studentList  = session.createQuery("From Student s where stuClass_id=? order by s.id").setString(0, id).list();
		for(int i=0 ; i<studentList.size();i++){
			List ScoreList =  session.createSQLQuery("select courseName,mark from Score s where stu_id=?").setString(0,studentList.get(i).getId()).list();
			for(int j=0 ; j<ScoreList.size();j++){
				Object[] b =(Object[]) ScoreList.get(j);
				studentList.get(i).getScoreMap().put((String)b[0], (Float) b[1]);
			}
			System.out.println("student:"+studentList.get(i));
		}
		return studentList;
		
	}
	//获得一个班级的成功率优秀率
	public List getClasscondition(StuClass s) {
		Session session=sessionFactory.getCurrentSession(); 
		StuClass c= (StuClass)session.get(StuClass.class, s.getId());
		List<Score> scoreList  = session.createQuery("From Score s where s.stu.stuClass=? ").setEntity(0, c).list();
		System.out.println(s.getId()+scoreList);
		return scoreList;
	}

	

}

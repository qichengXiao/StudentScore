package com.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.Subject;



import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ChannelDao;
import com.dao.StudentDao;
import com.model.Score;
import com.model.StuClass;
import com.model.Student;


import com.service.ChannelService;
import com.service.StudentService;
import com.util.Pager;


@Service("studentService")
//service.impl的实现注解 请看service接口
public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;
	
	@Resource(name="studentDao")
	public void setStudentDao(StudentDao studentDao) 
	{
		this.studentDao = studentDao;
	}
	@Transactional
	//获取一个班级的成绩
	public List<Student> getOneClassScores(String id, Pager pager){
		
			return studentDao.getOneClassScores(id,pager);
	
	}
	
	@Transactional
	//添加学生
	public boolean addStudent(String classId, Student stu){
		StuClass stuClass = studentDao.findClass(classId);
		if(stuClass==null) return false;
		stu.setStuClass(stuClass);
		studentDao.saveStudent(stu);
		return true;
		
	}
	@Transactional
	//获取学生信息,判断stuId是否能对上stuName
	public Student getStudent(String stuId, String stuName){
		Student stu = studentDao.findStudent(stuId);
		return stu;
	}
	@Transactional
	//添加班级
	public boolean addClass(String classId,String className, String academeName) {
		//StuClass  stuClass = studentDao.findClassByName(className);
		//if(stuClass==null) {
			StuClass  newStuClass = new StuClass(classId, className, academeName);
			if(studentDao.addClass(newStuClass))
				return true;
		//}	
		return false;
	}
	@Transactional
	//找出班级信息
	public StuClass findClass(String classId) {
		return studentDao.findClass(classId);
	}
	@Transactional
	//更新学生信息
	public void updateStudent(Student stu) {
		studentDao.updateStudent(stu);
	}
	@Transactional
	//删除学生信息
	public boolean deleteStudent(String stuId) {
		// TODO Auto-generated method stub
		return studentDao.deleteStudent(stuId);
	}
	@Transactional
	//搜索学生通过学号
	public Student searchStudenyById(String stuId) {
			return studentDao.findStudent(stuId);
	}
	
	@Transactional
	//通过班级名称找到该班级
	public StuClass findClassByName(String className) {
		return studentDao.findClassByName(className);
		
	
	}
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	//将一个班级成绩排序
	public List getOneClassScoresByOrder(String classId, Pager pager,int orderType) {
		List l = (List) studentDao.getOneClassScoresByOrder(classId);
		String order=null;
		System.out.println(orderType);
		switch(orderType){
			//学号排序
			case 1: break;
			//语文成绩
		case 21:
			Collections.sort(l, new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					Object mark1Object = o1.getScoreMap().get("语文");
					Object mark2Object = o2.getScoreMap().get("语文");
					float mark1=0;
					float mark2=0;
					if(mark1Object!=null) mark1=(float)mark1Object;
					if(mark2Object!=null)	mark2=(float)mark2Object;
					
					if(mark1>mark2) 
						return -1;
					else if(mark1<mark2	)
						return 1;
					else 
						return 0;
					
				}
			});
			break;
			
			
			//按数学排序
		case 22:
		
			Collections.sort(l, new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					Object mark1Object = o1.getScoreMap().get("数学");
					Object mark2Object = o2.getScoreMap().get("数学");
					float mark1=0;
					float mark2=0;
					if(mark1Object!=null) mark1=(float)mark1Object;
					if(mark2Object!=null)	mark2=(float)mark2Object;
					
					if(mark1>mark2) 
						return -1;
					else if(mark1<mark2	)
						return 1;
					else 
						return 0;
				}
			});
			break;
			//英语排序
		case 23:
			Collections.sort(l, new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					Object mark1Object = o1.getScoreMap().get("英语");
					Object mark2Object = o2.getScoreMap().get("英语");
					float mark1=0;
					float mark2=0;
					if(mark1Object!=null) mark1=(float)mark1Object;
					if(mark2Object!=null)	mark2=(float)mark2Object;
					
					if(mark1>mark2) 
						return -1;
					else if(mark1<mark2	)
						return 1;
					else 
						return 0;
				}
			});
			break;
		
		//总成绩排序
		case 3:
			Collections.sort(l, new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					float mark1=0;
					float mark2=0;
			
					for (String key : o1.getScoreMap().keySet()) {
						
						 mark1 += o1.getScoreMap().get(key);
					}
					for (String key : o2.getScoreMap().keySet()) {
						 mark2 += o2.getScoreMap().get(key);
					}
					
					if (mark1 == 0)
						return 1;
					if (mark2 == 0)
						return -1;
					if ( mark1 >  mark2)
						return -1;
					else
						return 1;
				}
			});
			break;
		
		
		//按平均成绩排序
		case 4:
			Collections.sort(l, new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					float mark1=0;
					float mark2=0;
					int i=0,j=0;
					for (String key : o1.getScoreMap().keySet()) {
						 mark1 += o1.getScoreMap().get(key);
						i++;
					}
					for (String key : o2.getScoreMap().keySet()) {
						 mark2 += o2.getScoreMap().get(key);
						j++;
					}
					if (mark1 == 0)
						return 1;
					if (mark2 == 0)
						return -1;
					if ( mark1/i >  mark2/j)
						return -1;
					else
						return 1;
				}
			});
			break;
			
		case 5:
			Collections.sort(l, new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					float mark1=0;
					float mark2=0;
					int i=0,j=0;
					for (String key : o1.getScoreMap().keySet()) {
						 mark1 += o1.getScoreMap().get(key);
						i++;
					}
					for (String key : o2.getScoreMap().keySet()) {
						 mark2 += o2.getScoreMap().get(key);
						j++;
					}
					if (mark1 == 0)
						return 1;
					if (mark2 == 0)
						return -1;
					if ( mark1/i >  mark2/j)
						return -1;
					else
						return 1;
				}
			});
			break;
	}
		
		int firstRecord= (pager.getCurrPage()-1)*pager.getPageSize();
		int lastRecord=firstRecord+pager.getPageSize();
		if(lastRecord>=l.size())
			lastRecord=l.size()-1;
		return l.subList(firstRecord, lastRecord);
		
	}
	@Transactional
	//计算一个班级的优秀率成功率
	public String getClassCondition(StuClass s) {
		
		float cA=0,cB=0,cNum=0,eA=0,eB=0,eNum=0,mA=0,mB=0,mNum=0;
		List<Score> l=studentDao.getClasscondition(s);
		if(l==null) return s.getAcademeName()+"  "+s.getClassName() + "该班级无成绩"; 
		for(int i=0;i<l.size();i++){
			Score	score = l.get(i);
			
			if(score.getCourseName().equals("语文")){
				cNum++;
				if(score.getMark()>90)
					cA++;
				else if(score.getMark()>60)
					cB++;	
			}
			else if(score.getCourseName().equals("数学")){
				mNum++;
				if(score.getMark()>90)
					mA++;
				else if(score.getMark()>60)
					mB++;	
			}
			else if(score.getCourseName().equals("英语")){
				eNum++;
				if(score.getMark()>90)
					eA++;
				else if(score.getMark()>60)
					eB++;	
			}
		}
		if(eNum==0&&mNum==0&&cNum==0) return s.getAcademeName()+"  "+s.getClassName() + "该班级无成绩";
		//格式化数据 变成百分数
		BigDecimal num1 = new BigDecimal((cB+cA)/cNum).multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP);
		BigDecimal num2 = new BigDecimal((mB+mA)/mNum).multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP);
		BigDecimal num3 = new BigDecimal((eB+eA)/eNum).multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP);
		BigDecimal num4 = new BigDecimal(cA/cNum).multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP);
		BigDecimal num5 = new BigDecimal(mA/mNum).multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP);
		BigDecimal num6 = new BigDecimal(eA/eNum).multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP);
		
		
		String result =s.getAcademeName()+"  "+s.getClassName()+":的语数英及格率分别为"
						+num1+"%,"+num2+"%,"+ num3
						+"%  优秀率分别为"+num4+"%,"+num5+"%,"+num6+"%";
		
    
	
		return 	result;

	}


}

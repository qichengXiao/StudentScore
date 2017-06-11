package com.dao;


import java.util.List;

import javax.annotation.Resource;


import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.model.StuClass;



@Repository("channelDao")
//该持久层用与数据库操作与菜单有关的数据
public class ChannelDao
{
	SessionFactory sessionFactory;

	//因为我们在applicationContext-db.xml文件里配置了sessionFactory这个bean，系统初始化时会实例化这个bean，所以这里可以自动注入到我们声明的变量
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}
	
	public List getChannels() {
		List channels = sessionFactory.getCurrentSession().createQuery("from Channel ").list();
		return channels;
	}

	public List<StuClass> getClasses() {
		List<StuClass> classes = sessionFactory.getCurrentSession().createQuery("from StuClass ").list();
		return classes;
	}

}

package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Score {
	private String id;
	private String courseName;
	private float mark;
	private Student stu;
	
	public Score(String id, String courseName, float mark, Student stu) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.mark = mark;
		this.stu = stu;
	}
	public Score() {
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", courseName=" + courseName + ", mark="
				+ mark + ", stu=" + stu + "]";
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}
	@OneToOne
	public Student getStu() {
		return stu;
	}
	public void setStu(Student stu) {
		this.stu = stu;
	}
	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid")  
    @GeneratedValue(generator="idGenerator")  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}

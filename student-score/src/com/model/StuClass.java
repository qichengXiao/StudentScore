package com.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StuClass {
	private String id;
	private String className;//14级软件4班
	private String academeName;//计算机学院
	
	@Id
	
	public String getId() {
		return id;
	}
	public StuClass() {
		super();
	}
	public StuClass(String id, String className, String academeName) {
		super();
		this.id = id;
		this.className = className;
		this.academeName = academeName;
	}
	@Override
	public String toString() {
		return "StuClass [id=" + id + ", className=" + className
				+ ", academeName=" + academeName + "]";
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getAcademeName() {
		return academeName;
	}
	public void setAcademeName(String academeName) {
		this.academeName = academeName;
	}
}

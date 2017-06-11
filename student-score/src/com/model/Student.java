package com.model;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
@Entity
public class Student {
	private String id;
	private String name;
	private StuClass stuClass;
	private HashMap<String,Float > scoreMap=new HashMap<String,Float>();
	
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", stuClass="
				+ stuClass + ", scoreMap="  + scoreMap+"]";
	}
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	@ManyToOne
	public StuClass getStuClass() {
		return stuClass;
	}
	public void setStuClass(StuClass stuClass) {
		this.stuClass = stuClass;
	}
	@Transient
	public HashMap<String,Float > getScoreMap() {
		return scoreMap;
	}
	public void setScoreMap(HashMap<String,Float > scoreMap) {
		this.scoreMap = scoreMap;
	}
}

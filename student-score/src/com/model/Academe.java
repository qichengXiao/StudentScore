package com.model;

import java.util.List;

public class Academe {
	private String academeName;
	private List<StuClass> academeClasses;
	public String getAcademeName() {
		return academeName;
	}
	public void setAcademeName(String academeName) {
		this.academeName = academeName;
	}
	public List<StuClass> getAcademeClasses() {
		return academeClasses;
	}
	public void setAcademeClasses(List<StuClass> academeClasses) {
		this.academeClasses = academeClasses;
	}
	public Academe(String academeName) {
		
		this.academeName = academeName;
	}
	public Academe(String academeName, List<StuClass> academeClasses) {
		super();
		this.academeName = academeName;
		this.academeClasses = academeClasses;
	}
	
}

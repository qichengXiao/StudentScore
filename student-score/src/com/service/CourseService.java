package com.service;

import java.util.ArrayList;

import com.model.Score;
import com.util.Pager;




public interface CourseService {


	public ArrayList<Score> getOneCourseScore(String id, Pager pager);
}


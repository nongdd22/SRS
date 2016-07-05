package model;

import java.util.*;

public class CourseCatalog {

	private HashMap<String, Course> courses;
	
	public CourseCatalog() {	
		courses = new HashMap<String, Course>();
	}
	
	public CourseCatalog(HashMap<String, Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course c) {
		String key = c.getCourseNo();
		courses.put(key, c);
	}

}

package service;

import java.util.HashMap;
import java.util.List;

import dao.CourseDao;
import dao.DataAccess;
import model.Course;
import model.CourseCatalog;
import util.org.json.JSONArray;
import util.org.json.JSONObject;

public class courseService {
	private static CourseCatalog  Courses;
	CourseDao cd = DataAccess.createCourseDao();
	HashMap<String,Course> courses = new HashMap<String, Course>();
	List<Course> Course = cd.getCourses();
	List<Course> Prerequisite;
	Course course;Course prerequisite;
	
	public courseService(){
		for(Course c:Course){	
			course=new Course(c.getCourseNo(), c.getCourseName(), c.getCredits());
			Prerequisite = cd.getPrerequisites(c.getCourseNo());
			for(Course p:Prerequisite){
				prerequisite=cd.searchCourse(p.getCourseNo());
				course.addPrerequisite(prerequisite);
			}
			courses.put(course.getCourseNo(), course);
		}
		Courses=new CourseCatalog(courses);
	}
	
	public CourseCatalog getCourseCatalog() {
		return Courses;
	}
	
	public String getCourses() {
		JSONArray ja = new JSONArray();
		for(Course c:Course){
			JSONObject jo = new JSONObject();
			jo.put("courseNo", c.getCourseNo());
			jo.put("courseName", c.getCourseName());
			jo.put("credits", c.getCredits());
			Prerequisite = cd.getPrerequisites(c.getCourseNo());
			for(Course p:Prerequisite){
				prerequisite=cd.searchCourse(p.getCourseNo());
				jo.put("pcourseNo", prerequisite.getCourseNo());
				jo.put("pcourseName", prerequisite.getCourseName());
				jo.put("pcredits", prerequisite.getCredits());
			}
			ja.put(jo);
		}
		return ja.toString();
	}
	
	public String getCourse(String courseNo) {
		JSONObject jo = new JSONObject();
		Course c = cd.searchCourse(courseNo);
		jo.put("courseNo", c.getCourseNo());
		jo.put("courseName", c.getCourseName());
		jo.put("credits", c.getCredits());
		Prerequisite = cd.getPrerequisites(c.getCourseNo());
		for(Course p:Prerequisite){
			prerequisite=cd.searchCourse(p.getCourseNo());
			jo.put("pcourseNo", prerequisite.getCourseNo());
			jo.put("pcourseName", prerequisite.getCourseName());
			jo.put("pcredits", prerequisite.getCredits());
		} 	
		return jo.toString();
	}
}

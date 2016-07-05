package dao;

import java.util.List;

import model.Course;

public interface CourseDao {
	List<Course> getCourses();
	List<Course> getPrerequisites(String courseNo);
	void addCourse(Course course);
	void addCourseprerequisites(Course precourse,Course course);	
	void updateCourse(Course course);	
	void updateCourseprerequisites(Course precourse,Course course);
	void deleteCourseprerequisites(String courseNo);	
	void deleteCourse(String courseNo);
	Course searchCourse(String courseNo);
	String searchCourseNo(String courseName);
}

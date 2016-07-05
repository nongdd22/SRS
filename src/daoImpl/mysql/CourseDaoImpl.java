package daoImpl.mysql;

import java.util.List;

import dao.CourseDao;
import model.Course;


public class CourseDaoImpl implements CourseDao {

	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> getPrerequisites(String courseNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCourseprerequisites(Course precourse, Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCourseprerequisites(Course precourse, Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCourseprerequisites(String courseNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCourse(String courseNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Course searchCourse(String courseNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchCourseNo(String courseName) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

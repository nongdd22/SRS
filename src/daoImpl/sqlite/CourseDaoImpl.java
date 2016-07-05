package daoImpl.sqlite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.CourseDao;
import model.Course;
import util.DbUtil;

public class CourseDaoImpl implements CourseDao {

	@Override
	public List<Course> getCourses() {
		List<Course> courses = new ArrayList<Course>();
		Course c;
		String findAllCourse="select * from Course";
		Connection Conn = DbUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(findAllCourse);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String courseNo = rs.getString("courseNo");
				String courseName = rs.getString("courseName");
				double credits= rs.getDouble("credits");
				c=new Course(courseNo, courseName, credits);
				courses.add(c);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return courses;		
	}
	
	@Override
	public List<Course> getPrerequisites(String courseNo){
		List<Course> prerequisites = new ArrayList<Course>();
		Course p;
		String findAllPrerequisites="select PrerequisitesNo from Course_prerequisites where courseNo='"+courseNo+"'";
		Connection Conn = DbUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(findAllPrerequisites);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String PrerequisitesNo = rs.getString("PrerequisitesNo");
				p=new Course(PrerequisitesNo, "", 0);
				prerequisites.add(p);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return prerequisites;	
	}
	
	@Override
	public void addCourse(Course course) {

		Connection Conn = DbUtil.getSqliteConnection();
		String addCourse = "insert into Course(courseNo,courseName,credits) values(?,?,?)";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(addCourse);
			pstmt.setString(1, course.getCourseNo());
			pstmt.setString(2, course.getCourseName());
			pstmt.setDouble(3, course.getCredits());
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();
/*			Connection Conn1 = DbUtil.getSqliteConnection();
			String addPrerequisites = "insert into Course_prerequisites(PrerequisitesNo,courseNo) values(?,?)";
			PreparedStatement pstmt1 = Conn1.prepareStatement(addPrerequisites);
			String pcNo;
			for (Course Pc : course.getPrerequisites()) {
				pcNo=Pc.getCourseNo();
				pstmt1.setString(1, pcNo);
				pstmt1.setString(2, course.getCourseNo());
			}
			pstmt1.executeUpdate();
			pstmt1.close();
			Conn1.close()*/;
			System.out.println("添加课程成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void addCourseprerequisites(Course precourse,Course course) {

		Connection Conn = DbUtil.getSqliteConnection();
		String addPrerequisites = "insert into Course_prerequisites(PrerequisitesNo,courseNo) values(?,?)";
		try {		
			PreparedStatement pstmt = Conn.prepareStatement(addPrerequisites);
			pstmt.setString(1, precourse.getCourseNo());
			pstmt.setString(2, course.getCourseNo());
			pstmt.executeUpdate();
			course.addPrerequisite(precourse);
			pstmt.close();
			Conn.close();
			System.out.println("添加先修课成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void updateCourse(Course course) {
		
		Connection Conn = DbUtil.getSqliteConnection();
		String updateCourse = "update Course set courseName=?,credits=? where courseNo='"+course.getCourseNo()+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(updateCourse);
			pstmt.setString(1, course.getCourseName());
			pstmt.setDouble(2, course.getCredits());
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();		
			System.out.println("修改课程成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateCourseprerequisites(Course precourse,Course course) {
		
		Connection Conn = DbUtil.getSqliteConnection();
		String updatePrerequisites = "update Course_prerequisites set PrerequisitesNo=? where courseNo='"+course.getCourseNo()+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(updatePrerequisites);
			pstmt.setString(1, precourse.getCourseNo());
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();		
			System.out.println("修改先修课成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void deleteCourseprerequisites(String courseNo){
		
		Connection Conn = DbUtil.getSqliteConnection();
		String deleteCourseprerequisites = "delete from Course_prerequisites where courseNo='"+courseNo+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(deleteCourseprerequisites);
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();
			System.out.println("删除先修课成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteCourse(String courseNo) {

		Connection Conn = DbUtil.getSqliteConnection();
		String deleteCourse = "delete from Course where courseNo='"+courseNo+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(deleteCourse);
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();
			System.out.println("删除课程成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Course searchCourse(String courseNo) {
		
		Course sc = null;
		Connection Conn = DbUtil.getSqliteConnection();
		String searchCourse = "select * from Course where courseNo='"+courseNo+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(searchCourse);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String courseName = rs.getString("courseName");
				double credits= rs.getDouble("credits");
				sc=new Course(courseNo, courseName, credits);				
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sc;
	}
	
	@Override
	public String searchCourseNo(String courseName) {
		
		String courseNo = "";
		Connection Conn = DbUtil.getSqliteConnection();
		String searchCourse = "select courseNo from Course where courseName='"+courseName+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(searchCourse);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				courseNo = rs.getString("courseNo");			
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courseNo;
	}

}

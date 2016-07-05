package daoImpl.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.StudentDao;
import model.Course;
import model.Section;
import model.Student;
import util.DbUtil;

public class StudentDaoImpl implements StudentDao {

	@Override
	public List<Student> getStudents() {
		
		Connection Conn = DbUtil.getSqliteConnection();
		Student student;
		String sql = "select * from Student";
		List<Student> students = new ArrayList<Student>();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String SSsn = rs.getString("SSsn");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String major = rs.getString("major");
				String degree = rs.getString("degree");
				student = new Student(SSsn,name,password,major,degree);
				students.add(student);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public List<Section> getEnrolledSections(Student student) {
		
		List<Section> enrolledSections = new ArrayList<Section>();
		Section section;Course course = null;
		String getEnrolledSections = "select * from Transcript where SSsn='"+student.getSsn()+"'";
		Connection Conn = DbUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(getEnrolledSections);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				section = new Section(rs.getInt("sectionNo"),"","",course,"",0);
				enrolledSections.add(section);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enrolledSections;
	}
	
	@Override
	public String getPassword(String SSsn){
		
		String sp = "";
		Connection Conn = DbUtil.getSqliteConnection();
		String getPassword = "select password from Student where SSsn='" + SSsn + "'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(getPassword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				sp = rs.getString("password");
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sp;
		
	}
	
	@Override
	public void addStudent(Student student) {
		
		Connection Conn = DbUtil.getSqliteConnection();
		String addStudent = "insert into Student (SSsn,name,password,major,degree) values(?,?,?,?,?)";
		try {		
			PreparedStatement pstmt = Conn.prepareStatement(addStudent);
			pstmt.setString(1, student.getSsn());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getPassword());
			pstmt.setString(4, student.getMajor());
			pstmt.setString(5, student.getDegree());
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateStudent(Student student) {
		
		Connection Conn = DbUtil.getSqliteConnection();
		String updateStudent = "update Student set name=?,password=?,major=?,degree=? where SSsn='"+student.getSsn()+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(updateStudent);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getPassword());
			pstmt.setString(3, student.getMajor());
			pstmt.setString(4, student.getDegree());
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteStudent(Student student) {
		
		Connection Conn = DbUtil.getSqliteConnection();
		String deleteStudent = "delete from Student where SSsn='"+student.getSsn()+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(deleteStudent);
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Student searchStudent(String SSsn) {
		Student student = null ;
		String searchStudent="select * from Student where SSsn='"+SSsn+"'";
		Connection Conn = DbUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(searchStudent);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				String major = rs.getString("major");
				String degree = rs.getString("degree");
				student=new Student(SSsn,name,password,major,degree);			
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return student;
	}

}

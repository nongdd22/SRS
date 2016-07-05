package daoImpl.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProfessorDao;
import model.Course;
import model.Professor;
import model.Section;
import util.DbUtil;

public class ProfessorDaoImpl implements ProfessorDao {

	@Override
	public List<Professor> getProfessors() {
		
		List<Professor> professors = new ArrayList<Professor>();;
		Professor p;
		String findAllProfessors="select * from Professor";
		Connection Conn = DbUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(findAllProfessors);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String PSsn = rs.getString("PSsn");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String title = rs.getString("title");
				String department = rs.getString("department");
				p=new Professor(PSsn, name, password,title,department);
				professors.add(p);	
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return professors;		
	}

	@Override
	public List<Section> getteached(String Pssn) {

		List<Section> Tsection = new ArrayList<Section>();
		Section ts;Course tc;
		String findAllTsection="select * from Section where PSsn='"+Pssn+"'";
		Connection Conn = DbUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(findAllTsection);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sectionNo = rs.getInt("sectionNo");
				String dayOfWeek = rs.getString("dayOfWeek");
				String timeOfDay = rs.getString("timeOfDay");
				String room = rs.getString("room");
				int seatingCapacity = rs.getInt("seatingCapacity");
				String courseNo = rs.getString("courseNo");
				tc=new Course(courseNo,"",0.0);
				ts=new Section(sectionNo, dayOfWeek, timeOfDay,tc,room,seatingCapacity);
				Tsection.add(ts);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return Tsection;	
	}
	
	@Override
	public String getPassword(String PSsn){
		
		String pp = "";
		Connection Conn = DbUtil.getSqliteConnection();
		String getPassword = "select password from Professor where PSsn='" + PSsn + "'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(getPassword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pp = rs.getString("password");
			}
			rs.close();
			pstmt.close();
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pp;
		
	}
	
	@Override
	public void addProfessor(Professor professor) {
		
		Connection Conn = DbUtil.getSqliteConnection();
		String addProfessor = "insert into Professor(PSsn,name,password,title,department) values(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(addProfessor);
			pstmt.setString(1, professor.getSsn());
			pstmt.setString(2, professor.getName());
			pstmt.setString(3, professor.getPassword());
			pstmt.setString(4, professor.getTitle());
			pstmt.setString(5, professor.getDepartment());
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();
			System.out.println("添加教师成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProfessor(Professor professor) {
		Connection Conn = DbUtil.getSqliteConnection();
		String updateProfessor = "update Professor set name=?,password=?,title=?,department=? where PSsn='"+professor.getSsn()+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(updateProfessor);
			pstmt.setString(1, professor.getName());
			pstmt.setString(2, professor.getPassword());
			pstmt.setString(3, professor.getTitle());
			pstmt.setString(4, professor.getDepartment());
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();		
			System.out.println("修改教师信息成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProfessor(String Pssn) {
		
		Connection Conn = DbUtil.getSqliteConnection();
		String deleteProfessor = "delete from Professor where PSsn='"+Pssn+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(deleteProfessor);
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();
			System.out.println("删除教师成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Professor searchProfessor(String Pssn) {
		Professor professor = null ;
		String searchProfessor="select * from Professor where PSsn='"+Pssn+"'";
		Connection Conn = DbUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(searchProfessor);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String PSsn = rs.getString("PSsn");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String title = rs.getString("title");
				String department = rs.getString("department");
				professor=new Professor(PSsn,name,password,title,department);			
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return professor;
	}
	
	@Override
	public String searchPSsn(String name){
		String PSsn = "";
		String searchSsn="select PSsn from Professor where name=='"+name+"'";
		Connection Conn = DbUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(searchSsn);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PSsn = rs.getString("PSsn");		
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return PSsn;
	}
	
	
	@Override
	public void teachSection(Professor professor, Section section) {
		
		Connection Conn = DbUtil.getSqliteConnection();
		String teachSection = "update Section set PSsn=?  where sectionNo='"+section.getSectionNo()+"'";
		try {
			PreparedStatement pstmt = Conn.prepareStatement(teachSection);
			pstmt.setString(1, professor.getSsn());
			pstmt.executeUpdate();
			pstmt.close();
			Conn.close();		
			System.out.println("更新教师排课成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}

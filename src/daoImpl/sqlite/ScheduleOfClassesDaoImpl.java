package daoImpl.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import dao.ScheduleOfClassesDao;
import model.Course;
import model.ScheduleOfClasses;
import model.Section;
import util.DbUtil;

public class ScheduleOfClassesDaoImpl implements ScheduleOfClassesDao {

	/*@Override
	public List<Section> getsectionsOffered(ScheduleOfClasses schedule) {
		List<Section> sectionsOffered = new ArrayList<Section>();
		Section section;
		HashMap<String, Section> SectionsOffered = schedule.getSectionsOffered();
		Iterator<Entry<String, Section>> iter = SectionsOffered.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Section> entry = (Entry<String, Section>) iter.next();
			section = entry.getValue();
			sectionsOffered.add(section);
		}
		
		Section s;Course c = null;
		String findAllsectionsOffered="select * from ScheduleOfClasses";
		Connection Conn = DbUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(findAllsectionsOffered);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sectionNo = rs.getInt("sectionNo");
				s=new Section(sectionNo, "", "",c,"",0);
				sectionsOffered.add(s);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return sectionsOffered;	
	}*/

	@Override
	public void addScheduleOfClasses(ScheduleOfClasses schedule) {
		Connection Conn = DbUtil.getSqliteConnection();
		String addScheduleOfClasses = "insert into ScheduleOfClasses(semester,sectionNo) values(?,?)";
		HashMap<String, Section> sections = schedule.getSectionsOffered();
		Iterator<Entry<String, Section>> iter = sections.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Section> secs = (Entry<String, Section>) iter.next();
			try {
				PreparedStatement pstmt = Conn.prepareStatement(addScheduleOfClasses);
				pstmt.setString(1, schedule.getSemester());
				pstmt.setInt(2, secs.getValue().getSectionNo());
				pstmt.executeUpdate();
				pstmt.close();
				Conn.close();
				System.out.println("添加ScheduleOfClasses成功");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteScheduleOfClasses(ScheduleOfClasses schedule) {
		Connection Conn = DbUtil.getSqliteConnection();
		HashMap<String, Section> sections = schedule.getSectionsOffered();
		Iterator<Entry<String, Section>> iter = sections.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Section> secs = (Entry<String, Section>) iter.next();
			String deleteScheduleOfClasses = "delete from ScheduleOfClasses where semester='"+schedule.getSemester()+"' and sectionNo='"+secs.getValue().getSectionNo()+"'";
			try {
				PreparedStatement pstmt = Conn.prepareStatement(deleteScheduleOfClasses);
				pstmt.executeUpdate();
				pstmt.close();
				Conn.close();
				System.out.println("删除ScheduleOfClasses成功");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Section> searchScheduleOfClasses(String semester) {
		List<Section> ScheduleOfClass =  new ArrayList<Section>();
		Section section;Course course = null;
		String ScheduleOfClasses="select * from ScheduleOfClasses where semester='"+semester+"'";
		Connection Conn = DbUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(ScheduleOfClasses);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sectionNo = rs.getInt("sectionNo");
				section=new Section(sectionNo,"","",course,"",0);
				ScheduleOfClass.add(section);	
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return ScheduleOfClass;
	}

}

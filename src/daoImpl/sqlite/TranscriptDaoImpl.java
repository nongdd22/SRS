package daoImpl.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.TranscriptDao;
import model.Course;
import model.Section;
import model.Student;
import model.Transcript;
import model.TranscriptEntry;
import util.DbUtil;

public class TranscriptDaoImpl implements TranscriptDao{

	@Override
	public Transcript getStudentTranscript(Student student) {
		Transcript transcript = new Transcript(student) ;
		TranscriptEntry transcriptEntry;
		Section section;
		Course course=null;
		String searchProfessor="select * from Transcript where SSsn='"+student.getSsn()+"'";
		Connection Conn = DbUtil.getSqliteConnection();
		try {
			PreparedStatement pstmt = Conn.prepareStatement(searchProfessor);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sectionNo = rs.getInt("sectionNo");
				String grade = rs.getString("grade");
				section=new Section(sectionNo, "", "",course,"",0);
				transcriptEntry=new TranscriptEntry(student,grade,section);
				transcript.addTranscriptEntry(transcriptEntry);
			}
			rs.close();
			pstmt.close();
			Conn.close();
		}catch(Exception e){
		    e.printStackTrace();	
		}
		return transcript;
	}
}


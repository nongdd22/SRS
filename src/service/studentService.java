package service;

import java.util.List;

import dao.CourseDao;
import dao.DataAccess;
import dao.ProfessorDao;
import dao.SectionDao;
import dao.StudentDao;
import dao.TranscriptEntryDao;
import model.Course;
import model.Professor;
import model.Section;
import model.Student;
import model.TranscriptEntry;
import util.org.json.JSONArray;
import util.org.json.JSONObject;

public class studentService {

	public String getEnrolledSections(Student student){
		StudentDao sd = DataAccess.createStudentDao();
		SectionDao secd = DataAccess.createSectionDao();
		CourseDao cd = DataAccess.createCourseDao();
		ProfessorDao pd = DataAccess.createProfessorDao();
		List<Section> sections = sd.getEnrolledSections(student);
		Section section;Course course;Professor professor;
		JSONArray ja = new JSONArray();
		for(Section s:sections){
			JSONObject jo = new JSONObject();
			jo.put("sectionNo", s.getSectionNo());
			section=secd.searchSection(s.getSectionNo());
			jo.put("dayOfWeek", section.getDayOfWeek());
			jo.put("timeOfDay", section.getTimeOfDay());
			jo.put("room", section.getRoom());
			jo.put("seatingCapacity", section.getSeatingCapacity());
			course=cd.searchCourse(section.getRepresentedCourse().getCourseNo());
			jo.put("courseName", course.getCourseName());
			professor=pd.searchProfessor(section.getInstructor().getSsn());
			jo.put("professor", professor.getName());
			ja.put(jo);
		}
		return ja.toString();
	}
	
	public void getdropResult(int sectionNo,Student student){
		SectionDao secd = DataAccess.createSectionDao();
		TranscriptEntryDao ted = DataAccess.createTranscriptEntryDao();
		Section section=secd.searchSection(sectionNo);
		section.drop(student);
		TranscriptEntry transcriptEntry=new TranscriptEntry(student,"",section);
		ted.deleteTranscriptEntry(transcriptEntry);
	}
	
}

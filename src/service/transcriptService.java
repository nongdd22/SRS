package service;

import java.util.List;

import dao.CourseDao;
import dao.DataAccess;
import dao.ProfessorDao;
import dao.SectionDao;
import dao.TranscriptDao;
import model.Course;
import model.Professor;
import model.Section;
import model.Student;
import model.Transcript;
import model.TranscriptEntry;
import util.org.json.JSONArray;
import util.org.json.JSONObject;

public class transcriptService {
		
		public String getStudentTranscript(Student student){
			TranscriptDao td = DataAccess.createTranscriptDao();
			SectionDao sd = DataAccess.createSectionDao();
			CourseDao cd = DataAccess.createCourseDao();
			ProfessorDao pd = DataAccess.createProfessorDao();
			Section section;Course course;Professor professor;
			Transcript transcript=td.getStudentTranscript(student);
			List<TranscriptEntry> TranscriptEntries = transcript.getTranscriptEntries();
			JSONArray ja = new JSONArray();
			for(TranscriptEntry te:TranscriptEntries){
				JSONObject jo = new JSONObject();
				jo.put("grade", te.getGrade());
				section=sd.searchSection(te.getSection().getSectionNo());
				course=cd.searchCourse(section.getRepresentedCourse().getCourseNo());
				jo.put("courseName", course.getCourseName());
				professor=pd.searchProfessor(section.getInstructor().getSsn());
				jo.put("professor", professor.getName());
				ja.put(jo);
			}
			return ja.toString();

		}
	}

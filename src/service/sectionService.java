package service;


import java.util.List;

import dao.CourseDao;
import dao.DataAccess;
import dao.ProfessorDao;
import dao.SectionDao;
import dao.StudentDao;
import dao.TranscriptDao;
import dao.TranscriptEntryDao;
import model.Course;
import model.EnrollmentStatus;
import model.Professor;
import model.Section;
import model.Student;
import model.Transcript;
import model.TranscriptEntry;
import util.org.json.JSONArray;
import util.org.json.JSONObject;

public class sectionService {
	SectionDao sd = DataAccess.createSectionDao();
	CourseDao cd = DataAccess.createCourseDao();
	StudentDao stud = DataAccess.createStudentDao();
	ProfessorDao pd = DataAccess.createProfessorDao();
	Course course;Student student;Professor professor;
	
	public String getSections() {
		JSONArray ja = new JSONArray();
		List<Section> Sections = sd.getSections();
		for(Section s:Sections){
			JSONObject jo = new JSONObject();
			jo.put("sectionNo", s.getSectionNo());
			jo.put("dayOfWeek", s.getDayOfWeek());
			jo.put("timeOfDay", s.getTimeOfDay());
			course = cd.searchCourse(s.getRepresentedCourse().getCourseNo());
			jo.put("courseName",course.getCourseName());
			jo.put("room", s.getRoom());
			jo.put("seatingCapacity", s.getSeatingCapacity());	
			professor=pd.searchProfessor(s.getInstructor().getSsn());
			jo.put("professor", professor.getName());
			ja.put(jo);
		}
		return ja.toString();
	}
	
	public String getSection(int sectionNo) {
		JSONObject jo = new JSONObject();
		Section s = sd.searchSection(sectionNo);
		jo.put("sectionNo", s.getSectionNo());
		jo.put("dayOfWeek", s.getDayOfWeek());
		jo.put("timeOfDay", s.getTimeOfDay());
		course = cd.searchCourse(s.getRepresentedCourse().getCourseNo());
		jo.put("courseName",course.getCourseName());
		jo.put("room", s.getRoom());
		jo.put("seatingCapacity", s.getSeatingCapacity());
		professor=pd.searchProfessor(s.getInstructor().getSsn());
		jo.put("professor", professor.getName());
		return jo.toString();
	}
	
	public String getSectionsbyCourse(String courseNo) {
		JSONArray ja = new JSONArray();
		List<Section> SectionsbyCourse = sd.getCourseSection(courseNo);
		for(Section s:SectionsbyCourse){
			JSONObject jo = new JSONObject();
			jo.put("sectionNo", s.getSectionNo());
			jo.put("dayOfWeek", s.getDayOfWeek());
			jo.put("timeOfDay", s.getTimeOfDay());
			course = cd.searchCourse(s.getRepresentedCourse().getCourseNo());
			jo.put("courseName",course.getCourseName());
			jo.put("room", s.getRoom());
			jo.put("seatingCapacity", s.getSeatingCapacity());	
			professor=pd.searchProfessor(s.getInstructor().getSsn());
			jo.put("professor", professor.getName());
			ja.put(jo);
		}
		return ja.toString();
		
	}
	
	public String getEnrolledStudents(int sectionNo){
		JSONArray ja = new JSONArray();
		List<Student> enrolled=sd.getEnrolledStudents(sectionNo);	
		for(Student s:enrolled){
			JSONObject jo = new JSONObject();
			jo.put("SSsn", s.getSsn());
			student=stud.searchStudent(s.getSsn());
			jo.put("name", student.getName());
			jo.put("major", student.getMajor());
			jo.put("degree", student.getDegree());
			ja.put(jo);
		}
		return ja.toString();
	}
	
	public String getenrolledResult(int sectionNo, Student student) {
		JSONObject jo = new JSONObject();
		StudentDao sd = DataAccess.createStudentDao();
		SectionDao secd = DataAccess.createSectionDao();
		CourseDao cd = DataAccess.createCourseDao();
		TranscriptDao td = DataAccess.createTranscriptDao();
		ProfessorDao pd = DataAccess.createProfessorDao();
		TranscriptEntryDao ted = DataAccess.createTranscriptEntryDao();
		//该学生已选课程
		List<Section> enrolledSections = sd.getEnrolledSections(student);
		for(Section s:enrolledSections){	
			Section cs=secd.searchSection(s.getSectionNo());
			Course cscourse=cd.searchCourse(cs.getRepresentedCourse().getCourseNo());
			Professor csprofessor=pd.searchProfessor(cs.getInstructor().getSsn());
			Section completesection=new Section(cs.getSectionNo(),cs.getDayOfWeek(),cs.getTimeOfDay(),cscourse,cs.getRoom(),cs.getSeatingCapacity());
			completesection.setInstructor(csprofessor);
			student.addSection(completesection);
		}
		//该学生成绩单
		Transcript transcript=td.getStudentTranscript(student);
		Transcript realtranscript=new Transcript(student);
		List<TranscriptEntry> transcriptentry=transcript.getTranscriptEntries();
		TranscriptEntry realtranscriptentry;
		for(TranscriptEntry tse:transcriptentry){
			Section ts=secd.searchSection(tse.getSection().getSectionNo());
			Course tscourse=cd.searchCourse(ts.getRepresentedCourse().getCourseNo());
			Section tsection=new Section(ts.getSectionNo(),ts.getDayOfWeek(),ts.getTimeOfDay(),tscourse,ts.getRoom(),ts.getSeatingCapacity());
			TranscriptEntry transentry=ted.searchTranscriptEntry(student.getSsn(), tse.getSection().getSectionNo());
			realtranscriptentry=new TranscriptEntry(student,transentry.getGrade(),tsection);
			realtranscript.addTranscriptEntry(realtranscriptentry);
		}
		student.setTranscript(realtranscript);
		//本次选择的Section
		Section section=secd.searchSection(sectionNo);
		Course course = section.getRepresentedCourse();
		Course realcourse = cd.searchCourse(course.getCourseNo());
		List<Course> prerequisites = cd.getPrerequisites(realcourse.getCourseNo());
		for(Course c:prerequisites){
			Course pre=cd.searchCourse(c.getCourseNo());
			realcourse.addPrerequisite(pre);
		}
		section.setRepresentedCourse(realcourse);
		//本次选择结果
		EnrollmentStatus result = section.enroll(student);
		jo.put("result", result.toString());
		if(result.toString().equals("success")){
			TranscriptEntry transcriptEntry=new TranscriptEntry(student,"",section);
			ted.addTranscriptEntry(transcriptEntry);
			jo.put("value", result.value());
		}else{
			jo.put("value", result.value());
		}
		return jo.toString();
	}
	
}

package service;

import java.util.List;

import dao.CourseDao;
import dao.DataAccess;
import dao.ProfessorDao;
import dao.ScheduleOfClassesDao;
import dao.SectionDao;
import model.Course;
import model.Professor;
import model.Section;
import util.org.json.JSONArray;
import util.org.json.JSONObject;

public class scheduleofclassesService {
	
	public String getScheduleOfClasses(String semester){
	ScheduleOfClassesDao socd=DataAccess.createScheduleOfClassesDao();
	SectionDao sd = DataAccess.createSectionDao();
	CourseDao cd = DataAccess.createCourseDao();
	ProfessorDao pd = DataAccess.createProfessorDao();
	List<Section> sections=socd.searchScheduleOfClasses(semester);
	Section section;Course course;Professor professor;
	JSONArray ja = new JSONArray();
	for(Section s:sections){
		JSONObject jo=new JSONObject();
		jo.put("sectionNo", s.getSectionNo());
		section=sd.searchSection(s.getSectionNo());
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
}
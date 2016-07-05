package service;

import java.util.HashMap;
import java.util.List;

import dao.CourseDao;
import dao.DataAccess;
import dao.ProfessorDao;
import model.Course;
import model.Faculty;
import model.Professor;
import model.Section;
import util.org.json.JSONArray;
import util.org.json.JSONObject;

public class professorService {
	private static Faculty  Professors;
	ProfessorDao pd = DataAccess.createProfessorDao();
	HashMap<String,Professor> professors = new HashMap<String, Professor>();
	List<Professor> professor = pd.getProfessors();
	
	public professorService(){
		for(Professor p:professor){
			professors.put(p.getSsn(),p);
		}
		Professors=new Faculty(professors);
	}
	
	public Faculty getFaculty() {
		return Professors;
	}
	
	public String getProfessors() {
		JSONArray ja = new JSONArray();
		for(Professor p:professor){
			JSONObject jo = new JSONObject();
			jo.put("PSsn", p.getSsn());
			jo.put("name", p.getName());
			jo.put("title", p.getTitle());
			jo.put("department", p.getDepartment());
			ja.put(jo);
		}
		return ja.toString();
	}
	
	public String getProfessor(String PSsn) {
		JSONObject jo = new JSONObject();
		Professor p = pd.searchProfessor(PSsn);
		jo.put("PSsn", p.getSsn());
		jo.put("password", p.getPassword());
		jo.put("name", p.getName());
		jo.put("title", p.getTitle());
		jo.put("department", p.getDepartment());
		return jo.toString();
	}
	
	public String getSectionsbyProfessor(String Pssn) {
		JSONArray ja = new JSONArray();
		List<Section> SectionsbyProfessor = pd.getteached(Pssn);
		for(Section s:SectionsbyProfessor){
			JSONObject jo = new JSONObject();
			jo.put("sectionNo", s.getSectionNo());
			jo.put("dayOfWeek", s.getDayOfWeek());
			jo.put("timeOfDay", s.getTimeOfDay());
			CourseDao cd = DataAccess.createCourseDao();
			Course course = cd.searchCourse(s.getRepresentedCourse().getCourseNo());
			jo.put("courseName",course.getCourseName());
			jo.put("room", s.getRoom());
			jo.put("seatingCapacity", s.getSeatingCapacity());
			ja.put(jo);
		}
		return ja.toString();
		
	}
}

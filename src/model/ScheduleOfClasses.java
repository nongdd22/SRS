package model;

import java.util.HashMap;

public class ScheduleOfClasses {

	private String semester;
	private HashMap<String, Section> sectionsOffered; 

	public ScheduleOfClasses(String semester) {
		setSemester(semester);
		sectionsOffered = new HashMap<String, Section>();
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getSemester() {
		return semester;
	}

	public HashMap<String, Section> getSectionsOffered() {
		return sectionsOffered;
	}
	
	public void addSection(Section s) {
		String key = ""+s.getSectionNo()+"";
		sectionsOffered.put(key, s);
		s.setOfferedIn(this);
	}

	public Section findSection(String SectionNo) {
		return sectionsOffered.get(SectionNo);
	}

	public boolean isEmpty() {
		if (sectionsOffered.size() == 0){
			return true;
		}else {
			return false;
		}
	}
	public void display() {
		System.out.println("Schedule of Classes for " + getSemester());
		System.out.println();

		// Iterate through all the values in the HashMap.

		for (Section s : sectionsOffered.values()) {
			s.display();
			System.out.println();
		}
	}
	
}

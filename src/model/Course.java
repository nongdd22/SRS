package model;

import java.util.ArrayList;
import java.util.Collection;

public class Course {
	private String courseNo;
	private String courseName;
	private double credits;
	private ArrayList<Section> offeredAsSection;
	private ArrayList<Course> prerequisites; //ÏÈÐÞ¿Î³Ì
	public Course(String cNo, String cName, double credits) {
		setCourseNo(cNo);
		setCourseName(cName);
		setCredits(credits);
		offeredAsSection = new ArrayList<Section>();
		prerequisites = new ArrayList<Course>();
	}

	public void setCourseNo(String cNo) {
		courseNo = cNo;
	}
	
	public String getCourseNo() {
		return courseNo;
	}
	
	public void setCourseName(String cName) {
		courseName = cName;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCredits(double c) {
		credits = c;
	}
	
	public double getCredits() {
		return credits;
	}
	
	public void addPrerequisite(Course c) {
		prerequisites.add(c);
	}

	public Collection<Course> getPrerequisites() {
		return prerequisites;
	}
	
	public boolean hasPrerequisites() {
		if (prerequisites.size() > 0) {
			return true;
		}else{
			return false;
		}
	}

	public Section scheduleSection(String day, String time, String room,int capacity) {
		Section s = new Section(offeredAsSection.size() + 1,day, time, this, room, capacity);		
		addSection(s);		
		return s;
	}

	public void addSection(Section s) {
		offeredAsSection.add(s);
	}

}


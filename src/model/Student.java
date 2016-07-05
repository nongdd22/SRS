package model;

import java.util.ArrayList;
import java.util.Collection;

public class Student extends Person {

	private String major;
	private String degree;
	private Transcript transcript;
	private ArrayList<Section> attends; 
	public Student(String ssn, String name, String password, String major, String degree) {
		super(ssn, name, password);
		this.setMajor(major);
		this.setDegree(degree);
		this.setTranscript(new Transcript(this));
		attends = new ArrayList<Section>();
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getDegree() {
		return degree;
	}

	public void setTranscript(Transcript t) {
		transcript = t;
	}

	public Transcript getTranscript() {
		return transcript;
	}
	
	public void addSection(Section s) {
		attends.add(s);
	}
	
	public void dropSection(Section s) {
		attends.remove(s);
	}
	
	public Collection<Section> getEnrolledSections() {
		return attends;
	}
	
	public boolean isEnrolledIn(Section s) {
		if (attends.contains(s)){
			return true;
		}else {
			return false;
		}
	}
	
	//是否已参加同一课程的其他班
	public boolean isCurrentlyEnrolledInSimilar(Section s1) {
		boolean foundMatch = false;
		Course c1 = s1.getRepresentedCourse();
		for (Section s2 : attends) {
			Course c2 = s2.getRepresentedCourse();
			if (c1.getCourseNo().equals(c2.getCourseNo())) {
				if (s2.getGrade(this) == null) {
					foundMatch = true;
					break;
				}
			} 
		}
		return foundMatch;
	}	

	public void display() {
		// First, let's display the generic Person info.


		// Then, display Student-specific info.

		System.out.println("Student-Specific Information:");
		System.out.println("\tMajor:  " + this.getMajor());
		System.out.println("\tDegree:  " + this.getDegree());

		// Finish with a blank line.
		System.out.println();
	}
	
}

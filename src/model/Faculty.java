package model;

import java.util.*;

public class Faculty {

	private HashMap<String, Professor> professors;

	public Faculty() {

		professors = new HashMap<String, Professor>();
	}
	
	public Faculty(HashMap<String, Professor> professors) {

		this.professors = professors;
	}

	public void addProfessor(Professor p) {
		professors.put(p.getSsn(), p);
	}

	public Professor findProfessor(String ssn) {
		return (Professor) professors.get(ssn);
	}

	public boolean isEmpty() {
		if (professors.size() == 0){
			return true;
		}else{
			return false;
		}
	}
}

package dao;

import java.util.List;

import model.Professor;
import model.Section;

public interface ProfessorDao {
	List<Professor> getProfessors();
	List<Section> getteached(String Pssn);
	String getPassword(String SSsn);
	void addProfessor(Professor professor);
	void updateProfessor(Professor professor);
	void deleteProfessor(String Pssn);
	Professor searchProfessor(String Pssn);
	String searchPSsn(String name);
	void teachSection(Professor professor, Section section);
}

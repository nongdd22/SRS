package dao;

import java.util.List;

import model.Section;
import model.Student;

public interface SectionDao {
	List<Section> getSections();
	List<Student> getEnrolledStudents(int sectionNo);
	/*List<TranscriptEntry> getSectionGrades(Section section);*/
	List<Section> getCourseSection(String courseNo);
	void addSection(Section section);
	void updateSection(Section section);
	void deleteSection(String sectionNo);
	Section searchSection(int sectionNo);
}


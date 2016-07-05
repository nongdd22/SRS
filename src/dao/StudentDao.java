package dao;

import java.util.List;

import model.Section;
import model.Student;

public interface StudentDao {
	List<Student> getStudents();
	List<Section> getEnrolledSections(Student student);
	String getPassword(String SSsn);
	void addStudent(Student student);
	void deleteStudent(Student student);
	void updateStudent(Student student);
	Student searchStudent(String SSsn);
}

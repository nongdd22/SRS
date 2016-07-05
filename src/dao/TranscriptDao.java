package dao;

import model.Student;
import model.Transcript;

public interface TranscriptDao {
	Transcript getStudentTranscript(Student student);
}

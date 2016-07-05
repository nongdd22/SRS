
package test;

import java.util.List;

import dao.CourseDao;
import dao.DataAccess;
import dao.ProfessorDao;
import dao.SectionDao;
import dao.StudentDao;
import dao.TranscriptDao;
import dao.TranscriptEntryDao;
import model.Course;
import model.EnrollmentStatus;
import model.Professor;
import model.ScheduleOfClasses;
import model.Section;
import model.Student;
import model.Transcript;
import model.TranscriptEntry;

public class SRS {
	
	public static List<Course> courseCatalog;
	public static ScheduleOfClasses scheduleOfClasses = new ScheduleOfClasses("201501");
	
	public static void main(String[] args) {
		Student s1, s2, s3;
		Professor p1, p2, p3;
		Course c1, c2, c3, c4, c5;
		Section sec1, sec2, sec3, sec4, sec5;
		StudentDao sd = DataAccess.createStudentDao();
		ProfessorDao pd=DataAccess.createProfessorDao();
		TranscriptDao td = DataAccess.createTranscriptDao();
		CourseDao cd = DataAccess.createCourseDao();
		SectionDao secd= DataAccess.createSectionDao();
		TranscriptEntryDao ted = DataAccess.createTranscriptEntryDao();
		// -----------
		// Professors.
		// -----------
		List<Professor> professors = pd.getProfessors();
		p1 = professors.get(0);
		p2 = professors.get(1);
		p3 = professors.get(2);
		
		// ---------
		// Students.
		// ---------
		
		List<Student> students = sd.getStudents();
		s1 = students.get(0);
		List<Section> enrolledSections = sd.getEnrolledSections(s1);
		for(Section s:enrolledSections){	
			Section cs=secd.searchSection(s.getSectionNo());
			Course cscourse=cd.searchCourse(cs.getRepresentedCourse().getCourseNo());
			Professor csprofessor=pd.searchProfessor(cs.getInstructor().getSsn());
			Section completesection=new Section(cs.getSectionNo(),cs.getDayOfWeek(),cs.getTimeOfDay(),cscourse,cs.getRoom(),cs.getSeatingCapacity());
			completesection.setInstructor(csprofessor);
			s1.addSection(completesection);
		}
		Transcript transcript=td.getStudentTranscript(s1);
		Transcript realtranscript=new Transcript(s1);
		List<TranscriptEntry> transcriptentry=transcript.getTranscriptEntries();
		TranscriptEntry realtranscriptentry;
		for(TranscriptEntry tse:transcriptentry){
			Section ts=secd.searchSection(tse.getSection().getSectionNo());
			Course tscourse=cd.searchCourse(ts.getRepresentedCourse().getCourseNo());
			Section tsection=new Section(ts.getSectionNo(),ts.getDayOfWeek(),ts.getTimeOfDay(),tscourse,ts.getRoom(),ts.getSeatingCapacity());
			TranscriptEntry transentry=ted.searchTranscriptEntry(s1.getSsn(), tse.getSection().getSectionNo());
			realtranscriptentry=new TranscriptEntry(s1,transentry.getGrade(),tsection);
			realtranscript.addTranscriptEntry(realtranscriptentry);
		}
		s1.setTranscript(realtranscript);

		
		
		s2 = students.get(1);
		s3 = students.get(2);

		// --------
		// Courses.
		// --------
		
		courseCatalog = cd.getCourses();
		c1 = courseCatalog.get(0);// CMP101
		c2 = courseCatalog.get(1);// OBJ101
		c3 = courseCatalog.get(2);// CMP283
		c4 = courseCatalog.get(3);// CMP999
		c5 = courseCatalog.get(4);// ART101

		// Establish some prerequisites ( c3 => c4 => c5).

		c4.addPrerequisite(c3);
		c5.addPrerequisite(c4);

		// ---------
		// Sections.
		// ---------

		sec1 = c1.scheduleSection("Monday", "8:10-10:00 AM", "GOVT101", 35);
		sec2 = c1.scheduleSection("Tuesday", "6:10-8:00 PM", "SCI330", 1);
		sec3 = c2.scheduleSection("Thursday", "8:10-10:00 AM", "GJ2A203", 35);
		sec4 = c2.scheduleSection("Thursday", "10:10-12:00 AM", "GJ2B403", 35);
		sec5 = c3.scheduleSection("Monday", "8:10-10:00 AM", "GJ3A203", 30);
		
		sec1.setRepresentedCourse(c1);
		sec2.setRepresentedCourse(c2);
		sec3.setRepresentedCourse(c3);
		sec4.setRepresentedCourse(c4);
		sec5.setRepresentedCourse(c5);


		// Add these to the Schedule of Classes.
		
		scheduleOfClasses.addSection(sec1);
		scheduleOfClasses.addSection(sec2);
		scheduleOfClasses.addSection(sec3);
		scheduleOfClasses.addSection(sec4);
		scheduleOfClasses.addSection(sec5);
		
		p1.agreeToTeach(sec1);
		p1.agreeToTeach(sec4);
		p2.agreeToTeach(sec2);
		p2.agreeToTeach(sec3);
		p3.agreeToTeach(sec5);



		System.out.println("===============================");
		System.out.println("Student registration has begun!");
		System.out.println("===============================");
		System.out.println();

		// Simulate students attempting to enroll in sections of
		// various courses.

		attemptToEnroll(s1, sec1);
		attemptToEnroll(s1, sec5);
		attemptToEnroll(s2, sec2);
		attemptToEnroll(s3, sec2);
		attemptToEnroll(s3, sec3);
		sec3.postGrade(s3, "A");
		attemptToEnroll(s3, sec4);		

	}


	private static void reportStatus(EnrollmentStatus s) {
		System.out.println("Status:  " + s.value());
		System.out.println();
	}

	private static void attemptToEnroll(Student s, Section sec) {
		System.out.println("Student " + s.getName() + " is attempting to enroll in " + sec.getRepresentedCourse().getCourseName());
		reportStatus(sec.enroll(s));
	}
}

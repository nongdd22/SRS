package dao;

public class DataAccess {
	private static String daoName = "daoImpl.sqlite";
	/*private static String daoName = "daoImpl.mysql";*/
	public static CourseDao createCourseDao() {
		CourseDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "CourseDao" + "Impl").newInstance();
			result = (CourseDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static ProfessorDao createProfessorDao() {
		ProfessorDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "ProfessorDao" + "Impl").newInstance();
			result = (ProfessorDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static StudentDao createStudentDao() {
		StudentDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "StudentDao" + "Impl").newInstance();
			result = (StudentDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static SectionDao createSectionDao() {
		SectionDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "SectionDao" + "Impl").newInstance();
			result = (SectionDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}	
	
	public static TranscriptDao createTranscriptDao() {
		TranscriptDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "TranscriptDao" + "Impl").newInstance();
			result = (TranscriptDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static TranscriptEntryDao createTranscriptEntryDao() {
		TranscriptEntryDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "TranscriptEntryDao" + "Impl").newInstance();
			result = (TranscriptEntryDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static ScheduleOfClassesDao createScheduleOfClassesDao() {
		ScheduleOfClassesDao result = null;
		try {
			Object o = Class.forName(daoName + "." + "ScheduleOfClassesDao" + "Impl").newInstance();
			result = (ScheduleOfClassesDao)o;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
}


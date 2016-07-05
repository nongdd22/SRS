package dao;

import java.util.List;

import model.ScheduleOfClasses;
import model.Section;

public interface ScheduleOfClassesDao {
	void addScheduleOfClasses(ScheduleOfClasses schedule);
	void deleteScheduleOfClasses(ScheduleOfClasses schedule);
	List<Section> searchScheduleOfClasses(String semester);
}

package model;

public enum EnrollmentStatus {
	/*success("Enrollment successful!  :o)"), 
	secFull("Enrollment failed;  section was full.  :op"), 
	prereq("Enrollment failed; prerequisites not satisfied.  :op"), 
	prevEnroll("Enrollment failed; previously enrolled.  :op");*/
	success("选课成功!"), 
	secFull("选课失败:课程人数已满."), 
	prereq("选课失败:先修课程条件不满足."), 
	prevEnroll("选课失败:已选过该课程.");
	private final String value;

	EnrollmentStatus(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
}

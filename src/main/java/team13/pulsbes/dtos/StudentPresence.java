package team13.pulsbes.dtos;

public class StudentPresence {
	
	private StudentDTO student;
	
	private Boolean presence;	
	
	public StudentPresence(StudentDTO student, Boolean presence) {
		this.student = student;
		this.presence = presence;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public Boolean getPresence() {
		return presence;
	}

	public void setPresence(Boolean presence) {
		this.presence = presence;
	}	
}

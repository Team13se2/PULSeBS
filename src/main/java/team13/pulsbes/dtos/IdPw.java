package team13.pulsbes.dtos;

public class IdPw {
	
	private String email;
	
	private String psw;

	private Boolean teacher;
	
	public IdPw(String email, String psw, Boolean teacher) {
		this.email = email;
		this.psw = psw;
		this.teacher = teacher;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public Boolean getTeacher() {
		return teacher;
	}

	public void setTeacher(Boolean teacher) {
		this.teacher = teacher;
	}
	
}

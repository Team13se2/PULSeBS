package team13.pulsbes.dtos;

public class IdPw {
	
	private String Email;
	
	private String Psw;

	private Boolean Teacher;
	
	public IdPw(String email, String psw, Boolean teacher) {
		this.Email = email;
		this.Psw = psw;
		this.Teacher = teacher;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public String getPsw() {
		return Psw;
	}

	public void setPsw(String psw) {
		this.Psw = psw;
	}

	public Boolean getTeacher() {
		return Teacher;
	}

	public void setTeacher(Boolean teacher) {
		this.Teacher = teacher;
	}
	
}

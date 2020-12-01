package team13.pulsbes.dtos;

public class IdPw {
	
	private String Email;
	
	private String Psw;

	private String Role;
	
	public IdPw(String email, String psw, String teacher) {
		this.Email = email;
		this.Psw = psw;
		this.Role = teacher;
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

	public String getRole() {
		return Role;
	}

	public void setRole(String teacher) {
		this.Role = teacher;
	}
	
}

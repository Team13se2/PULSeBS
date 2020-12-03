package team13.pulsbes.dtos;

public class IdPw {
	
	private String email;
	
	private String psw;

	private String role;
	
	public IdPw(String email, String psw, String teacher) {
		this.email = email;
		this.psw = psw;
		this.role = teacher;
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

	public String getRole() {
		return role;
	}

	public void setRole(String teacher) {
		this.role = teacher;
	}
	
}

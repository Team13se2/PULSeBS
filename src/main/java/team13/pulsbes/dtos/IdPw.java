package team13.pulsbes.dtos;

public class IdPw {
	
	private String email;
	
	private String psw;

	public IdPw(String email, String psw) {
		this.email = email;
		this.psw = psw;
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
	
}

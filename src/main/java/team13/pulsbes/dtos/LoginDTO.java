package team13.pulsbes.dtos;

public class LoginDTO {
	
	private String email;
	
	private String psw;

	
	public LoginDTO(String email, String psw) {
		super();
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

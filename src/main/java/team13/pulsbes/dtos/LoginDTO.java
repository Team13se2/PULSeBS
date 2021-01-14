package team13.pulsbes.dtos;

public class LoginDTO {
	
	private String email;
	private String id;
	private String name;
	private String surname;
	private String role;
	private String token;
	
	public LoginDTO(String email, String id, String name, String surname,String teacher, String token) {
		super();
		this.email = email;
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.role = teacher;
		this.token = token;
	}

	public LoginDTO() {
		this.id = "-1";
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String teacher) {
		this.role = teacher;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}

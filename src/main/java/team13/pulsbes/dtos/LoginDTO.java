package team13.pulsbes.dtos;

public class LoginDTO {
	
	private String Email;
	private String Id;
	private String Name;
	private String Surname;
	private String Role;
	private String Token;
	
	public LoginDTO(String email, String id, String name, String surname,String teacher, String token) {
		super();
		this.Email = email;
		this.Id = id;
		this.Name = name;
		this.Surname = surname;
		this.Role = teacher;
		this.Token = token;
	}

	public LoginDTO() {
		this.Id = "-1";
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		this.Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		this.Surname = surname;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String teacher) {
		this.Role = teacher;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		this.Token = token;
	}
	
	
	
}

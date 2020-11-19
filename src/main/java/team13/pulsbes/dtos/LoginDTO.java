package team13.pulsbes.dtos;

public class LoginDTO {
	
	private String Email;
	private String Id;
	private String Name;
	private String Surname;
	private Boolean Teacher;
	private String Token;
	
	public LoginDTO(String email, String id, String name, String surname,Boolean teacher, String token) {
		super();
		this.Email = email;
		this.Id = id;
		this.Name = name;
		this.Surname = surname;
		this.Teacher = teacher;
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

	public Boolean getTeacher() {
		return Teacher;
	}

	public void setTeacher(Boolean teacher) {
		this.Teacher = teacher;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		this.Token = token;
	}
	
	
	
}

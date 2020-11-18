package team13.pulsbes.dtos;

import lombok.Data;

import javax.persistence.Id;

@Data
public class StudentDTO {

    @Id
    private String Id;

    private String Name;

    private String Surname;

    private String Email;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

    
    
}

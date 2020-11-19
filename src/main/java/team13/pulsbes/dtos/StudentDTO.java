package team13.pulsbes.dtos;

import lombok.Data;

import javax.persistence.Id;


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

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

    
    
}

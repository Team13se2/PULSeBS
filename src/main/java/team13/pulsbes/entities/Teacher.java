package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder

public class Teacher {

    @Id
    private String id;
    private String name;

    private String surname;

    private String email;

    private String psw;
    @OneToMany (mappedBy = "teacher")
    List<Lecture> lectures;
    {
        lectures = new ArrayList<>();
    }
    
    @OneToMany (mappedBy = "teacher")
    List <Course> courses;
    {
        courses = new ArrayList<>();
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


	public List<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}


	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}

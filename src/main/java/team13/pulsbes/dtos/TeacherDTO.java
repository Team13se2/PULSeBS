package team13.pulsbes.dtos;

import lombok.Data;
import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToMany;

@Data
public class TeacherDTO
{
    @Id
    private String id;

    private String name;

    private String surname;

    private String email;

    List<Lecture> lectures;
    {
        lectures = new ArrayList<>();
    }

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

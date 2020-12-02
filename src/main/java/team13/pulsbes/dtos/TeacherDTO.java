package team13.pulsbes.dtos;

import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

public class TeacherDTO
{
    @Id
    private String Id;

    private String Name;

    private String Surname;

    private String Email;

    List<Lecture> lectures;
    {
        lectures = new ArrayList<>();
    }

    List <Course> courses;
    {
        courses = new ArrayList<>();
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
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		this.Email = email;
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

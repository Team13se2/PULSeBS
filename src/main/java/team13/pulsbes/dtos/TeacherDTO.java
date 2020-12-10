package team13.pulsbes.dtos;

import team13.pulsbes.entities.Course;
import team13.pulsbes.entities.Lecture;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

public class TeacherDTO
{
    @Id
    private String number;

    private String name;

    private String surname;

    private String email;

	private String SSN;



    List<Lecture> lectures;
    {
        lectures = new ArrayList<>();
    }

    List <Course> courses;
    {
        courses = new ArrayList<>();
    }
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	public String getSSN() { return SSN;}
	public void setSSN(String SSN) {this.SSN = SSN; }



}

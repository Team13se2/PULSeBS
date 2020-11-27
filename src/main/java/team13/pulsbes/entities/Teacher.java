package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team13.pulsbes.exception.InvalidCourseException;
import team13.pulsbes.exception.InvalidLectureException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Teacher {

    @Id
    private String Id;
    private String Name;

    private String Surname;

    private String Email;

    private String Psw;
    @OneToMany (mappedBy = "teacher",cascade = CascadeType.ALL, orphanRemoval = true)
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


	public String getPsw() {
		return Psw;
	}


	public void setPsw(String psw) {
		this.Psw = psw;
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

	public void removeLecture (Lecture lecture) throws InvalidLectureException, InvalidCourseException {

    	lectures.remove(lecture);
    	lecture.setTeacher(null);
		System.out.println("1" + lecture.getStudents());
		lecture.getStudents().forEach(s-> s.removeBookedLecture(lecture));
		lecture.getStudents().forEach(s-> System.out.println("2" + s.getBookedLectures()));
		lecture.getCourse().cancelLecture(lecture);
		System.out.println("3" + lecture.getCourse().getLectures());

	}
}

package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team13.pulsbes.exception.InvalidCourseException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Student {

    @Id
    private String Id;

    private String Name;

    private String Surname;

    private String Email;

    private List<Course> courses = new ArrayList<Course>();

	public void addCourse(Course c) throws InvalidCourseException {
    	if(c==null) {
    		throw new InvalidCourseException("Invalid Course");
    	}
    	
    	courses.add(c);
    }
    public void removeCourse(Course c) throws InvalidCourseException {
    	if(c==null) {
    		throw new InvalidCourseException("Invalid Course");
    	}
    	courses.remove(c);
	}
	
	public Student( String Id, String Name, String Surname) {
		super();
		this.Id = Id;
		this.Name = Name;
		this.Surname = Surname;
	}

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


	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	
}

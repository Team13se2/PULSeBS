package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import team13.pulsbes.exception.InvalidCourseException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder

public class Student {
    @Id
    private String id;

    private String name;

    private String surname;

    private String email;

    private String psw;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "courses_students",joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Course> courses;
    {
        courses = new ArrayList<>();

    }

	public Student( String Id, String Name, String Surname) {
		super();
		this.id = Id;
		this.name = Name;
		this.surname = Surname;
	}

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

	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	
}

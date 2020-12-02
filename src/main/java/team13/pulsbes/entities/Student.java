package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import team13.pulsbes.exception.InvalidCourseException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Builder

public class Student {
    @Id
    private String Id;

    private String Name;

    private String Surname;

    private String Email;

    private String Psw;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "courses_students",joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Course> courses;
    {
        courses = new ArrayList<>();

    }

    @ManyToMany (mappedBy = "students")
	List <Lecture> bookedLectures = new ArrayList<>();

	public Student() {};

	public Student( String Id, String Name, String Surname) {
		super();
		this.Id = Id;
		this.Name = Name;
		this.Surname = Surname;
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


    public void addBookLecture(Lecture l) {
		bookedLectures.add(l);
		if (l.getNrStudents() == null) {
			l.setNrStudents(1);
		}
		else {
			l.setNrStudents(l.getNrStudents()+1);
		}		
    }
    
    public void removeBookedLecture(Lecture l) {

    	this.bookedLectures.remove(l);
		l.getStudents().remove(this);
		l.setNrStudents(l.getNrStudents()-1);
		l.setAvailableSeat(l.getAvailableSeat()+1);
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

	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getPsw() {
		return Psw;
	}

	public void setPsw(String psw) {
		this.Psw = psw;
	}

	public List<Lecture> getBookedLectures() {
		return bookedLectures;
	}

	public void setBookedLectures(List<Lecture> bookedLectures) {
		this.bookedLectures = bookedLectures;
	}

	
}

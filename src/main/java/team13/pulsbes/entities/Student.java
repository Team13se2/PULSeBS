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
    private String id;

    private String name;

    private String surname;

	private String city;

	private String birthday;
	
    private String email;

    private String SSN;

    private String psw;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "courses_students",joinColumns = @JoinColumn(name = "course_code"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Course> courses;
    {
        courses = new ArrayList<>();

    }

    @ManyToMany (mappedBy = "students")
	List <Lecture> bookedLectures = new ArrayList<>();

	public Student() {};

	public Student( String id, String name, String surname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
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
		this.courses = courses;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public List<Lecture> getBookedLectures() {
		return bookedLectures;
	}

	public void setBookedLectures(List<Lecture> bookedLectures) {
		this.bookedLectures = bookedLectures;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
}

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
    @JoinTable(name = "courses_students",joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_code"))
    private List<Course> courses;
    {
        courses = new ArrayList<>();

    }

    @ManyToMany (mappedBy = "students")
	List <Lecture> bookedLectures = new ArrayList<>();

	@ManyToMany (mappedBy = "studentsPresent")
	List <Lecture> attendedLectures = new ArrayList<>();

	@ManyToMany (mappedBy = "studentswaiting")
	List <Lecture> waitingLectures = new ArrayList<>();

	public Student() {}

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
		if (l.getNrStudentsBooked() == null) {
			l.setNrStudentsBooked(1);
		}
		else {
			l.setNrStudentsBooked(l.getNrStudentsBooked()+1);
		}		
    }
    
    public void removeBookedLecture(Lecture l) {

    	this.bookedLectures.remove(l);
		l.getStudents().remove(this);
		l.setNrStudentsBooked(l.getNrStudentsBooked()-1);
		l.setAvailableSeat(l.getAvailableSeat()+1);
	}

	public void removewaitingLecture (Lecture l)
	{
		this.waitingLectures.remove(l);
		l.removeStudentsWaiting(this);
	}
	
	public void addLecturePresence(Lecture l) {
		attendedLectures.add(l);
		if (l.getNrStudentsPresent() == null) {
			l.setNrStudentsPresent(1);
		}
		else {
			l.setNrStudentsPresent(l.getNrStudentsPresent()+1);
		}		
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

	public List<Lecture> getAttendedLectures() {
		return attendedLectures;
	}

	public void setAttendedLectures(List<Lecture> attendedLectures) {
		this.attendedLectures = attendedLectures;
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

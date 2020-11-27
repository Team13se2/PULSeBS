package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team13.pulsbes.exception.InvalidStudentException;

import javax.persistence.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Lecture {

    @Id
    private String id;    
    
    private String startTime;
    
    private String endTime;

    private String subjectName;

    private String lectureType;
    //theory or exercitation

    private String surnameString;

    private Integer availableSeat;

    private Integer totalSeat;

	private String roomName;
	
	private static final String dateFormatString = "yyyy-MM-dd HH:mm";

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="lecture_students", joinColumns = @JoinColumn(name="lecture_id"),
            inverseJoinColumns = @JoinColumn(name="student_id"))

    private List<Student> students;
    {
        students = new ArrayList<>();
    }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn (name = "teacher_id")
    private Teacher teacher;

    
	public void addStudentAttending(Student s) throws InvalidStudentException {
    	if(s==null) {
    		throw new InvalidStudentException("Invalid Student");
    	}
    	students.add(s);
    }
    public void removeStudentAttending(Student s)throws InvalidStudentException {
    	if(s==null) {
    		throw new InvalidStudentException("Invalid Student");
    	}
    	students.remove(s);
	}
	
//	public Lecture( String Id, Integer AvailableSeat, Integer TotalSeat) {
//		super();
//		this.Id = Id;
//		this.AvailableSeat = AvailableSeat;
//		this.TotalSeat = TotalSeat;
//	}
    public void addStartTime(Integer year, Integer month, Integer day, Integer hour, Integer minutes) {

		Calendar c = Calendar.getInstance();
		
		c.set(year, month, day, hour, minutes, 0);
		
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		System.out.println(c.getTime().toString());
        String strDate = dateFormat.format(c.getTime());
        
		startTime = strDate;
    }
    public Date getStartTime2() throws ParseException{
    	Date notificationTime = new Date();
    	DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
    	
    	notificationTime = dateFormat.parse(startTime);
    	return notificationTime;
    }
    public void addEndTime(int year, int month, int day, int hour, int minutes) {
    	Calendar c = Calendar.getInstance();
		
		c.set(year, month, day, hour, minutes, 0);
		
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        String strDate = dateFormat.format(c.getTime());
        
		endTime = strDate;
    }
    public Date getEndTime2() throws ParseException {
    	Date notificationTime = new Date();
    	DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
    	//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    	
    	notificationTime = dateFormat.parse(endTime);
    	return notificationTime;
    }


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getLectureType() {
		return lectureType;
	}

	public void setLectureType(String lectureType) {
		this.lectureType = lectureType;
	}

	public String getSurnameString() {
		return surnameString;
	}

	public void setSurnameString(String surnameString) {
		this.surnameString = surnameString;
	}

	public Integer getAvailableSeat() {
		return availableSeat;
	}

	public void setAvailableSeat(Integer availableSeat) {
		this.availableSeat = availableSeat;
	}

	public Integer getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(Integer totalSeat) {
		this.totalSeat = totalSeat;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
    
    
}

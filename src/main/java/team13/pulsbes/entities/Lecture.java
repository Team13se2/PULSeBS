package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.model.Date;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder

public class Lecture {

    @Id
    private String Id;    
    
    private Date StartTime;
    
    private Date EndTime;

    private String SubjectName;

    private String LectureType;
    //theory or exercitation

    private String SurnameString;

    private Integer AvailableSeat;

    private Integer TotalSeat;

    private String RoomName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="lecture_students", joinColumns = @JoinColumn(name="lecture_id"),
            inverseJoinColumns = @JoinColumn(name="student_id"))
    private List<Student> students;
    {
        students = new ArrayList<>();
    }

    @ManyToOne
    @JoinColumn (name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn (name = "teacher_id")
    private Teacher teacher;

    public Lecture() {}
    
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
    
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public Date getEndTime() {
		return EndTime;
	}

	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public String getLectureType() {
		return LectureType;
	}

	public void setLectureType(String lectureType) {
		LectureType = lectureType;
	}

	public String getSurnameString() {
		return SurnameString;
	}

	public void setSurnameString(String surnameString) {
		SurnameString = surnameString;
	}

	public Integer getAvailableSeat() {
		return AvailableSeat;
	}

	public void setAvailableSeat(Integer availableSeat) {
		AvailableSeat = availableSeat;
	}

	public Integer getTotalSeat() {
		return TotalSeat;
	}

	public void setTotalSeat(Integer totalSeat) {
		TotalSeat = totalSeat;
	}

	public String getRoomName() {
		return RoomName;
	}

	public void setRoomName(String roomName) {
		RoomName = roomName;
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

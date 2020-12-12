package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import team13.pulsbes.exception.InvalidStudentException;

import javax.persistence.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Lecture {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;

    private String code;

	private String subjectName;

	private String startTime;
    
    private String endTime;

    private Integer availableSeat;

    private Integer totalSeat;

	private String roomName;

	private Integer nrStudentsBooked;

	private Integer nrStudentsPresent;

	private String day;

	private Boolean bookable;

	public Map<String, Integer> getQueue() {
		return queue;
	}

	public void setQueue(Map<String, Integer> queue) {
		this.queue = queue;
	}

	@ElementCollection
	private Map<String,Integer> queue;
	{
		queue=new HashMap<>();
	}

	private static final String DATE_FORMAT_STRING = "yyyy-MM-dd HH:mm";

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="lecture_students", joinColumns = @JoinColumn(name="lecture_id"),
            inverseJoinColumns = @JoinColumn(name="student_id"))

    private List<Student> students;
    {
        students = new ArrayList<>();
	}
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="lecture_students_present", joinColumns = @JoinColumn(name="lecture_id"),
            inverseJoinColumns = @JoinColumn(name="student_id"))

    private List<Student> studentsPresent;
    {
        students = new ArrayList<>();
    }


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn (name = "teacher_number")
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void addStudentPresent(Student s) throws InvalidStudentException {
    	if(s==null) {
    		throw new InvalidStudentException("Invalid Student");
    	}
    	studentsPresent.add(s);
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
		
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
        String strDate = dateFormat.format(c.getTime());
        
		startTime = strDate;
    }
    public Date getStartTime2() throws ParseException{
    	//Date notificationTime = new Date();
    	DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
    	
    	//Date notificationTime = dateFormat.parse(startTime);
    	return dateFormat.parse(startTime);
    }
    public void addEndTime(int year, int month, int day, int hour, int minutes) {
    	Calendar c = Calendar.getInstance();
		
		c.set(year, month, day, hour, minutes, 0);
		
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
        String strDate = dateFormat.format(c.getTime());
        
		endTime = strDate;
    }
    public Date getEndTime2() throws ParseException {
    	//Date notificationTime = new Date();
    	DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);
    	//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    	
    	//Date notificationTime = dateFormat.parse(endTime);
    	return dateFormat.parse(endTime);
    }


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Integer getNrStudentsBooked(){
		return nrStudentsBooked;
	}
	public void setNrStudentsBooked(Integer nrStudentsBooked){
		this.nrStudentsBooked = nrStudentsBooked;
	}

	public Integer getNrStudentsPresent(){
		if(nrStudentsPresent == null) {
			return 0;
		}
		else return nrStudentsPresent;
	}
	public void setNrStudentsPresent(Integer nrStudentsPresent){
		this.nrStudentsPresent = nrStudentsPresent;
	}

	public Boolean isBookable() {
		return bookable;
	}

	public void setBookable(Boolean bookable) {
		this.bookable = bookable;
	}


	public List<Student> getStudentsPresent() {
        return studentsPresent;
    }
    
    

	public Boolean getBookable() {
		return bookable;
	}


	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}



}

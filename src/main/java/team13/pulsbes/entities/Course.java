package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import team13.pulsbes.exception.InvalidStudentException;
import team13.pulsbes.exception.InvalidLectureException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Course {

   @Id

   private String id;

   private String name;

   private Integer cancelledLectures;

   @ManyToOne
   @JoinColumn (name = "teacher_id")
   private Teacher teacher;

   @ManyToMany(mappedBy = "courses")
   private List<Student> students = new ArrayList<>();

   @OneToMany (mappedBy = "course")

    List<Lecture> lectures;
    {
        lectures = new ArrayList<>();
	}
	

	public void newStudentEnrolled(Student s) throws InvalidStudentException {
		if(s==null) {
			throw new InvalidStudentException("Invalid Student");
		}
		students.add(s);
	}
	public void studentRemove(Student s) throws InvalidStudentException {
		if(s==null) {
			throw new InvalidStudentException("Invalid Student");
		}
		students.remove(s);
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
	public Integer getCancelledLectures() {
		return cancelledLectures;
	}
	public void setCancelledLectures(Integer cancelledLectures) {
		this.cancelledLectures = cancelledLectures;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}

	public void cancelLecture(Lecture lecture) throws InvalidLectureException{
		if(lecture==null) {
			throw new InvalidLectureException("Invalid lecture");
		}
		lectures.remove(lecture);
		setCancelledLectures(getCancelledLectures() + 1);
	}
	
}




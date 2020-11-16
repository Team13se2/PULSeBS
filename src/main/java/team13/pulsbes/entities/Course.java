package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team13.pulsbes.exception.InvalidStudentException;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder

public class Course {

   @Id

   private String Id;

   private String name;

   @ManyToOne
   @JoinColumn (name = "teacher_id")
   private Teacher teacher;

   @ManyToMany(mappedBy = "courses")
   private List<Student> students = new ArrayList<>();

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
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}




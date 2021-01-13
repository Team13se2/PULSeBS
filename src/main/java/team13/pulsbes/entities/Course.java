package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import team13.pulsbes.exception.InvalidStudentException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder


public class Course {

    @Id
    private String code;

    private String year;

    private String semester;

    private String name;

    @ManyToOne
    @JoinColumn (name = "teacher_number")
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
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
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
    public List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }
  /*  public void cancelLecture(Lecture lecture) throws InvalidLectureException{

        if(lecture==null) {
            throw new InvalidLectureException("Invalid lecture");
        }
        lectures.remove(lecture);
    }*/
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}

}




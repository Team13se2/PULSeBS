package team13.pulsbes.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team13.pulsbes.model.Date;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
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

    private Integer BookedSeat;

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

}

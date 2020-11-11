package team13.pulsbes.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Teacher {

    @Id

    private String id;

    private String name;

    private String surname;

    private String Email;

    @OneToMany (mappedBy = "teacher_id")
    List<Lecture> lectures;
    {
        lectures = new ArrayList<>();
    }


}

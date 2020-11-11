package team13.pulsbes.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn (name = "lecture_id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn (name = "student_id")
    private Student student;




}

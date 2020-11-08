package team13.pulsbes.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Student {

    @Id
    private String id;

    private String Name;

    private String Surname;

    private String Matricola;

    private String Email;



}

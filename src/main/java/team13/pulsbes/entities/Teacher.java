package team13.pulsbes.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Teacher {

    @Id

    private String id;

    private String name;

    private String surname;

    private String Email;


}

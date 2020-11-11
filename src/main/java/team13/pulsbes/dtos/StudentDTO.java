package team13.pulsbes.dtos;

import lombok.Data;

import javax.persistence.Id;

@Data
public class StudentDTO {

    @Id
    private String Id;

    private String Name;

    private String Surname;

    private String Email;


}

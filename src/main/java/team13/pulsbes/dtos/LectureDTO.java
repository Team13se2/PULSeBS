package team13.pulsbes.dtos;


import lombok.Data;
import org.hibernate.exception.DataException;

import javax.persistence.Id;
import java.util.Date;

@Data
public class LectureDTO {

    @Id
    private String Id;

    private Date StartTime;

    private Date EndTime;

    private String LectureType;

    private String SurnameString;

    private Integer AvailableSeat;

    private Integer TotalSeat;

    private String RoomName;
}

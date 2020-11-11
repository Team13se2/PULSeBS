package team13.pulsbes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Date implements Serializable {

    private String day;
    private String month;
    private String year;
    private String hour;


}

package team13.pulsbes.dtos;


import lombok.Data;
import team13.pulsbes.entities.Student;

import org.hibernate.exception.DataException;

import javax.persistence.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class LectureDTO {

    @Id
    private String Id;

    private Date StartTime;

    private Date EndTime;
    
    private String SubjectName;

    private String LectureType;

    private String SurnameString;

    private Integer AvailableSeat;

    private Integer TotalSeat;

    private String RoomName;

    
	public LectureDTO(){}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Date getStartTime() {
		return StartTime;
	}
	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}
	public Date getEndTime() {
		return EndTime;
	}
	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}
	public String getLectureType() {
		return LectureType;
	}
	public void setLectureType(String lectureType) {
		LectureType = lectureType;
	}
	public String getSurnameString() {
		return SurnameString;
	}
	public void setSurnameString(String surnameString) {
		SurnameString = surnameString;
	}
	public Integer getAvailableSeat() {
		return AvailableSeat;
	}
	public void setAvailableSeat(Integer availableSeat) {
		AvailableSeat = availableSeat;
	}
	public Integer getTotalSeat() {
		return TotalSeat;
	}
	public void setTotalSeat(Integer totalSeat) {
		TotalSeat = totalSeat;
	}
	public String getRoomName() {
		return RoomName;
	}
	public void setRoomName(String roomName) {
		RoomName = roomName;
	}
	public String getSubjectName() {
		return SubjectName;
	}
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}
    
}

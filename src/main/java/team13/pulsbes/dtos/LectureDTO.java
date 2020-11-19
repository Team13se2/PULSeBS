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
    private String id;

    private Date startTime;

    private Date endTime;
    
    private String subjectName;

    private String lectureType;

    private String surnameString;

    private Integer availableSeat;

    private Integer totalSeat;

    private String roomName;

    
	public LectureDTO(){}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getLectureType() {
		return lectureType;
	}
	public void setLectureType(String lectureType) {
		this.lectureType = lectureType;
	}
	public String getSurnameString() {
		return surnameString;
	}
	public void setSurnameString(String surnameString) {
		this.surnameString = surnameString;
	}
	public Integer getAvailableSeat() {
		return availableSeat;
	}
	public void setAvailableSeat(Integer availableSeat) {
		this.availableSeat = availableSeat;
	}
	public Integer getTotalSeat() {
		return totalSeat;
	}
	public void setTotalSeat(Integer totalSeat) {
		this.totalSeat = totalSeat;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
    
}

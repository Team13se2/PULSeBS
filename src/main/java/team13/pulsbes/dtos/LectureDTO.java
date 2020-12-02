package team13.pulsbes.dtos;

import javax.persistence.Id;

public class LectureDTO {

    @Id
    private String Id;

    private String startTime;

    private String endTime;
    
    private String subjectName;

    private String lectureType;

    private String surnameString;

    private Integer availableSeat;

    private Integer totalSeat;

    private String roomName;

    private Integer nrStudents;

    
	public LectureDTO(){}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		this.Id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
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
    public Integer getNrStudents(){
		return nrStudents;
	}
	public void setNrStudents(Integer nrStudents){
		this.nrStudents = nrStudents;
	}
}

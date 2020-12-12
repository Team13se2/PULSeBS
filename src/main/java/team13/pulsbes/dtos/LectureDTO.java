package team13.pulsbes.dtos;

import javax.persistence.Id;

public class LectureDTO {





	@Id
    Integer id;

	private String code;

    private String startTime;

    private String endTime;
    
    private String subjectName;

    private Integer availableSeat;

    private Integer totalSeat;

    private String roomName;

	private Integer nrStudentsBooked;

	private Integer nrStudentsPresent;
	
	private Boolean bookable;

    
	public LectureDTO(){}
	public Integer getId() {
		return id;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getBookable() {
		return bookable;
	}
	public void setId(Integer id) {
		this.id = id;
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
    public Integer getNrStudentsBooked(){
		return nrStudentsBooked;
	}
	public void setNrStudentsBooked(Integer nrStudentsBooked){
		this.nrStudentsBooked = nrStudentsBooked;
	}
	public Integer getNrStudentsPresent(){
		return nrStudentsPresent;
	}
	public void setNrStudentsPresent(Integer nrStudentsPresent){
		this.nrStudentsPresent = nrStudentsPresent;
	}

	public Boolean isBookable() {
		return bookable;
	}

	public void setBookable(Boolean bookable) {
		this.bookable = bookable;
	}
}

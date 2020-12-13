class LectureDTO{
    constructor(id,code,startTime,endTime,subjectName,availableSeat,totalSeat,roomName,nrStudentsBooked,nrStudentsPresent,bookable){
        this.id = id;
        this.code = code;
        this.availableSeat = availableSeat;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalSeat = totalSeat;
        this.roomName = roomName;
        this.subjectName = subjectName;
        this.nrStudentsBooked = nrStudentsBooked;
        this.nrStudentsPresent = nrStudentsPresent;
        this.bookable = bookable;
    }
}
export default LectureDTO;
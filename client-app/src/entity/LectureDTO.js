class LectureDTO{
    constructor(id,availableSeat,startTime,endTime,lectureType,surnameString,totalSeat,roomName){
        this.id = id;
        this.availableSeat = availableSeat;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lectureType = lectureType;
        this.surnameString = surnameString;
        this.totalSeat = totalSeat;
        this.roomName = roomName;
    }
}
export default LectureDTO;
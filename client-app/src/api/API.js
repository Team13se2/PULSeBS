import LectureDTO from "../entity/LectureDTO";

const baseURL = "";

async function userLogin(email, psw, type) {
    return new Promise((resolve, reject) => {
        fetch(baseURL + '/login/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({email: email, psw: psw, teacher: type == "teacher" ? 1 : 0}),
        }).then((response) => {
            if (response.ok) {
                response.json().then((user) => {
                    resolve(user);
                });
            } else {
                // analyze the cause of error
                response.json()
                    .then((obj) => { reject(obj); }) // error msg in the response body
                    .catch((err) => { reject({ errors: [{ param: "Application", msg: "Cannot parse server response" }] }) }); // something else
            }
        }).catch((err) => { reject({ errors: [{ param: "Server", msg: "Cannot communicate" }] }) }); // connection errors
    });
}

async function userLogout(username, password) {
    return new Promise((resolve, reject) => {
        fetch(baseURL + '/login/logout', {
            method: 'POST',
        }).then((response) => {
            if (response.ok) {
                resolve(null);
            } else {
                // analyze the cause of error
                response.json()
                    .then((obj) => { reject(obj); }) // error msg in the response body
                    .catch((err) => { reject({ errors: [{ param: "Application", msg: "Cannot parse server response" }] }) }); // something else
            }
        });
    });
}

async function getAllLectures(){
    let url = "/teacher/getAllLectures";

    const response = await fetch(baseURL + url);
    const lecturesJSON = await response.json();
    if(response.ok){
        /*let lectures = lecturesJSON.map((l) => new LectureDTO(l.id,l.availableSeat,l.startTime,l.endTime,l.lectureType,l.surnameString,l.totalSeat,l.roomName,l.subjectName));
        lectures.forEach(async function(element ,i){
            const nr = await (await getNumberStudentsAttending(element.id)).nrStudents;
            lectures[i].nrStudents = nr;
        });
        return lectures;*/
        return lecturesJSON.map((l) => new LectureDTO(l.id,l.availableSeat,l.startTime,l.endTime,l.lectureType,l.surnameString,l.totalSeat,l.roomName,l.subjectName,l.nrStudents));
    } else {
        let err = {status: response.status, errObj:lecturesJSON};
        throw err;  // An object with the error coming from the server
    }
}

async function getNumberStudentsAttending(lecture_id){
    let url = "/teacher/getNumberStudentsAttending"+"?lecture_id="+lecture_id;

    const response = await fetch(baseURL + url);
    const nrStudents = await response.json();
    if(response.ok){
        return {nrStudents: nrStudents};
    } else {
        let err = {status: response.status, errObj:nrStudents};
        throw err;  // An object with the error coming from the server
    }
}

async function getStudentList(lecture_id){
    let url = "/teacher/getStudentList"+"?lecture_id="+lecture_id;

    const response = await fetch(baseURL + url);
    const students = await response.json();
    if(response.ok){
        return {students: students};
    } else {
        let err = {status: response.status, errObj:students};
        throw err;  // An object with the error coming from the server
    }
}

async function getAllLecturesStudent(){
    let url = "/student/getAllLectures";

    const response = await fetch(baseURL + url);
    const lecturesJSON = await response.json();
    if(response.ok){
        /*let lectures = lecturesJSON.map((l) => new LectureDTO(l.id,l.availableSeat,l.startTime,l.endTime,l.lectureType,l.surnameString,l.totalSeat,l.roomName,l.subjectName));
        lectures.forEach(async function(element ,i){
            const nr = await (await getNumberStudentsAttending(element.id)).nrStudents;
            lectures[i].nrStudents = nr;
        });
        return lectures;*/
        return lecturesJSON.map((l) => new LectureDTO(l.id,l.availableSeat,l.startTime,l.endTime,l.lectureType,l.surnameString,l.totalSeat,l.roomName,l.subjectName,l.nrStudents));
    } else {
        let err = {status: response.status, errObj:lecturesJSON};
        throw err;  // An object with the error coming from the server
    }
}

async function getBookedLectures(){
    let url = "/student/getBookedLectures";

    const response = await fetch(baseURL + url);
    const lecturesJSON = await response.json();
    if(response.ok){
        /*let lectures = lecturesJSON.map((l) => new LectureDTO(l.id,l.availableSeat,l.startTime,l.endTime,l.lectureType,l.surnameString,l.totalSeat,l.roomName,l.subjectName));
        lectures.forEach(async function(element ,i){
            const nr = await (await getNumberStudentsAttending(element.id)).nrStudents;
            lectures[i].nrStudents = nr;
        });
        return lectures;*/
        return lecturesJSON.map((l) => new LectureDTO(l.id,l.availableSeat,l.startTime,l.endTime,l.lectureType,l.surnameString,l.totalSeat,l.roomName,l.subjectName,l.nrStudents));
    } else {
        let err = {status: response.status, errObj:lecturesJSON};
        throw err;  // An object with the error coming from the server
    }
}

async function bookLecture(lecture_id){
    let url = "/student/bookLecture?"+"lecture_id="+lecture_id;
    const response = await fetch(baseURL + url);
    return true; 
}

const API = {userLogin,userLogout,getAllLectures,getNumberStudentsAttending,getStudentList,getAllLecturesStudent,getBookedLectures,bookLecture} ;
export default API;
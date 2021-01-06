import LectureDTO from "../entity/LectureDTO";
import Cookies from 'js-cookie';
import StudentDTO from "../entity/StudentDTO";

const baseURL = "";

async function isAuthenticated(){
    const id = Cookies.get("id");
    const username = Cookies.get("username");
    const type = Cookies.get("type");
    if (id !== undefined && username !== undefined && type !== undefined) {
        const user = {
            id: id,
            username: username,
            type: type
        };
        return user;
    }else{
        let err = {status: "authentication Error", errObj:"Authentication Error!"};
        throw err;  // An object with the error coming from the server
    }
}

async function userLogin(email, psw) {
    return new Promise((resolve, reject) => {
        fetch(baseURL + '/login/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({email: email, psw: psw,teacher:1}),
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

async function getPastLectures(){
    let url = "/teacher/getPastLectures";

    const response = await fetch(baseURL + url);
    const lecturesJSON = await response.json();
    if(response.ok){
        return lecturesJSON.map((l) => new LectureDTO(l.id,l.code,l.startTime,l.endTime,l.subjectName,l.availableSeat,l.totalSeat,l.roomName,l.nrStudentsBooked,l.nrStudentsPresent,l.bookable));
    } else {
        let err = {status: response.status, errObj:lecturesJSON};
        throw err;  // An object with the error coming from the server
    }
}

async function getAllLectures(){
    let url = "/teacher/getAllLectures";

    const response = await fetch(baseURL + url);
    const lecturesJSON = await response.json();
    if(response.ok){
        return lecturesJSON.map((l) => new LectureDTO(l.id,l.code,l.startTime,l.endTime,l.subjectName,l.availableSeat,l.totalSeat,l.roomName,l.nrStudentsBooked,l.nrStudentsPresent,l.bookable));
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


async function getNoBookedLectures(){
    let url = "/student/getAllLectures";

    const response = await fetch(baseURL + url);
    const lecturesJSON = await response.json();
    if(response.ok){
        return lecturesJSON.map((l) => new LectureDTO(l.id,l.code,l.startTime,l.endTime,l.subjectName,l.availableSeat,l.totalSeat,l.roomName,l.nrStudentsBooked,l.nrStudentsPresent,l.bookable));
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
        return lecturesJSON.map((l) => new LectureDTO(l.id,l.code,l.startTime,l.endTime,l.subjectName,l.availableSeat,l.totalSeat,l.roomName,l.nrStudentsBooked,l.nrStudentsPresent,l.bookable));
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

async function getStudentList(lecture_id){
    let url = "/teacher/getStudentList?lecture_id="+lecture_id;

    const response = await fetch(baseURL + url);
    const studentJSON = await response.json();
    if(response.ok){;
        console.log(studentJSON);
        return studentJSON.map((l) => new StudentDTO(l.student.name,l.student.id,l.student.email,l.student.surname,l.presence));
    } else {
        let err = {status: response.status, errObj:studentJSON};
        throw err;  // An object with the error coming from the server
    }
}

async function removeStudentLecture(lecture_id) {
    return new Promise((resolve, reject) => {
        fetch(baseURL + '/student/cancelLecture?lecture_id='+lecture_id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        }).then((response) => {
            if (response.ok) {
                resolve(response);
            } else {
                // analyze the cause of error
                response.json()
                    .then((obj) => { reject(obj); }) // error msg in the response body
                    .catch((err) => { reject({ errors: [{ param: "Application", msg: "Cannot parse server response" }] }) }); // something else
            }
        }).catch((err) => { reject({ errors: [{ param: "Server", msg: "Cannot communicate" }] }) }); // connection errors
    });
}

async function removeTeacherLecture(lecture_id) {
    return new Promise((resolve, reject) => {
        fetch(baseURL + '/teacher/cancelLecture?lecture_id='+lecture_id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
        }).then((response) => {
            if (response.ok) {
                resolve(response);
            } else {
                // analyze the cause of error
                response.json()
                    .then((obj) => { reject(obj); }) // error msg in the response body
                    .catch((err) => { reject({ errors: [{ param: "Application", msg: "Cannot parse server response" }] }) }); // something else
            }
        }).catch((err) => { reject({ errors: [{ param: "Server", msg: "Cannot communicate" }] }) }); // connection errors
    });
}

async function getAllLecturesBookingManager(){
    let url = "/booking_manager/getAllLectures";

    const response = await fetch(baseURL + url);
    const lecturesJSON = await response.json();
    if(response.ok){
        return lecturesJSON.map((l) => new LectureDTO(l.id,l.code,l.startTime,l.endTime,l.subjectName,l.availableSeat,l.totalSeat,l.roomName,l.nrStudentsBooked,l.nrStudentsPresent,l.bookable));
    } else {
        let err = {status: response.status, errObj:lecturesJSON};
        throw err;  // An object with the error coming from the server
    }
}


async function uploadStudentCSV(file,type) {
    return new Promise((resolve, reject) => {
        fetch(baseURL + '/support_officer/'+type,{
            method: 'POST',
            headers: {
                'Content-Type': 'text/csv',
            },
            body: file
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

async function addPresence(studentId,lecture_id) {
    return new Promise((resolve, reject) => {
        fetch(baseURL + '/teacher/addPresence',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({studentId: studentId, lectureId: lecture_id})
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

async function getCurrentLectureTeacher(){
    let url = "/teacher/getCurrentLectures";

    const response = await fetch(baseURL + url);
    const lecturesJSON = await response.json();
    if(response.ok){
        return lecturesJSON.map((l) => new LectureDTO(l.id,l.code,l.startTime,l.endTime,l.subjectName,l.availableSeat,l.totalSeat,l.roomName,l.nrStudentsBooked,l.nrStudentsPresent,l.bookable));
    } else {
        let err = {status: response.status, errObj:lecturesJSON};
        throw err;  // An object with the error coming from the server
    }
}

async function getLecturesOfTheDay(){
    let url = "/teacher/getLecturesOfTheDay";

    const response = await fetch(baseURL + url);
    const lecturesJSON = await response.json();
    if(response.ok){
        return lecturesJSON.map((l) => new LectureDTO(l.id,l.code,l.startTime,l.endTime,l.subjectName,l.availableSeat,l.totalSeat,l.roomName,l.nrStudentsBooked,l.nrStudentsPresent,l.bookable));
    } else {
        let err = {status: response.status, errObj:lecturesJSON};
        throw err;  // An object with the error coming from the server
    }
}

async function updateListSupportOfficer(year,dateStart,dateEnd,type) {
    return new Promise((resolve, reject) => {
        const url= "/support_officer/"+(type === "add" ? "readdLectures" : "removeLectures")+"?year="+year+"&dateStart="+dateStart+"&dateEnd="+dateEnd;
        console.log(url);
        fetch(url,{
            method: 'POST',
            /*headers: {
                'Content-Type': 'text/csv',
            },*/
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

async function getWaitingLecture(){
    let url = "/student/getWaitingLectures";

    const response = await fetch(baseURL + url);
    const lecturesJSON = await response.json();
    if(response.ok){
        return lecturesJSON.map((l) => new LectureDTO(l.id,l.code,l.startTime,l.endTime,l.subjectName,l.availableSeat,l.totalSeat,l.roomName,l.nrStudentsBooked,l.nrStudentsPresent,l.bookable));
    } else {
        let err = {status: response.status, errObj:lecturesJSON};
        throw err;  // An object with the error coming from the server
    }
}

async function getContactReport(studentId){
    let url = "/booking_manager/getContactReport?studentId="+studentId;

    const response = await fetch(baseURL + url);
    const responseJSON = await response.json();    
    if(response.ok){ 
        return responseJSON.map((l) => new StudentDTO(l.name,l.id,l.email,l.surname,true));
    } else {
        let err = {status: response.status, errObj:responseJSON};
        throw err;  // An object with the error coming from the server
    }
}

async function removeHolidaysSupportOfficer(dateStart,dateEnd) {
    return new Promise((resolve, reject) => {
        const url= "/support_officer/"+"removeHolidays"+"?&dateStart="+dateStart+"&dateEnd="+dateEnd;
        console.log(url);
        fetch(url,{
            method: 'POST',
            /*headers: {
                'Content-Type': 'text/csv',
            },*/
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


const API = {isAuthenticated,userLogin,userLogout,getAllLectures,
    getNumberStudentsAttending,getStudentList,
    getNoBookedLectures,getBookedLectures,
    bookLecture,removeStudentLecture,removeTeacherLecture,
    getPastLectures,getAllLecturesBookingManager,
    uploadStudentCSV,addPresence,getCurrentLectureTeacher,updateListSupportOfficer,
    getWaitingLecture,getContactReport,getLecturesOfTheDay,
    removeHolidaysSupportOfficer} ;
export default API;
import LoginForm from './components/LoginForm';
import PastLecturesFilter from './components/PastLecturesFilter'
import React from 'react';
import {AuthContext} from './auth/AuthContext';
// import "react-big-calendar/lib/css/react-big-calendar.css";
import {Redirect, Route, Link} from 'react-router-dom';
import {Switch} from 'react-router';
import {withRouter} from 'react-router-dom';
import Header from './components/Header';
import LecturesTable from './components/LecturesTable';
import './App.css';
import API from './api/API';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import LecturesTableTeacher from './components/LectureTableTeacher';
import MyCalendar from './components/MyCalendar';
import MonthChart from './components/MonthChart';
import Day_Picker from './components/Day_Picker';
import moment from 'moment';
import MonthNrStudents from './components/MonthNrStudents';
import LecturesTableNoBooked from './components/LecturesTableNoBooked';
import UploadFiles from './components/UploadFiles';
import MonthChartBookingManager from './components/MonthChartBookingManager';
import RadioButtonsCSV from './components/RadioButtonsCSV';
import ContactTracing from './components/ContactTracing';

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {noBookedLectures: [],bookedLectures: [],teacherLecture:[],bookingManagerLecture: []};
    }

    componentDidMount() { // check if the user is authenticated
        API.isAuthenticated().then((user) => {
            this.setState({authUser: user});
        }).catch((err) => {
            this.setState({authErr: err.errorObj});
            this.props.history.push("/login");
        });
    }

    handleErrors(err) {
        if (err) {
            if (err.status && err.status === 401) {
                this.setState({authErr: err.errorObj});
                this.props.history.push("/login");
            }
        }
    }

    // Add a logout method
    logout = () => {
        console.log("logout");
        API.userLogout().then(() => {
            this.setState({authUser: undefined, authErr: undefined});
            this.props.history.push("/");
        });
    }

    // Add a login method
    login = (username, password) => {
        API.userLogin(username, password).then((user) => {
            let usr;
            console.log(user);
            if(user.id != -1){
                usr = {id: user.id, password: user.password, type: user.role};
            }
            console.log(usr);
            this.setState({authUser: usr});
            if (user.id == -1) {
                this.setState({authErr: "Login Error"});
            }
        }).catch((errorObj) => {
            const err0 = errorObj.errors[0];
            this.setState({authErr: err0});
        });
    }

    getNoBookedLectures = () =>{
        API.getNoBookedLectures().then((lecture) =>{
            this.setState({noBookedLectures: lecture, authErr: undefined});
        }).catch((err) =>{
            console.log(err);
        })
    };

    getBookedLectures = () =>{
        API.getBookedLectures().then((lecture) =>{
            this.setState({bookedLectures: lecture, authErr: undefined});
        }).catch((err) =>{
            console.log(err);
        })
    };

    bookLecture = (lecture_id) =>{
        API.bookLecture(lecture_id).then((response) =>{
        let l = this.state.noBookedLectures.filter((l =>l.id !== lecture_id));
        this.setState({noBookedLectures: l});
        this.getBookedLectures();
        }).catch((err) =>{
            console.log(err);
        })
    };

    getStudentList = (lecture_id) =>{
        API.getStudentList(lecture_id).then((response) =>{
            this.setState({students: response});
        }).catch((err) =>{
            console.log(err);
        })
    }

    getAllLecturesTeacher = () =>{
        API.getAllLectures().then((lecture) =>{
            this.setState({teacherLecture: lecture});
        }).catch((err) =>{
            console.log(err);
        })
    }

    getPastLecturesTeacher = () =>{
        API.getPastLectures().then((lecture) =>{
            const l = lecture.sort((a,b) =>{
                return a.id > b.id;
            })
            this.setState({teacherLecture: lecture});
        }).catch((err) =>{
            console.log(err);
        })
    }

    removeTeacherLecture = (lecture_id) =>{
        API.removeTeacherLecture(lecture_id).then((response) =>{
            this.getAllLecturesTeacher();
        })
    }

    removeLecture = (lecture_id) =>{
        API.removeStudentLecture(lecture_id).then((response) =>{
            console.log(response);
            this.getBookedLectures();
            this.getNoBookedLectures();
        })
    }

    addWaitingList = (lecture_id) =>{
        console.log("Inserire l'API");
    }

    getAllLecturesBookingManager = () =>{
        API.getAllLecturesBookingManager().then((lecture) =>{
            console.log(lecture);
            this.setState({bookingManagerLecture: lecture});
        }).catch((err) =>{
            console.log(err);
        })
    }

    selectWeek = (start,end) =>{
        let sum = 0;
        let nrLectures = 0;
        this.state.teacherLecture.forEach(lecture => {
            const format1 = "YYYY-MM-DD HH:mm:ss";
            let startTime = moment(lecture.startTime).format(format1);
            startTime = moment(startTime,"YYYY-MM-DD HH:mm:ss");
            console.log(lecture);
            if(startTime.isBetween(start,end)){
                sum += lecture.nrStudentsBooked;
                nrLectures ++;
            }
        });
        this.setState({nrStudents: sum/(nrLectures == 0 ? 1: nrLectures)});
    }

    selectMonth = (month) =>{
        let sum = 0;
        let nrLectures = 0;
        this.state.teacherLecture.forEach(lecture =>{
            const format1 = "YYYY-MM-DD HH:mm:ss";
            let startTime = moment(lecture.startTime).format(format1);
            startTime = moment(startTime,"YYYY-MM-DD HH:mm:ss");
            console.log(month);
            if(startTime.month() == month){
                sum += lecture.nrStudentsBooked;
                nrLectures ++;
            }
        });
        console.log(sum);
        this.setState({nrStudents: sum/(nrLectures == 0 ? 1: nrLectures)});
    }

    uploadStudentCSV = (file) =>{
        let type = "";
        switch (this.state.CSV) {
            case "students":
                type = "addStudents";
                break;
            case "teachers":
                type = "addTeachers";
                break;
            case "courses":
                type = "addCourses";
                break;
            case "enrollment":
                type = "enrollStudents";
                break;
            default:
                break;
        }
        API.uploadStudentCSV(file,type).then((e) =>{
            console.log("upload andato a buon fine");
        }).catch((err) =>{
            throw err;
        })
    }

    selectListForCSV = (value) =>{
        this.setState({CSV: value});
    }

    addIdContactTracing = (id) =>{
        console.log("call API with "+id);
    }



    render() {
        const value = {
            authUser: this.state.authUser,
            authErr: this.state.authErr,
            loginUser: this.login,
            logoutUser: this.logout
        }
        return (<AuthContext.Provider value={value}>
            <Row className="rowHeader">
                <Header />
            </Row>
                
            <Switch >
                <Route path="/login">
                    <Row className="vheight-100">
                        <Col sm={4}></Col>
                        <Col sm={4}
                            className="below-nav loginColumn">
                            <LoginForm/>
                        </Col>
                    </Row>
                </Route>
                <Route path="/student">
                    <Switch>
                        <Route exact path="/student/calendar">
                            <Row className="vheight-0">
                                <Col sm={1}/>
                                <Col sm={8}className="below-nav">
                                    <MyCalendar lectures={this.state.bookedLectures.map(function(elem) {
                                        const format = "YYYY-MM-DD HH:mm:ss";
                                        return {
                                          start: new Date (elem.startTime),
                                          end: new Date(elem.endTime),
                                          title: elem.subjectName,
                                        } 
                                      })
                                    } getBookedLectures={this.getBookedLectures}/>
                                </Col>
                                <Col sm={1}/>
                            </Row>
                        </Route>
                        <Route exact path="/student/noBookedLectures">
                            <Row className="vheight-0">
                                <Col sm={1}/>
                                <Col sm={8}
                                    className="below-nav">
                                    <h1>No Booked Lectures</h1>
                                    <LecturesTableNoBooked lectures={this.state.noBookedLectures} getLectures={this.getNoBookedLectures} remove={false} job={this.bookLecture} job2={this.addWaitingList}/>
                                </Col>
                                <Col sm={1}/>
                            </Row>
                        </Route>
                        <Route exact path="/student/">
                            <Row className="">
                                <Col sm={1}/>
                                <Col sm={10}
                                    className="below-nav">
                                    <h1>Booked Lectures</h1>
                                    <LecturesTable lectures={this.state.bookedLectures} getLectures={this.getBookedLectures} remove={true} job={this.removeLecture}/>
                                </Col>
                                <Col sm={1}/>
                            </Row>
                        </Route>
                    </Switch>
                </Route>
                <Route path="/teacher">
                    <Switch>
                        <Route path="/teacher/pastLectures">
                            <Route path="/teacher/pastLectures">
                                <Row className="">
                                    <Col sm={2} className="overflow">
                                        <PastLecturesFilter getAllPastLectures={this.getPastLecturesTeacher}/>
                                    </Col>
                                    <Switch>
                                        <Route exact path="/teacher/pastLectures/all">
                                            <Col sm={8}
                                                className="below-nav">
                                                <h1>Past Lectures</h1>
                                                <LecturesTableTeacher lectures={this.state.teacherLecture} past={true} getLectures={this.getPastLecturesTeacher}/>
                                            </Col>
                                            <Col sm={1}/>
                                        </Route>
                                        <Route exact path="/teacher/pastLectures/week">
                                            <Col sm={3}
                                                className="below-nav">
                                                <h1>Week</h1>
                                                <Day_Picker selectWeek={this.selectWeek}/>
                                                
                                            </Col>
                                            <Col sm={3} className="below-nav">
                                                {this.state.nrStudents !== undefined && <h1>There are {this.state.nrStudents.toFixed(1)} booked per lecture</h1>}
                                            </Col>
                                            <Col sm={1}/>
                                        </Route>
                                        <Route path="/teacher/pastLectures/month">
                                            <Col sm={8}
                                                className="below-nav">
                                                    <Switch>
                                                        <Route exact path="/teacher/pastLectures/month/January">
                                                            <MonthNrStudents selectMonth={this.selectMonth} month={0}/>
                                                            <h1>January</h1>
                                                        </Route>
                                                        <Route exact path="/teacher/pastLectures/month/February">
                                                            <MonthNrStudents selectMonth={this.selectMonth} month={1}/>
                                                            <h1>February</h1>
                                                        </Route>
                                                        <Route exact path="/teacher/pastLectures/month/March">
                                                            <MonthNrStudents selectMonth={this.selectMonth} month={2}/>
                                                            <h1>March</h1>
                                                        </Route>
                                                        <Route exact path="/teacher/pastLectures/month/April">
                                                            <MonthNrStudents selectMonth={this.selectMonth} month={3}/>
                                                            <h1>April</h1>
                                                        </Route>
                                                        <Route exact path="/teacher/pastLectures/month/May">
                                                            <MonthNrStudents selectMonth={this.selectMonth} month={4}/>
                                                            <h1>May</h1>
                                                        </Route>
                                                        <Route exact path="/teacher/pastLectures/month/June">
                                                            <MonthNrStudents selectMonth={this.selectMonth} month={5}/>
                                                            <h1>June</h1>
                                                        </Route>
                                                        <Route exact path="/teacher/pastLectures/month/July">
                                                            <MonthNrStudents selectMonth={this.selectMonth} month={6}/>
                                                            <h1>July</h1>
                                                        </Route>
                                                        <Route exact path="/teacher/pastLectures/month/August">
                                                            <MonthNrStudents selectMonth={this.selectMonth} month={7}/>
                                                            <h1>August</h1>
                                                        </Route>
                                                        <Route exact path="/teacher/pastLectures/month/September">
                                                            <MonthNrStudents selectMonth={this.selectMonth} month={8}/>
                                                            <h1>September</h1>
                                                        </Route>
                                                        <Route exact path="/teacher/pastLectures/month/October">
                                                            <MonthNrStudents selectMonth={this.selectMonth} month={9}/>
                                                            <h1>October</h1>
                                                        </Route>
                                                        <Route exact path="/teacher/pastLectures/month/November">
                                                            <MonthNrStudents selectMonth={this.selectMonth} month={10}/>
                                                            <h1>November</h1>
                                                        </Route>   
                                                        <Route exact path="/teacher/pastLectures/month/December">
                                                            <MonthNrStudents selectMonth={this.selectMonth} month={11}/>
                                                            <h1>December</h1>
                                                        </Route>                                             
                                                    </Switch>
                                                    {this.state.nrStudents !== undefined && <h1>There are {this.state.nrStudents.toFixed(1)} booked per lecture</h1>}
                                            </Col>
                                            <Col sm={1}/>
                                        </Route>
                                        <Route exact path="/teacher/pastLectures/graph">
                                            <Col sm={10}
                                                className="below-nav">
                                                <MonthChart lectures={this.state.teacherLecture} getAllPastLectures={this.getPastLecturesTeacher}/>
                                            </Col>
                                            <Col sm={1}/>
                                        </Route>
                                        <Route exact path="/teacher/pastLectures">
                                            <Redirect to="/teacher/pastLectures/all"/>
                                        </Route>
                                    </Switch>
                                </Row>
                            </Route>
                        </Route>
                        <Route exact path="/teacher">
                            <Row className="">
                                <Col sm={1}/>
                                <Col sm={10}
                                    className="below-nav">
                                    <h1>Next Lectures</h1>
                                    <LecturesTableTeacher lectures={this.state.teacherLecture} past={false} getLectures={this.getAllLecturesTeacher} job={(lecture_id) => this.getStudentList(lecture_id)} students={this.state.students} job2={(lecture_id) =>this.removeTeacherLecture(lecture_id)}/>
                                </Col>
                                <Col sm={1}/>
                            </Row>
                        </Route>
                    </Switch>
                </Route>
                <Route path="/booking_manager">
                    <Switch>
                    <Route exact path="/booking_manager">
                            <Row className="">
                                <Col sm={1} style={{display:"none"}} className="overflow">
                                        <PastLecturesFilter getAllPastLectures={this.getAllLecturesBookingManager}/>
                                    </Col>
                                <Col sm={2} className="overflow"></Col>
                                <Col sm={8}
                                    className="below-nav">
                                    <MonthChartBookingManager lectures={this.state.bookingManagerLecture} getAllPastLectures={this.getAllLecturesBookingManager}/>
                                </Col>
                                <Col sm={1}/>
                            </Row>
                    </Route>
                    <Route exact path="/booking_manager/contact_tracing/">
                            <Row className="">
                                <Col sm={4}/>
                                <Col sm={4}
                                    className="below-nav">
                                    <ContactTracing addIdContactTracing={this.addIdContactTracing}/>
                                </Col>
                                <Col sm={4}/>
                            </Row>
                    </Route>
                    </Switch>
                </Route>
                <Route path="/support_officer">
                    <Switch>
                    <Route exact path="/support_officer">
                            <Row className="">
                                <Col sm={2} className="below-nav"> <RadioButtonsCSV selectListForCSV={this.selectListForCSV}/></Col>
                                <Col sm={8}
                                    className="below-nav">
                                    <h1>Aggiungere la parte del support_officer</h1>
                                    {<UploadFiles uploadStudentCSV={this.uploadStudentCSV}/>}
                                </Col>
                                <Col sm={1}/>
                            </Row>
                        </Route>
                    </Switch>
                </Route>
                <Route>
                    <Redirect to='/login'/>
                </Route>
            </Switch>
        </AuthContext.Provider>);
    }
}

export default withRouter(App);

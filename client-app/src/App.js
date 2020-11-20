import LoginForm from './components/LoginForm';
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

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {noBookedLectures: [],bookedLectures: [],teacherLecture:[]};
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
    // Add a logout method
    logout = () => {
        console.log("logout");
        API.userLogout().then(() => {
            this.setState({authUser: undefined, authErr: undefined});
        });
        this.props.history.push("/login");
    }

    // Add a login method
    login = (username, password, type) => {
        API.userLogin(username, password, type).then((user) => {
            this.setState({authUser: user});
            if (user.id != -1) {
                if (user.teacher) {
                    this.props.history.push("/teacher");
                } else {
                    this.props.history.push("/student");
                }
            } else {
                this.setState({authErr: "Login Error"});
            }
        }).catch((errorObj) => {
            const err0 = errorObj.errors[0];
            this.setState({authErr: err0});
        });
    }

    getNoBookedLectures = () =>{
        console.log("here");
        API.getNoBookedLectures().then((lecture) =>{
            this.setState({noBookedLectures: lecture, authErr: undefined});
        }).catch((err) =>{
            console.log(err);
        })
    };

    getBookedLectures = () =>{
        console.log(this.state.authUser);
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

    getAllLecturesTeacher = () =>{
        API.getAllLectures().then((lecture) =>{
            this.setState({teacherLecture: lecture});
        })
    }

    render() {
        const value = {
            authUser: this.state.authUser,
            authErr: this.state.authErr,
            loginUser: this.login,
            logoutUser: this.logout
        }
        return (<AuthContext.Provider value={value}>

            <Header/>

            <Switch>
                <Route path="/login">
                    <Row className="vheight-100">
                        <Col sm={4}></Col>
                        <Col sm={4}
                            className="below-nav">
                            <LoginForm/>
                        </Col>
                    </Row>
                </Route>
                <Route path="/student">
                    <Row className="">
                        <Col sm={1}/>
                        <Col sm={8}
                            className="below-nav">
                            <h1>Booked Lectures</h1>
                            <LecturesTable lectures={this.state.bookedLectures} getLectures={this.getBookedLectures} remove={true}/>
                        </Col>
                        <Col sm={1}/>
                    </Row>
                    <Row className="vheight-0">
                        <Col sm={1}/>
                        <Col sm={8}
                            className="below-nav">
                            <h1>No Booked Lectures</h1>
                            <LecturesTable lectures={this.state.noBookedLectures} getLectures={this.getNoBookedLectures} remove={false} job={this.bookLecture}/>
                        </Col>
                        <Col sm={1}/>
                    </Row>
                </Route>

                <Route path="/teacher">
                    <Row className="">
                        <Col sm={1}/>
                        <Col sm={8}
                            className="below-nav">
                            <h1>Next Lectures</h1>
                            <LecturesTableTeacher lectures={this.state.teacherLecture} getLectures={this.getAllLecturesTeacher} job={() => console.log("farlo")}/>
                        </Col>
                        <Col sm={1}/>
                    </Row>
                </Route>
                <Route>
                    <Redirect to='/login'/>
                </Route>
            </Switch>
        </AuthContext.Provider>);
    }
}

export default withRouter(App);

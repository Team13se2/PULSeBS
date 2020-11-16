import './App.css';
import Header from './components/Header';
import LoginForm from './components/LoginForm';
import EventsList from './components/EventsList';
import TeacherPage from './components/TeacherPage';
import React from 'react';
import {BrowserRouter as Router, Switch, Route} from "react-router-dom";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import {Calendar, momentLocalizer} from "react-big-calendar";
import moment from "moment";
import {AuthContext} from './auth/AuthContext';

import "react-big-calendar/lib/css/react-big-calendar.css";
import API from './api/API';


const localizer = momentLocalizer(moment);

class App extends React.Component {

    state = {
        events: [
            {
                start: moment().toDate(),
                end: moment().add(1, "hour").toDate(),
                title: "Scemo chi legge"
            }, {
                start: moment().add(5, "days").toDate(),
                end: moment().add(5, "days").add(1, "hour").toDate(),
                title: "Scemo ch"
            }
        ]
    };

    // Add a login method
    login = (email, password, type) => {
        API.userLogin(email, password, type).then((user) => {
            this.setState({authUser: user})
        }).catch((errorObj) => {
            const err0 = errorObj.errors[0];
            this.setState({authErr: err0});
        });
    }


    // Add a logout method
    logout = () => {
        API.userLogout().then(() => {
            this.setState({authUser: null, authErr: null, tasks: null});
        });
    }

    getAllLectures = () =>{
        API.getAllLectures().then((lecture) =>{
            this.setState({lecture: lecture});
        })
    }

    getNumberStudentsAttending = (lecture_id) =>{
        API.getNumberStudentsAttending(lecture_id).then((nrStudents) =>{
            this.setState({nrStudents: nrStudents});
        })
    }

    componentDidMount() {
        //this.getNumberStudentsAttending(2);
        //this.getAllLectures();
        //this.login("teacher@gmail.com", "psw", "teacher");
        //this.logout();
        // check if the user is authenticated
        /*API.isAuthenticated().then(
        (user) => {
          this.setState({authUser: user});
        }
      ).catch((err) => { 
        this.setState({authErr: err.errorObj});
        this.props.history.push("/login");
      });*/
    };

    render() { // compose value prop as object with user object and logout method
        const value = {
            authUser: this.state.authUser,
            authErr: this.state.authErr,
            loginUser: this.login,
            logoutUser: this.logout
        }
        return (
            <AuthContext.Provider value={value}>
                <Router>
                    <div className="App">

                        <div className="container d-flex align-items-center flex-column">
                            <Switch>
                                <Route path="/login"
                                    exact={true}>
                                    <LoginForm login={
                                        this.login
                                    }/>
                                </Route>

                                <Route path="/student"
                                    exact={true}>

                                    <EventsList/>
                                </Route>

                                <Route path="/teacher"
                                    exact={true}>
                                    <TeacherPage/>


                                    <div id="pagine">
                                        <Row>
                                            <Col sm={12}>
                                                <h3 className="mb-5">Personal Calendar</h3>
                                            </Col>
                                        </Row>
                                        <Calendar localizer={localizer}
                                            defaultDate={
                                                new Date()
                                            }
                                            defaultView="month"
                                            events={
                                                this.state.events
                                            }
                                            style={
                                                {height: "60vh"}
                                            }/>
                                    </div>


                                </Route>
                            </Switch>
                        </div>
                    </div>
                </Router>
            </AuthContext.Provider>
        );
    }
}


export default App;

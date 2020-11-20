import './App.css';

import LoginForm from './components/LoginForm';
import StudentPage from './components/StudentPage';
import TeacherPage from './components/TeacherPage';
import React from 'react';
import Container from 'react-bootstrap/Container';
import {AuthContext} from './auth/AuthContext';
import "react-big-calendar/lib/css/react-big-calendar.css";
import API from './api/API';
import Cookies from 'js-cookie';
import {Redirect, Route, Link} from 'react-router-dom';
import {Switch} from 'react-router';
import {withRouter} from 'react-router-dom';


class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    // Add a login method
    login = (email, password, type) => {
        API.userLogin(email, password, type).then((user) => {
            console.log(user);
            if (user.id == -1) {
                this.setState({authUser: null})
            } else {
                console.log("here");
                const u = {
                    id: user.id,
                    email: user.email,
                    type: user.teacher === "teacher" ? "teacher" : "student"
                };
                this.setState({authUser: u});
                this.props.history.push("/"+type);
            }
        }).catch((errorObj) => {
            const err0 = errorObj.errors[0];
            this.setState({authErr: err0});
        });
    }


    // Add a logout method
    logout = () => {
        console.log("logout");
        API.userLogout().then(() => {
            this.setState({authUser: null, authErr: null});
        });
    }


    isAuthenticated = () => {
        const id = Cookies.get("id");
        const username = Cookies.get("username");
        const type = Cookies.get("type");
        if (id !== undefined && username !== undefined && type !== undefined) {
            const user = {
                id: id,
                username: username,
                type: type
            };
            this.setState({authUser: user});
        }
    }


    componentDidMount() {
        this.isAuthenticated();
        // this.getNumberStudentsAttending(2);
        // this.getAllLectures();
        // this.login("teacher@gmail.com", "psw", "teacher");
        // this.logout();
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
                    <Switch>
                        <Route path="/login"
                            exact={true}>
                            <LoginForm login={
                                    this.login
                                }
                                logout={
                                    this.logout
                                }/>
                        </Route>

                        <Route path="/student"
                            exact={true}>

                            <StudentPage logout={
                                this.logout
                            }/>
                        </Route>

                        <Route path="/teacher"
                            exact={true}
                            logout={
                                this.logout
                        }>
                            <TeacherPage/>
                        </Route>
                        <Route>
                            <Redirect to='/login'/>
                        </Route>
                    </Switch>
            </AuthContext.Provider>
        );
    }
}


export default withRouter(App);

import './App.css';

import LoginForm from './components/LoginForm';
import EventsList from './components/EventsList';
import TeacherPage from './components/TeacherPage';
import React from 'react';
import {BrowserRouter as Router, Switch, Route} from "react-router-dom";
import moment from "moment";
import {AuthContext} from './auth/AuthContext';
import "react-big-calendar/lib/css/react-big-calendar.css";
import API from './api/API';


class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            
        };
    }
    
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
        console.log(email+ password+ type);
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

    
    isAuthenticated = () =>{
        const id = Cookies.get("id");
        const username = Cookies.get("username");
        if(id !== undefined && username !== undefined){
            const user = {id:id,username:username};
            this.setState({authUser: user});
        }
    }
    

    componentDidMount() {
        this.isAuthenticated();
        //this.getNumberStudentsAttending(2);
       //this.getAllLectures();
       //this.login("teacher@gmail.com", "psw", "teacher");
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
                <Router>
                    <div className="App">

                        <div className="container d-flex align-items-center flex-column">
                            <Switch>
                                <Route path="/login"
                                    exact={true}>
                                    <LoginForm login={
                                        this.login
                                    }
                                    logout={ this.logout}/>
                                </Route>

                                <Route path="/student"
                                    exact={true}>

                                    <EventsList/>
                                </Route>

                                <Route path="/teacher"
                                    exact={true}
                                    logout={ this.logout}>
                                    <TeacherPage/>
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
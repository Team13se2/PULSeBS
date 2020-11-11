
import './App.css';
import Header from './components/Header';
import LoginForm from './components/LoginForm';
import EventsList from './components/EventsList';
import React from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

import { Calendar, momentLocalizer } from "react-big-calendar";
import moment from "moment";

import "react-big-calendar/lib/css/react-big-calendar.css";

const localizer = momentLocalizer(moment);

class App extends React.Component {
  state = {
    events: [
      {
        start: moment().toDate(),
        end: moment()
          .add(1, "hour")
          .toDate(),
        title: "Scemo chi legge"
      },
      {
        start: moment().add(5, "days").toDate(),
        end: moment().add(5, "days")
          .add(1, "hour")
          .toDate(),
        title: "Scemo ch"
      }
    ]
  };
  render() {
   

    return (
      <Router>
      <div className="App">
        
          <div className="container d-flex align-items-center flex-column">
            <Switch>
              <Route path="/login" exact={true}>
              <LoginForm />
              </Route>

              <Route path="/student" exact={true}>
                
              <EventsList />
              </Route>

              <Route path="/teacher" exact={true}>
              <div id="pagine" className="mt-5 hv-center">
              <div className="row col-14  d-flex justify-content-center">
                <span className="h2 font-weight-bold mb-5">Teaching Portal</span>
                
            </div>
                <Calendar
                localizer={localizer}
                defaultDate={new Date()}
                defaultView="month"
                events={this.state.events}
                style={{ height: "60vh" }}
              />
               <button  type="button" className="btn btn-secondary mt-5" >Logout</button>
              </div>
              
              </Route>
            </Switch>
         </div>
     </div>
    </Router>
    );
  }
}

/* <Calendar
          localizer={localizer}
          defaultDate={new Date()}
          defaultView="month"
          events={this.state.events}
          style={{ height: "100vh" }}
        />
        
        function App() {
  return (
    <Router>
    <div className="App">
      <Header/>
        <div className="container d-flex align-items-center flex-column">
          <Switch>
            <Route path="/login" exact={true}>
              
              <LoginForm />
            </Route>
          </Switch>
       </div>
   </div>
  </Router>









   <div className="App">

      
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
      <body>CIAO STRONZI</body>
      <body>CIAO STRONZI</body>
    </div>*/


export default App;

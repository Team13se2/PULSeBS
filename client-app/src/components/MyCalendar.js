import { Calendar, momentLocalizer  } from 'react-big-calendar' 
import React, {useState,useEffect} from 'react';
import 'react-big-calendar/lib/css/react-big-calendar.css';
import moment from 'moment'
//import events from './Event';

// Setup the localizer by providing the moment (or globalize) Object
// to the correct localizer.
const localizer = momentLocalizer(moment) // or globalizeLocalizer
const MyCalendar = (props) => {
  let {lectures,getBookedLectures} = props;
  useEffect(() => {
    getBookedLectures();
  }, []);
  return(
    <div style={{fontSize:'150%'}}>
      <Calendar style={{height: "800px"}}
        localizer={localizer}
        events={lectures}
        startAccessor="start"
        endAccessor="end"
        font-size="50px"
      />
    </div>
  );
};

export default MyCalendar;
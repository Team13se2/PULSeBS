import React, {useEffect} from 'react';
import {Redirect} from 'react-router-dom';
import {AuthContext} from '../auth/AuthContext'
import Table from "react-bootstrap/Table";

const LecturesTableNoBooked = (props) => {

    let {lectures, getLectures, remove, job,job2} = props;

    // same as componentDidMount()
    useEffect(() => {
      getLectures();
    }, []);


    return(
        <AuthContext.Consumer>
          {(context) => (
              <>
            <Table responsive striped bordered hover>
                  <thead>
                    <tr>
                      <th>Id</th>
                      <th>Start</th>
                      <th>End</th>
                      <th>Subject</th>
                      <th>Lecture Type</th>
                      <th>Available Seat</th>
                      <th>Total Seat</th>
                      <th>Room Name</th>
                      <th> </th> 
                    </tr>
                  </thead>

                  <tbody>
                  {lectures.length > 0 &&
                      lectures.map((lecture, index) => (
                        <tr key={index}>
                          <td>{lecture.id}</td>
                          <td>{lecture.startTime}</td>
                          <td>{lecture.endTime}</td>                          
                          <td>{lecture.subjectName}</td>
                          <td>{lecture.lectureType}</td>                       
                          <td>{lecture.availableSeat}</td>
                          <td>{lecture.totalSeat}</td>
                          <td>{lecture.roomName}</td>
                          <td>
                          {remove && <button type="button" className="btn btn-danger" onClick={() => job(lecture.id)}> 
                              Remove
                          </button> }
                          {!remove && lecture.availableSeat > 0 && <button type="button" className="btn btn-success" onClick={() => job(lecture.id)}> 
                                      Book
                          </button> }
                          {lecture.availableSeat === 0 && <button type="button" 
                          className="btn btn-warning" 
                          onClick={() => job(lecture.id)}>
                             Waiting List
                          </button>}
                          </td>
                        </tr>
                    ))}
                  </tbody>
                  
              </Table>
            </>
          )}
        </AuthContext.Consumer>
      );
}

export default LecturesTableNoBooked;

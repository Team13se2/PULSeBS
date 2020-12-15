import React, {useEffect} from 'react';
import {Redirect} from 'react-router-dom';
import {AuthContext} from '../auth/AuthContext'
import Table from "react-bootstrap/Table";

const CurrentLectures = (props) => {

    let {lectures, getLectures, remove, job} = props;

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
                      <th>Code</th>
                      <th>Course</th>
                      <th>Available Seats</th>
                      <th>Total Seats</th>
                      <th>№ Booked</th>
                      <th>№ Present</th>
                      <th>Start Time</th>
                      <th>End Time</th>
                      <th>Location</th>
                      <th> </th> 
                    </tr>
                  </thead>

                  <tbody>
                  {lectures.length > 0 &&
                      lectures.map((lecture, index) => (
                        <tr key={index}>
                          <td>{lecture.code}</td>
                          <td>{lecture.subjectName}</td>
                          <td>{lecture.availableSeat}</td>
                          <td>{lecture.totalSeat}</td>
                          <td>{lecture.nrStudentsBooked}</td>
                          <td>{lecture.nrStudentsPresent}</td>
                          <td>{lecture.startTime}</td>                          
                          <td>{lecture.endTime}</td>
                          <td>{lecture.roomName}</td>
                          <td>
                          {remove && <button type="button" className="btn btn-outline-danger" onClick={() => job(lecture.id)}> 
                              Remove
                          </button> }
                          {!remove && <button type="button" className="btn btn-outline-success" onClick={() => job(lecture.id)}> 
                                      Book
                          </button> }
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

export default CurrentLectures;

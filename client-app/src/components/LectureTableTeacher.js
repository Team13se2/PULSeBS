import React, {useEffect} from 'react';
import {Redirect} from 'react-router-dom';
import {AuthContext} from '../auth/AuthContext'
import Table from "react-bootstrap/Table";

const LecturesTableTeacher = (props) => {

    let {lectures, getLectures,job} = props;

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
                      <th>Course</th>
                      <th>Date</th>
                      <th>Teacher</th>
                      <th>Location</th>
                      <th>Students №</th>
                      <th> </th> 
                    </tr>
                  </thead>

                  <tbody>
                  {lectures.length > 0 &&
                      lectures.map((lecture, index) => (
                        <tr key={index}>
                          <td>{lecture.id}</td>
                          <td>{lecture.subjectName}</td>
                          <td>{lecture.startTime}</td>                          
                          <td>{lecture.surnameString}</td>
                          <td>{lecture.roomName}</td>
                          <td>{lecture.nrStudents}</td>
                          <td><button type="button" className="btn btn-outline-success" onClick={() => job(lecture.id)}> 
                                      Students
                          </button> 
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

export default LecturesTableTeacher;

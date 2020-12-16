import React, {useEffect} from 'react';
import {Redirect} from 'react-router-dom';
import {AuthContext} from '../auth/AuthContext'
import Table from "react-bootstrap/Table";
import Modal from 'react-modal';
import moment from 'moment';

const customStyles = {
  content : {
    top                   : '50%',
    left                  : '50%',
    right                 : 'auto',
    bottom                : 'auto',
    marginRight           : '-50%',
    transform             : 'translate(-50%, -50%)'
  }
};

const LecturesTableTeacher = (props) => {

    let {lectures,getLectures,job,students,job2,past} = props;
    // same as componentDidMount()
    useEffect(() => {
      getLectures();
    }, []);
    const [modalIsOpen,setIsOpen] = React.useState(false);
    const [lecture_id,setLecture_id] = React.useState(-1);
    function openModal(lecture_id) {
      setLecture_id(lecture_id);
      job(lecture_id)
      setIsOpen(true);
    }
   
    function closeModal(){
      setLecture_id(-1);
      setIsOpen(false);
    }

    function removeButton(lecture_id,startTime){
      const format1 = "YYYY-MM-DD HH:mm:ss";
      let date1 = new Date();
      let dateTime1 = moment(date1).format(format1);
      let date2 = new Date(startTime);
      let dateTime2 = moment(date2).format(format1);
      dateTime2 = moment(dateTime2,"YYYY-MM-DD HH:mm:ss");
      if(dateTime2.diff(dateTime1, 'minutes') > 60){
        return (<><button type="button"className="btn btn-outline-danger" onClick={() =>job2(lecture_id)}>Remove</button><br></br><br></br>
        <button type="button"className="btn btn-outline-primary" onClick={() =>job2(lecture_id)}>Move Online</button></>);
      }else{
        if(dateTime2.diff(dateTime1, 'minutes') > 30){
          return (<><button type="button" disabled className="btn btn-outline-danger" onClick={() =>job2(lecture_id)}>Remove</button><br></br><br></br>
          <button type="button"className="btn btn-outline-primary" onClick={() =>job2(lecture_id)}>Move Online</button></>);
        }else{
          return (<><button type="button" disabled className="btn btn-outline-danger" onClick={() =>job2(lecture_id)}>Remove</button><br></br><br></br>
          <button type="button" disabled className="btn btn-outline-primary" onClick={() =>job2(lecture_id)}>Move Online</button></>);
        }
      }
    }

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
                      {!past && <th> </th> }
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
                          {!past &&<td style={{display: 'flex', justifyContent: 'center'}}><button type="button" className="btn btn-outline-success" onClick={() =>openModal(lecture.id)}>Students</button><br></br>
                            {removeButton(lecture.id,lecture.startTime)}
                          </td> }
                        </tr>
                    ))}
                  </tbody>
                  
              </Table>
              <Modal isOpen={modalIsOpen} onRequestClose={closeModal} style={customStyles}>
                {students && students.length == 0 && <h1>There are no students enrolled </h1> }
                {students && students.length > 0 &&
                <Table responsive striped bordered hover>
                <thead>
                    <tr>
                      <th>Id</th>
                      <th>Name</th>
                      <th>Surname</th>
                      <th>Email</th> 
                    </tr>
                  </thead>
                  <tbody>
                {students && students.map((student,index) =>(
                  <tr key={index}>
                  <td>{student.id}</td>
                  <td>{student.name}</td>
                  <td>{student.surname}</td>
                  <td>{student.email}</td>
                  </tr>
                ))}        
                </tbody>
                </Table>
                }
              </Modal>
            </>
          )}
        </AuthContext.Consumer>
      );
}

export default LecturesTableTeacher;

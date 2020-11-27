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

    let {lectures, getLectures,job,students,job2} = props;
    // same as componentDidMount()
    useEffect(() => {
      getLectures();
    }, []);
    const [modalIsOpen,setIsOpen] = React.useState(false);
    function openModal(lecture_id) {
      job(lecture_id)
      setIsOpen(true);
    }
   
    function closeModal(){
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
        return (<button type="button"className="btn btn-outline-danger" onClick={() =>job2(lecture_id)}>Remove</button>);
      }else{
        console.log("here");
        return (<button type="button" disabled className="btn btn-outline-danger" onClick={() =>job2(lecture_id)}>Remove</button>);
      }
    }

    return(
        <AuthContext.Consumer>
          {(context) => (
              <>
            <Table responsive striped bordered hover>
                  <thead>
                    <tr>
                      <th>Id</th>
                      <th>Course</th>
                      <th>Start Time</th>
                      <th>End Time</th>
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
                          <td>{lecture.endTime}</td>         
                          <td>{lecture.surnameString}</td>
                          <td>{lecture.roomName}</td>
                          <td>{lecture.nrStudents}</td>
                          <td><button type="button" className="btn btn-outline-success" onClick={() =>openModal(lecture.id)}>Students</button>
                            {removeButton(lecture.id,lecture.startTime)}
                          </td>
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

import React, {useEffect} from 'react';
import {Redirect} from 'react-router-dom';
import {AuthContext} from '../auth/AuthContext'
import Table from "react-bootstrap/Table";
import Modal from 'react-modal';
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

const CurrentLectures = (props) => {

    let {lectures, getLectures, setPresence, job,students} = props;

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
                      <th>Present</th> 
                    </tr>
                  </thead>
                  <tbody>
                {students && students.map((student,index) =>(
                  <tr key={index}>
                  <td>{student.id}</td>
                  <td>{student.name}</td>
                  <td>{student.surname}</td>
                  <td>{student.email}</td>
                  <td><button type="button" className="btn btn-success" disabled={student.presence} onClick={()=>setPresence(student.id,lecture_id)}>Present 
                  </button>
                  </td>
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

export default CurrentLectures;
import React from "react";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import Table from "react-bootstrap/Table";
import { useState, useEffect } from "react";
import moment from "moment";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import {Redirect} from "react-router-dom";
import { Calendar, momentLocalizer } from "react-big-calendar";
import "react-big-calendar/lib/css/react-big-calendar.css";
const localizer = momentLocalizer(moment);




const TeacherPage = (props) => {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  /*state = {
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
                <Calendar
                localizer={localizer}
                defaultDate={new Date()}
                defaultView="month"
                events={this.state.events}
                style={{ height: "60vh" }}
              />*/
    

  return (
      <div>
      <Modal show={show} onHide={handleClose}>
      
      <Modal.Body>
          <Modal.Header closeButton>
        <Modal.Title>Student Booked for this Lecture</Modal.Title>
      </Modal.Header>
      <Table responsive striped bordered hover>
                <thead>
                  <tr>
                    <th>Name</th>
                    <th>Id</th>
                    <th>Email</th>
                  </tr>
                </thead>

                <tbody>
                    <tr>
                        <td>student.name}</td>
                        <td>student.id}</td>
                        <td>student.email}</td>
                    </tr>
                </tbody>
            </Table>
      <Modal.Footer>
        
        <Button variant="secondary" onClick={handleClose}>
          Close
        </Button>
      </Modal.Footer>
      </Modal.Body>
      
    </Modal>
      
    
        <div id="pagine" className="mt-5 hv-center">
        <div  id="list-container">
             <Row>
              <Col sm={12}>
              
              <div className="row col-14  d-flex justify-content-center">
                <span className="h2 font-weight-bold mb-5">Portal - Teacher section</span>
            </div>
            
                </Col>
            </Row>
              
            <Row>
              <Col sm={12}>
                <h3>Next Lectures</h3>
              </Col>
            </Row>
            <Table responsive striped bordered hover>
                <thead>
                  <tr>
                    <th>Course</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Location</th>
                    <th>Students №</th>
                    <th> </th>
                    
                  </tr>
                </thead>

                <tbody>
                  <tr>
                        
                        <td>lecture.course}</td>
                        <td>lecture.date}</td>
                        <td>lecture.time}</td>
                        <td>lecture.location}</td>
                        <td>lecture.studentno</td>
                      
                        <td>
                            <button type="button" className="btn btn-outline-info" onClick={handleShow}>
                                Students
                            </button>
                        </td>
                        

                      </tr>
                   
                </tbody>
                
            </Table>
            
            
            
            
           
            
          </div>
          
                <button  type="button" className="btn btn-secondary mt-5" >Logout</button>
            
          </div>

        </div>
         
    
  );
}

export default TeacherPage;
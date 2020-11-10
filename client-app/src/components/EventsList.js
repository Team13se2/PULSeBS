import React from "react";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import Table from "react-bootstrap/Table";
import { useState, useEffect } from "react";
import moment from "moment";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import {Redirect} from "react-router-dom";

const EventsList = (props) => {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  
    

  return (
      <div>

        

    <Modal show={show} onHide={handleClose}>
      
      <Modal.Body>
          <Modal.Header closeButton>
        <Modal.Title>You are deleting this booking</Modal.Title>
      </Modal.Header>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Confirm
        </Button>
        <Button variant="secondary" onClick={handleClose}>
          Exit
        </Button>
      </Modal.Footer>
      </Modal.Body>
      
    </Modal>
      
    
        <div id="pagine" className="mt-5 hv-center">
        <div  id="list-container">
             <Row>
              <Col sm={12}>
              
              <div className="row col-14  d-flex justify-content-center">
                <span className="h2 font-weight-bold mb-5">Teaching Portal</span>
            </div>
            
            
                
                </Col>
            </Row>
              
            <Row>
              <Col sm={12}>
                <h3>Booked Lessons</h3>
              </Col>
            </Row>
            <Table responsive striped bordered hover>
                <thead>
                  <tr>
                    <th>Course</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Teacher</th>
                    <th>Location</th>
                    <th> </th>
                    
                  </tr>
                </thead>

                <tbody>
                  <tr>
                        
                        <td>lesson.course}</td>
                        <td>lesson.date}</td>
                        <td>lesson.time}</td>
                        <td>lesson.teacher}</td>
                        <td>lesson.location}</td>
                      
                        <td>
                            <button type="button" className="btn btn-danger" onClick={handleShow}>
                                Unbook
                            </button>
                        </td>
                        

                      </tr>
                   
                </tbody>
                
            </Table>
            

            <Row>
              <Col sm={12}>
                <h3>Others</h3>
              </Col>
            </Row>
              <Table responsive striped bordered hover>
                <thead>
                  <tr>
                    <th>Course</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Teacher</th>
                    <th>Location</th>
                    <th> </th>
                    
                  </tr>
                </thead>

                <tbody>
                  <tr>
                        
                        <td>lesson.course}</td>
                        <td>lesson.date}</td>
                        <td>lesson.time}</td>
                        <td>lesson.teacher}</td>
                        <td>lesson.location}</td>
                        <td>
                            <button type="button" className="btn btn-success" data-toggle="modal" data-target="#bookModal">
                                Book
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

export default EventsList;
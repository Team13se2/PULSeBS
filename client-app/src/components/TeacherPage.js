import React from "react";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import Table from "react-bootstrap/Table";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import {Redirect} from "react-router-dom";
import { AuthContext } from "../auth/AuthContext";
import { Nav} from "react-bootstrap";
import { NavLink } from "react-router-dom";

import API from '../api/API';

class TeacherPage extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      id:[],
        lectures: [],
        show: false
    };
  }

  componentDidMount(){
    this.getAllLectures();
  }

  getAllLectures = () =>{
    API.getAllLectures().then((lecture) =>{
        this.setState({lectures: lecture});
    })
}

logout = () => {
  API.userLogout().then(() => {
      this.setState({authUser: null, authErr: null, tasks: null});
  });
}


showModal = (lectureIndex) => {
  this.setState({ show: true , lectureIndex: lectureIndex});
};

hideModal = () => {
  this.setState({ show: false });
};

render(){


  return (
    
      <AuthContext.Consumer>

      {(context) => (
        <>
          {context.authErr && <Redirect to="/login"></Redirect>}
          <Modal show={this.state.show} handleClose={this.hideModal} >
                      <Modal.Body>
                          <Modal.Header closeButton>
                        <Modal.Title>Students List</Modal.Title>
                      </Modal.Header> 
                      
                      {this.state.lectureIndex !== undefined && this.state.lectures[this.state.lectureIndex].nrStudents === 0 && <p> None booked this lecture yet. </p>}
                      {this.state.nrStudents > 0 &&
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
                                        <td>student.name</td>
                                        <td>student.id</td>
                                        <td>student.email</td>
                                    </tr>
                                </tbody>
                            </Table>}
                      <Modal.Footer>
                        
                        <Button variant="secondary" onClick={this.hideModal} >
                          Close
                        </Button>
                      </Modal.Footer>
                      </Modal.Body>
                  </Modal>
              
          <div id="pagine" className="mt-5 hv-center">
            <div id="list-container">
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

              {this.state.lectures.length === 0 && <h4>There aren't scheduled lectures</h4>}
              {this.state.lectures.length > 0 && 
                <Table responsive striped bordered hover>
                  <thead>
                    <tr>
                      <th>Id</th>
                      <th>Course</th>
                      <th>Date</th>
              {/*<th>Time</th>*/}

                      <th>Teacher</th>
                      <th>Location</th>
                      <th>Students №</th>
                      <th> </th>
                      
                    </tr>
                  </thead>
                  <tbody>
                    {this.state.lectures.length > 0 &&
                      this.state.lectures.map((lecture, index) => (
                        <tr key={index + 1}>
                          <td>{lecture.id}</td>
                          <td>{lecture.subjectName}</td>
                          {/*<td>{lecture.date}</td>*/}
                          <td>{lecture.startTime}</td>
                        
                          <td>{lecture.surnameString}</td>
                          <td>{lecture.roomName }</td>
                          
                          <td>{lecture.nrStudents}</td>
                          
                          <td>
                            <button  type="button" className="btn btn-outline-success mt-5" 
                                  onClick={() => this.showModal(index)}
                                  id={lecture.id}> Students
                            </button> 
                          </td>
                        </tr>
                      ))}
                  </tbody>
                </Table>
              }

              <Nav.Link as={NavLink} to="/login"  > 
                <button  type="button" className="btn btn-secondary mt-5" onClick={() => { context.logoutUser(); }}>Logout
                </button> 
              </Nav.Link>
            </div>
          </div>  
        </>
      )}
      
    </AuthContext.Consumer> 
   ); //chiudo return
  } //chiudo render
} //chiudo class

export default TeacherPage;
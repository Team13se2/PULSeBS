import React from "react";
import Col from "react-bootstrap/Col";
import Row from "react-bootstrap/Row";
import Table from "react-bootstrap/Table";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import {Redirect} from "react-router-dom";
import { AuthContext } from "../auth/AuthContext";
import API from '../api/API';
import { NavLink } from "react-router-dom";
import { Nav} from "react-bootstrap";

class StudentPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        studentId: [],
        bookedLectures:[],
        notBookedLectures: [],
        show: false
    };

  }
  componentDidMount(){
    console.log("here");
  }

  getAllLectures = () =>{
    API.getAllLectures().then((lecture) =>{
        this.setState({lectures: lecture});
    })
  }

  getBookedLectures = (studentId) =>{
   /* API.getBookedLectures(studentId).then((lecture) =>{
        this.setState({lectures: lecture});
    })*/
  }
  getNotBookedLectures = (studentId) =>{
    /*API.getBookedLectures(studentId).then((lecture) =>{
        this.setState({lectures: lecture});
    })*/
  }
  
  showModal = () => {
    this.setState({ show: true });
  };
  
  hideModal = () => {
    this.setState({ show: false });
  };
  
  render(){

    return (
      <AuthContext.Consumer>

      {(context) => (
        <>
          {context.authUser === null ? <Redirect to="/login"/> : ""}
          {context.authUser === undefined? <Redirect to="/login"/> : ""}
        <Modal show={this.state.show} handleClose={this.hideModal} >
     
          <Modal.Body>
              <Modal.Header closeButton>
            <Modal.Title>You are deleting this booking</Modal.Title>
          </Modal.Header>
          <Modal.Footer>
            <Button variant="secondary" onClick={this.hideModal}>
              Confirm
            </Button>
            <Button variant="secondary"onClick={this.hideModal} >
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
                <span className="h2 font-weight-bold mb-5">Portal - Student section</span>
              </div>
                </Col>
            </Row>
              
            <Row>
              <Col sm={12}>
                <h3>Booked Lectures</h3>
              </Col>
            </Row>

            {this.state.bookedLectures.length === 0 && <h4>There aren't booked lectures</h4>}
            {this.state.bookedLectures.length > 0 && 
              <Table responsive striped bordered hover>
                  <thead>
                    <tr>
                      <th>Course</th>
                      <th>Date</th>
                      <th>Teacher</th>
                      <th>Location</th>
                      <th> </th>
                    </tr>
                  </thead>

                  <tbody>
                    {this.state.bookedLectures.length > 0 &&
                      this.state.bookedLectures.map((lecture, index) => (
                        <tr>
                          <td>{lecture.subjectName}</td>
                          <td>{lecture.date}</td>                          
                          <td>{lecture.surnameString}</td>
                          <td>{lecture.roomName}</td>
                          <td>
                              <button type="button" className="btn btn-outline-danger" onClick={this.showModal}>
                                  Unbook
                              </button>
                          </td>
                        </tr>
                    ))}
                  </tbody>
                  
              </Table>
            }

            <Row>
              <Col sm={12}>
                <h3>Others</h3>
              </Col>
            </Row>

            {this.state.notBookedLectures.length === 0 && <h4>There aren't lectures to book</h4>}
            {this.state.notBookedLectures.length > 0 && 
              <Table responsive striped bordered hover>
                  <thead>
                    <tr>
                      <th>Id</th>
                      <th>Course</th>
                      <th>Date</th>
                      <th>Teacher</th>
                      <th>Location</th>
                      <th> </th>
                      
                    </tr>
                  </thead>

                  <tbody>
                  {this.state.notBookedLectures.length > 0 &&
                      this.state.notBookedLectures.map((lecture, index) => (
                        <tr>
                          <td>{lecture.id}</td>
                          <td>{lecture.subjectName}</td>
                          <td>{lecture.date}</td>                          
                          <td>{lecture.surnameString}</td>
                          <td>{lecture.roomName}</td>
                        
                          <td>
                              <button type="button" className="btn btn-outline-success" onClick={this.showModal}>
                                  Book a seat
                              </button>
                          </td>
                          

                        </tr>
                    ))}
                  </tbody>
                  
              </Table>
            }
           
           <Nav.Link as={NavLink} to="/" onClick={() => this.props.logout()}><Button>Logout</Button>
            </Nav.Link>
              
          </div>
        </div>

       
      </>
     
      )}
    </AuthContext.Consumer> 
   ); //chiudo return
  } //chiudo render
} //chiudo class

export default StudentPage;
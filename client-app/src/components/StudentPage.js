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
    this.getAllLectures();
    this.getBookedLectures();
  };

  getAllLectures = () =>{
    API.getAllLecturesStudent().then((lecture) =>{
        this.setState({lectures: lecture});
    })
  };

  getBookedLectures = () =>{
    API.getBookedLectures().then((lecture) =>{
        this.setState({bookedLectures: lecture});
    })
  };
  
  showModal = () => {
    this.setState({ show: true });
  };
  
  hideModal = () => {
    this.setState({ show: false });
  };

  bookLecture = (lecture_id) =>{
    API.bookLecture(lecture_id).then((response) =>{
      let l = this.state.lectures.filter((l =>l.id !== lecture_id));
      this.setState({lectures: l});
      this.getBookedLectures();
    })
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
                      <th>id</th>
                      <th>startTime</th>
                      <th>endTime</th>
                      <th>subjectName</th>
                      <th>lectureType</th>
                      <th>surnameString</th>
                      <th>availableSeat</th>
                      <th>totalSeat</th>
                      <th>roomName</th>
                      <th> </th>
                      
                    </tr>
                  </thead>

                  <tbody>
                  {this.state.bookedLectures.length > 0 &&
                      this.state.bookedLectures.map((lecture, index) => (
                        <tr>
                          <td>{lecture.id}</td>
                          <td>{lecture.startTime}</td>
                          <td>{lecture.endTime}</td>                          
                          <td>{lecture.subjectName}</td>
                          <td>{lecture.lectureType}</td>
                          <td>{lecture.surnameString}</td>                          
                          <td>{lecture.availableSeat}</td>
                          <td>{lecture.totalSeat}</td>
                          <td>{lecture.roomName}</td>
                          <td>
                              <button type="button" className="btn btn-outline-danger" onClick={this.showModal}>
                                  Remove
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

            {this.state.lectures === 0 && <h4>There aren't lectures to book</h4>}
            {this.state.lectures ? this.state.lectures.length > 0 && 
              <Table responsive striped bordered hover>
                  <thead>
                    <tr>
                      <th>id</th>
                      <th>startTime</th>
                      <th>endTime</th>
                      <th>subjectName</th>
                      <th>lectureType</th>
                      <th>surnameString</th>
                      <th>availableSeat</th>
                      <th>totalSeat</th>
                      <th>roomName</th>
                      <th> </th>
                      
                    </tr>
                  </thead>

                  <tbody>
                  {this.state.lectures.length > 0 &&
                      this.state.lectures.map((lecture, index) => (
                        <tr>
                          <td>{lecture.id}</td>
                          <td>{lecture.startTime}</td>
                          <td>{lecture.endTime}</td>                          
                          <td>{lecture.subjectName}</td>
                          <td>{lecture.lectureType}</td>
                          <td>{lecture.surnameString}</td>                          
                          <td>{lecture.availableSeat}</td>
                          <td>{lecture.totalSeat}</td>
                          <td>{lecture.roomName}</td>
                          <td>
                              <button type="button" className="btn btn-outline-success" onClick={() => this.bookLecture(lecture.id)}>
                                  Book a seat
                              </button>
                          </td>
                          

                        </tr>
                    ))}
                  </tbody>
                  
              </Table>
            : ""}
           
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
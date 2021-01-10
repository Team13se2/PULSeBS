import React, {useEffect} from 'react';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import API from '../api/API';
import DropdownButton from 'react-bootstrap/DropdownButton';
import Dropdown from 'react-bootstrap/Dropdown';
import { Input } from '@material-ui/core';
import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button"


const SupportOfficerSchedule = (props) => {

    let {} = props;
    const [courseId,setCourseId] = React.useState(undefined);
    const [inputCourse,setInputCourse] = React.useState("");
    const [schedule,setSchedule] = React.useState(undefined);
    const [selectedInputCourse,setSelectedInputCourse] = React.useState("");
    const [results,setResults] = React.useState("");
    const [test,setTest] = React.useState(undefined);
    const [startDay,setStartDay] = React.useState(undefined);
    const [unique,setUnique] = React.useState(undefined);
    const courses = [{id: 1, name:"Applicazioni web"},{id: 2, name:"Informatica"},{id: 3, name:"Analisi"}
    ]
    const days = ["Mon","Tue","Wed","Thu","Fri"];
    const lessons = [{startTime: "2021-12-02",endTime: "2021-12-02",room:"10",seats:"100",day:"mon"},
    {startTime: "2021-12-02",endTime: "2021-12-02",room:"10",seats:"150",day:"mon"}];
    // same as componentDidMount()
    useEffect(() => {
        getSchedule();
    }, []);

    function getSchedule(){
        API.getSchedule().then((e) =>{
            setSchedule(e);
            setUnique(removeDuplicates(e.map(elem => elem.coursename)));
        }).catch((err) =>{
            console.log(err);
        });
    }
    function removeDuplicates(data){
        return [...new Set(data)];
    }
    function selectLectures(courseName){
        setResults(schedule.filter(a => a.coursename == courseName));
    }
    function updateStartTime(value,index){
        let res = results;
        res[index].startTime = value;
        setResults(res);
    }
    function updateEndTime(value,index){
        let res = results;
        res[index].endTime = value;
        setResults(res);
    }
    function updateRoom(value,index){
        let res = results;
        res[index].room = value;
        setResults(res);
    }
    function updateSeats(value,index){
        let res = results;
        res[index].seats = value;
        setResults(res);
    }
    function updateDay(value,index){
        let res = results;
        res[index].day = value;
        setResults(res);
    }
    function clkUpdate(index){
        checkValues(index);
        if(checkValues(index)){
            console.log(index);
            API.modifySchedule(results[index].id,startDay,results[index].code,results[index].startTime,results[index].endTime,results[index].seats,results[index].room,results[index].day)
            .then((e) =>{
                alert("UPDATED")
            }).catch((err) =>{
                alert("ERROR");
            });
        }else{
            alert("Select all fields");
        }
    }

    function checkValues(index){
        if(new Date(startDay).getTime() > new Date().getTime()){
            if(results[index].startTime !== "" && results[index].startTime !== ""){
                if(results[index].room !== "" && results[index].seats > 0){
                    if(results[index].day !== ""){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    let duplicates = [];

    return(
        <Row className="">
            <Col sm={2} />
            <Col sm={8} className="below-nav">
                <div className="selectCourse">
                    <h2>Select a course</h2>
                    <Input type="text" placeholder="Course" value={inputCourse} onChange={e => {setInputCourse(e.target.value.toLowerCase()); setSelectedInputCourse("")}}></Input>
                    <div className="divForElements">
                        <DropdownButton id="dropdown-basic-button" title={selectedInputCourse === "" || inputCourse === "" ? "Courses" : selectedInputCourse}>
                            
                            {/*schedule && schedule.sort((a,b) => (a.name > b.name)).filter(course => course.coursename.toLowerCase().includes(inputCourse)).map(element => {
                                return (<Dropdown.Item onClick={() =>{ setSelectedInputCourse(element.coursename); setInputCourse(element.coursename); selectLectures(element.coursename)}}>{element.coursename}</Dropdown.Item>)
                                
                            })*/}
                            {unique && unique.sort((a,b) => (a > b)).filter(course => course.toLowerCase().includes(inputCourse)).map(element => {
                                return (<Dropdown.Item onClick={() =>{ setSelectedInputCourse(element); setInputCourse(element); selectLectures(element)}}>{element}</Dropdown.Item>)
                                
                            })}
                        </DropdownButton>
                        <Button variant="danger" onClick={() =>{setSelectedInputCourse(""); setInputCourse(""); setCourseId(""); setResults(""); getSchedule(); setStartDay("");
                    }}>Reset</Button>
                    </div>
                </div>
                <Row className="below-nav">
                <Table responsive striped bordered hover>
                    <thead>
                        <tr>
                            <th>Modification Start Date</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                            <th>Room</th>
                            <th>Seats</th>
                            <th>Day</th>
                            <th></th>
                        </tr>
                  </thead>
                        {results && results.map((lesson,i) => {
                            return (<tr>
                                <td><Input type="date" min={new Date().toJSON().split('T')[0]} onChange={(e) => {setStartDay(e.target.value);}}></Input></td>
                                <td><Input type="text" defaultValue={lesson.startTime} onChange={(e) => updateStartTime(e.target.value,i)}>{lesson.startTime}</Input></td>
                                <td><Input type="text" defaultValue={lesson.endTime}onChange={(e) => updateEndTime(e.target.value,i)}>{lesson.endTime}</Input></td>
                                <td><Input type="text" defaultValue={lesson.room}onChange={(e) => updateRoom(e.target.value,i)}>{lesson.room}</Input></td>
                                <td><Input type="number" defaultValue={lesson.seats}onChange={(e) => updateSeats(e.target.value,i)}>{lesson.seats}</Input></td>
                                <td><select >{days.map((day) => <option onClick={(e) => updateDay(day,i)} selected={lesson.day === day ? "selected" : ""} key={day}>{day}</option>)}</select></td>
                                <td><Button onClick={()=> clkUpdate(i)}>Update</Button></td>
                            </tr>)
                        })}
                  <tbody>
                  </tbody>
                  
              </Table>
                </Row>
            </Col>
        </Row>
      );
}

export default SupportOfficerSchedule;

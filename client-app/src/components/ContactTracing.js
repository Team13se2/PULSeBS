import { Button } from '@material-ui/core';
import TextField from '@material-ui/core/TextField';
import React, {useEffect} from 'react';
import { Row } from 'react-bootstrap';
import Col from 'react-bootstrap/Col';

const ContactTracing = (props) => {

    let {addIdContactTracing} = props;

    // same as componentDidMount()
    useEffect(() => {
      
    }, []);
    const [id,setId] = React.useState("");
    const [type,setType] = React.useState("");

    function addContact(){
        addIdContactTracing(id,type);
    }

    return (
        <><div style={{display: 'block', justifyContent: 'center'}} >
        <Row
            display="flex"
         justifyContent= "center"
         margin="20px">
        <Col sm={12} className="below-nav">
        <Row>
        <Col sm={4}>
        <TextField id="outlined-basic" type="text" label="ID" variant="outlined" onChange={(e) => setId(e.target.value)}/>
        </Col>
        <Col sm={8} className="contact-radio" >
        <div><input id="outlined-basic" type="radio" name="type" label="student" id="student" variant="outlined" onChange={(e) => setType("student")}/><label for="student">Student</label></div>
        <div><input id="outlined-basic" type="radio" name="type" label="teacher" id="teacher" variant="outlined" onChange={(e) => setType("teacher")}/><label for="teacher">Teacher</label></div>
        </Col>
        </Row>
        </Col>
        </Row> 
       
        <Row className="below-nav" style={{"margin-left":"45%"}}> 
        <Button
        variant="contained"
        color="primary"
        size="large"
        onClick={() => addContact()}
        disabled={type === "" || id === "" ? true : false}
        >
         Save
        </Button>
        </Row></div>
        </>
    );
}
export default ContactTracing;
import React, {useEffect} from 'react';
import {AuthContext} from '../auth/AuthContext'
import Col from 'react-bootstrap/Col';
import UploadFiles from './UploadFiles';
import RadioButtonsCSV from './RadioButtonsCSV';
import Row from 'react-bootstrap/Row';
import API from '../api/API';
import Loader from './Loader';

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

const SupportOfficerMainPage = (props) => {

    let {} = props;
    const [CSV,setCSV] = React.useState("students");
    const [response,setResponse] = React.useState("");
    const [loading,setLoading] = React.useState(false);
    // same as componentDidMount()
    useEffect(() => {
    }, []);

    function uploadStudentCSV(file){
        setLoading(true);
        let type = "";
        switch (CSV) {
            case "students":
                type = "addStudents";
                break;
            case "teachers":
                type = "addTeachers";
                break;
            case "courses":
                type = "addCourses";
                break;
            case "enrollment":
                type = "enrollStudents";
                break;
            default:
                break;
        }

        API.uploadStudentCSV(file,type).then((e) =>{
            setResponse("Upload successful");
            setLoading(false);
        }).catch((err) =>{
            throw err;
        })
    }

    function selectListForCSV(value){
        setCSV(value);
    }

    return(
        <AuthContext.Consumer>
          {(context) => (
            <>
            {loading && <><Col sm={2} className="below-nav" /><Loader /></>}
            {!loading && <><Col sm={2} className="below-nav"> <RadioButtonsCSV selectListForCSV={selectListForCSV} CSV={CSV}/></Col>
                <Col sm={8}
                    className="below-nav">
                    <h1>Upload a file</h1>
                    {<UploadFiles uploadStudentCSV={uploadStudentCSV}/>}
                    <Row>
                        {response !== "" && <h3 style={{color: "red"}}>{response}</h3>}
                    </Row>
                </Col>
            <Col sm={1}/></>}

            </>
          )}
        </AuthContext.Consumer>
      );
}

export default SupportOfficerMainPage;

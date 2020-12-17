import React, {useEffect} from 'react';
import {AuthContext} from '../auth/AuthContext'
import Col from 'react-bootstrap/Col';
import UploadFiles from './UploadFiles';
import RadioButtonsCSV from './RadioButtonsCSV';
import Row from 'react-bootstrap/Row';
import API from '../api/API';
import Loader from './Loader';
import SelectInput from '@material-ui/core/Select/SelectInput';
import { render } from '@testing-library/react';

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

class SupportOfficerMainPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {csv: "students",response:"",loading:false};
    }

    uploadStudentCSV = (file) => {
        let type = "";
        this.setState({loading:true});
        switch (this.state.csv) {
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
                case "lectures":
                type = "addLectures"

            default:
                break;
        }
        
        API.uploadStudentCSV(file,type).then((e) =>{
            this.setState({loading:false,response:"Upload successful"});
        }).catch((err) =>{
            throw err;
        })
        
    }

    selectListForCSV = (value) =>{
        this.setState({csv:value});
    }
    render(){   
        return(
            <AuthContext.Consumer>
            {(context) => (
                <>
                <Col sm={1}/>
                {this.state.loading && <><Col sm={3} className="below-nav" /><Loader /></>}
                {!this.state.loading && <><Col sm={3} className="below-nav"> <RadioButtonsCSV selectListForCSV={(e) =>{this.selectListForCSV(e)}} CSV={this.state.csv}/></Col>
                <Col sm={1}/>
                <Col sm={6}
                    className="below-nav">
                    <h2 style={{display: 'flex', justifyContent: 'center'}}><b> Upload a file</b></h2>
                    {<UploadFiles uploadStudentCSV={this.uploadStudentCSV}/>}
                    <Row>
                        {this.state.response !== "" && <h3 style={{color: "red"}}>{this.state.response}</h3>}
                    </Row>
                </Col>
                <Col sm={1}/></>}

                </>
            )}
            </AuthContext.Consumer>
        );
    }
}

export default SupportOfficerMainPage;

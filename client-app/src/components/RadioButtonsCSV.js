import React, {useEffect} from 'react';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form'
const RadioButtonsCSV = (props) => {
    let {selectListForCSV} = props;
    // same as componentDidMount()
    useEffect(() => {
    }, []);

    function onChangeValue(event) {
        console.log(event.target.value);
        selectListForCSV(event.target.value);
    }
   return(
       <>
        <p>Please select the list which has to be updated:</p>
        <div onChange={onChangeValue}>
            <div><label><input type="radio" id="students" name="list" value="students" />Students</label></div>
            <div><label><input type="radio" id="teachers" name="list" value="teachers" />Teachers</label></div>
            <div><label><input type="radio" id="courses" name="list" value="courses" />Courses</label></div>
            <div><label><input type="radio" id="lectures" name="list" value="lectures" />Lectures</label></div>
            <div><label><input type="radio" id="enrollment" name="list" value="enrollment" />Enrollment</label></div>
        </div>
      </>
   );
}
export default RadioButtonsCSV;
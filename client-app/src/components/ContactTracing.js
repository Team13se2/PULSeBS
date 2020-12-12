import { Button } from '@material-ui/core';
import TextField from '@material-ui/core/TextField';
import React, {useEffect} from 'react';
import { Row } from 'react-bootstrap';

const ContactTracing = (props) => {

    let {addIdContactTracing} = props;

    // same as componentDidMount()
    useEffect(() => {
      
    }, []);
    const [id,setId] = React.useState("");

    function addContact(){
        addIdContactTracing(id);
    }

    return (
        <>
        <Row>
            <TextField id="outlined-basic" type="number" label="ID" variant="outlined" onChange={(e) => setId(e.target.value)}/>
        </Row>
        <Row>
        <Button
        variant="contained"
        color="primary"
        size="large"
        onClick={() => addContact()}
        >
        Save
        </Button>
        </Row>
        </>
    );
}
export default ContactTracing;
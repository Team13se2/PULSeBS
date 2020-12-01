import React, {useEffect} from 'react';
import {Redirect} from 'react-router-dom';
import {AuthContext} from '../auth/AuthContext'
import Table from "react-bootstrap/Table";
import Modal from 'react-modal';
import moment from 'moment';


const MonthNrStudents = (props) => {

    let {selectMonth,month} = props;
    // same as componentDidMount()
    useEffect(() => {
        selectMonth(month);
    }, [month]);

    return(<> </>
      );
}

export default MonthNrStudents;

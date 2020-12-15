import React, {useEffect} from 'react';
import {Redirect} from 'react-router-dom';
import {AuthContext} from '../auth/AuthContext'
import Table from "react-bootstrap/Table";

const ListCovid = (props) => {

    let {listCOVID} = props;

    // same as componentDidMount()
    useEffect(() => {
    }, []);


    return(
        <AuthContext.Consumer>
          {(context) => (
              <>
           <Table responsive striped bordered hover>
                <thead>
                    <tr>
                      <th>Id</th>
                      <th>Name</th>
                      <th>Surname</th>
                      <th>Email</th> 
                    </tr>
                  </thead>
                  <tbody>
                {listCOVID && listCOVID.map((student,index) =>(
                  <tr key={index}>
                  <td>{student.id}</td>
                  <td>{student.name}</td>
                  <td>{student.surname}</td>
                  <td>{student.email}</td>
                  </tr>
                ))}        
                </tbody>
                </Table>
            </>
          )}
        </AuthContext.Consumer>
      );
}

export default ListCovid;

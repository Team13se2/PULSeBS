import React, {useEffect} from 'react';
import {AuthContext} from '../auth/AuthContext'
import { ProSidebar, Menu, MenuItem, SubMenu } from 'react-pro-sidebar';
import {Link} from 'react-router-dom';
import 'react-pro-sidebar/dist/css/styles.css';

const PastLecturesFilter = (props) => {

    let {getAllPastLectures} = props;
    // same as componentDidMount()
    useEffect(() => {
        getAllPastLectures();
    }, []);

    return(
        <AuthContext.Consumer>
          {(context) => (
              <>
            <ProSidebar>
                <Menu iconShape="square">
                    <SubMenu title="Single Lecture" >
                    <MenuItem>Component 1</MenuItem>
                    <MenuItem>Component 2</MenuItem>
                    </SubMenu>
                    <MenuItem >All Lectures <Link to="/teacher/pastLectures/all"/></MenuItem>
                    <MenuItem >Week <Link to="/teacher/pastLectures/week"/></MenuItem>
                    <MenuItem >Month<Link to="/teacher/pastLectures/month"/></MenuItem>
                    <MenuItem >Graph</MenuItem>
                </Menu>
                
            </ProSidebar>
            </>
          )}
        </AuthContext.Consumer>
      );
}

export default PastLecturesFilter;

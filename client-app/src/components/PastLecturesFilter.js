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
            <ProSidebar >
                <Menu iconShape="square">
                    <MenuItem >All Lectures <Link to="/teacher/pastLectures/all"/></MenuItem>
                    <MenuItem >Week <Link to="/teacher/pastLectures/week"/></MenuItem>
                    <SubMenu title="Month" >
                    <MenuItem>January<Link to="/teacher/pastLectures/month/January"/></MenuItem>
                    <MenuItem>February<Link to="/teacher/pastLectures/month/February"/></MenuItem>
                    <MenuItem>March<Link to="/teacher/pastLectures/month/March"/></MenuItem>
                    <MenuItem>April<Link to="/teacher/pastLectures/month/April"/></MenuItem>
                    <MenuItem>May<Link to="/teacher/pastLectures/month/May"/></MenuItem>
                    <MenuItem>June<Link to="/teacher/pastLectures/month/June"/></MenuItem>
                    <MenuItem>July<Link to="/teacher/pastLectures/month/July"/></MenuItem>
                    <MenuItem>August<Link to="/teacher/pastLectures/month/August"/></MenuItem>
                    <MenuItem>September<Link to="/teacher/pastLectures/month/September"/></MenuItem>
                    <MenuItem>October<Link to="/teacher/pastLectures/month/October"/></MenuItem>
                    <MenuItem>November<Link to="/teacher/pastLectures/month/November"/></MenuItem>
                    <MenuItem>December<Link to="/teacher/pastLectures/month/December"/></MenuItem>
                    </SubMenu>
                    <MenuItem >Graph<Link to="/teacher/pastLectures/graph"/></MenuItem>
                </Menu>
                
            </ProSidebar>
            </>
          )}
        </AuthContext.Consumer>
      );
}

export default PastLecturesFilter;

import React from 'react';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from'react-bootstrap/Button';
import Alert from'react-bootstrap/Alert';
import {Redirect} from 'react-router-dom';
import {AuthContext} from '../auth/AuthContext';
class LoginForm extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {username: 's900000@students.politu.it', password: 'psw', submitted: false};
    }

    componentDidMount(){
    }

    onChangeUsername = (event) => {
        this.setState({username : event.target.value});
    }; 
    
    onChangePassword = (event) => {
        this.setState({password : event.target.value});
    };
    
    handleSubmit = (event, onLogin) => {
        event.preventDefault();
        onLogin(this.state.username,this.state.password);
        this.setState({submitted : true});
    }

    render() {
        if (this.state.submitted)
            return <Redirect to='/' />;
        return(
            <AuthContext.Consumer>
                {(context) => (
                <>
                
                {context.authUser && context.authUser.type === "teacher" && <Redirect to='/teacher'/>}
                {context.authUser && context.authUser.type === "student" && <Redirect to='/student'/>}
                {context.authUser && context.authUser.type === "booking_manager" && <Redirect to='/booking_manager'/>}
                {context.authUser && context.authUser.type === "support_officer" && <Redirect to='/support_officer'/>}
                <Container fluid>
                    <Row>
                        <Col>
                            <h2 className="ui teal image header" style={{display: 'flex', justifyContent: 'center'}}>
                                <div className="content">
                                     
                                    <p><strong   >
                                        &nbsp;&nbsp;
                                        Log-in to your account
                                    </strong>
                                        </p>
                                </div>
                            </h2>

                            <Form method="POST" onSubmit={(event) => this.handleSubmit(event, context.loginUser)} >
                                <Form.Group controlId="username">
                                    <Form.Label style={{"font-size":"50%"}}>E-mail</Form.Label>
                                    <Form.Control style={{"font-size":"50%"}} type="email" name="email" placeholder="E-mail" value = {this.state.username} onChange={(ev) => this.onChangeUsername(ev)} required autoFocus/>
                                </Form.Group>

                                <Form.Group controlId="password">
                                    <Form.Label style={{"font-size":"50%"}}>Password</Form.Label>
                                    <Form.Control style={{"font-size":"50%"}} type="password" name="password" placeholder="Password" value = {this.state.password} onChange={(ev) => this.onChangePassword(ev)} required/>
                                </Form.Group>
                                
                            <div style={{display: 'flex', justifyContent: 'center'}}>
                                <Button variant="primary" type="submit">Login</Button>
                            </div>
                                

                            </Form>

                            {context.authErr && 
                            <Alert variant= "danger">
                                {context.authErr}
                            </Alert>
                            }
                    
                        </Col>
                    </Row>
                </Container>
                </>
                )}
            </AuthContext.Consumer>

        );
    }


}
LoginForm.contextType = AuthContext;
export default LoginForm;
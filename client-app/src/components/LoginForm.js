import React from 'react';
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Alert from "react-bootstrap/Alert";
import { Redirect } from "react-router-dom";
import { AuthContext } from "../auth/AuthContext";

class LoginForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = { email: "", psw: "", submitted: false };
      }
    
      onChangeEmail = (event) => {
        this.setState({ email: event.target.value });
      };
    
      onChangePsw = (event) => {
        this.setState({ psw: event.target.value });
      };
      onChangeType = (event) => {
        this.setState({ type: event.target.value });
      };
      handleSubmit = (event, onLogin) => {
        event.preventDefault();
        onLogin(this.state.email, this.state.psw, "teacher");
        this.setState({ submitted: true });
      };
    render(){
      
        return(
            <AuthContext.Consumer>
              
            {(context) => (
            <>
            {context.authErr && <Redirect to="/login"></Redirect>}
            {context.authUser && <Redirect to="/teacher"/>}
            
            <Container fluid>
                <Row>
                    <div id="loginnnni" className="card col-12 col-lg-4 login-card mt-5 hv-center">
                        <div className="row d-flex justify-content-center text-gray">
                            <span className="h2 font-weight-bold mb-5">Teaching Portal</span>
                        </div>

                        <Form id="form-login"
                         method="POST"
                         onSubmit={(event) =>
                            this.handleSubmit(event, context.loginUser)
                          }
                        >
                            <Form.Group controlId="email">
                                <Form.Label >E-mail</Form.Label>
                                <Form.Control
                                type="email"
                                name="email"
                                placeholder="E-mail"
                                value={this.state.email}
                                onChange={(ev) => this.onChangeEmail(ev)}
                                required
                                autoFocus
                                />
                            </Form.Group>

                            <Form.Group controlId="psw">
                                <Form.Label>Password</Form.Label>
                                <Form.Control
                                type="password"
                                name="psw"
                                placeholder="Password"
                                value={this.state.psw}
                                onChange={(ev) => this.onChangePsw(ev)}
                                required
                                />
                            </Form.Group>

                            <Form.Group controlId="psw"
                            value={this.state.type}
                            onChange={(ev) => this.onChangeType(ev)}>
                               
                            
                            </Form.Group>
                            <Button className="btn btn-secondary" type="submit">
                                Login
                            </Button>
                        </Form>
                    </div>
                </Row>
            </Container>
        
            </>
        )}
      </AuthContext.Consumer>
        );
    }
}
export default LoginForm;
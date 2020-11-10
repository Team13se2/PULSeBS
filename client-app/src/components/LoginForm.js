import React from 'react';

function LoginForm(props) {
  return(
        <div id="loginnnni" className="card col-12 col-lg-4 login-card mt-5 hv-center">
             <div className="row d-flex justify-content-center text-gray">
             <span className="h2 font-weight-bold mb-5">Teaching Portal</span>
            </div>
            <form>
                <div className="form-group text-left">
                <label htmlFor="exampleInputEmail1">Email address</label>
                <input type="email" 
                       className="form-control" 
                       id="email" 
                       aria-describedby="emailHelp" 
                       placeholder="Enter email"
                />
                
                </div>
                <div id="navbo" className="form-group text-left">
                    <label htmlFor="exampleInputPassword1">Password</label>
                    <input type="password" 
                        className="form-control" 
                        id="password" 
                        placeholder="Password"
                    />
                </div>
                
                <button 
                    type="submit" 
                    className="btn btn-secondary"
                >
                    Login
                </button>
            </form>
        </div>
    )
}
export default LoginForm;
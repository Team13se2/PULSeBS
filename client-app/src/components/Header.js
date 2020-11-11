import React from 'react';
function Header() {
    return(
        <nav id="navbo" class="navbar navbar-dark bg-info">
            <div className="row col-1 d-flex justify-content-center text-white">
                
                
            </div>   
            <div className="row col-10 d-flex justify-content-center text-white">
                <span className="h3">Teaching Portal</span>
            </div>

             
            <div className="row col-1 d-flex justify-content-center text-white">
                
                <button type="button" className="btn btn-info" >
                    Logout
                </button>
            </div>           
        </nav>
    )
}
export default Header;
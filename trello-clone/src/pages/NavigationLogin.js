import {Link} from "react-router-dom"
import React from 'react';

/* functionality for stagnant registration */
function NavigationLogin(){

    return(
        <nav>
            <ul>
                <li><Link to="/login">Login</Link></li>
                <li><Link to="/register">Register</Link></li>
            </ul>
        </nav>


    );
}
export default NavigationLogin;
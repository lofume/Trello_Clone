import {Link} from "react-router-dom"
import React from 'react';

/* functionality for stagnant registration */
function NavigationWorkspace(){

    return(
     <nav>
         <ul>
            <li><Link to="/login">Logout</Link></li>
            <li><Link to="/workspaces">Workspaces</Link></li>
            <li><Link to="/create-workspace">Create Workspaces</Link></li>
        </ul>
     </nav>

       
    );
}
export default NavigationWorkspace;
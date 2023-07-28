import React from 'react';
import { useHistory } from 'react-router-dom';
import AddMemberToWorkspaceForm from "../components/forms/AddMemberToWorkspaceForm";

function AddMemberToWorkspace() {

    const history = useHistory();

    function addMemberHandler(workspaceId, email) {
        fetch(`http://localhost:9001/workspace/addUser/${workspaceId}?email=${email}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            response => response.json()
        ).then( function(data) {
            console.log(data)
            if (data === true){
                alert("User Successfully Added to Workspace!")
                history.replace('/workspaces/'+localStorage.getItem('userId'))
            } else {
                alert("User does not exist, or user is already part of this workspace.")
            }
        })
    }

    return(
        <AddMemberToWorkspaceForm addUserToWorkspace = {addMemberHandler}/>
    );
}
export default AddMemberToWorkspace;
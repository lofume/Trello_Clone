import React from 'react';
import { useHistory } from 'react-router-dom';
import CreateWorkspaceForm from '../components/forms/CreateWorkspaceForm';

function CreateWorkspace() {

    const history = useHistory();
    const userId = localStorage.getItem('user');
    const userEmail = localStorage.getItem('userEmail');

    function addToWorkspace(workspaceId, email) {
        // console.log(workspaceId)
        fetch(`http://localhost:9001/workspace/addUser/${workspaceId.workspaceId}?email=${email}`, {
            method: 'POST',
            // body: JSON.stringify(workspace),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            response => response.json()

        ).then( function(data) {
            console.log(data)
            if (data === true){
                alert("Successfully added user to workspace.")
            } else {
                alert("User already exists in this workspace.")
            }
            return;
        }).then(
            () => history.replace(`/workspaces/${userId}`)
        );
    }

    function createWorkspaceHandler(workspace) {
        fetch('http://localhost:9001/workspace/save', {
            method: 'POST',
            body: JSON.stringify(workspace),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            response => response.json()

        ).then(

            (response) => {addToWorkspace(response, userEmail)}
        )
    }



    return (
        <CreateWorkspaceForm createWorkspace={createWorkspaceHandler} />
    );
};

export default CreateWorkspace;
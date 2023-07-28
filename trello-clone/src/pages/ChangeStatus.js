import React from 'react';
import { useHistory } from 'react-router-dom';
import ChangeStatusForm from "../components/forms/ChangeStatusForm";

// const taskId = localStorage.getItem('assignTaskTo')

function ChangeStatus() {

    const history = useHistory();

    function changeStatusHandler(taskId, status) {
        fetch(`http://localhost:9001/task/changeStatus/${taskId}?status=${status}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            response => response.json()
        ).then( function(data) {
            console.log(data)
            if (data === true){
                alert("Task Status Successfully Changed!")
                history.replace('/tasks/'+localStorage.getItem('current_boardId'))
            } else {
                alert("Something went wrong, try again later.")
            }
        })
    }

    return(
        <ChangeStatusForm changeStatus = {changeStatusHandler}/>
    );
}
export default ChangeStatus;
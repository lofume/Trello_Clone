import React from 'react';
import { useHistory } from 'react-router-dom';
import CreateTaskForm from "../components/forms/CreateTaskForm";


function CreateTask() {

    const history = useHistory();
    const boardId = localStorage.getItem('current_boardId')

    function addTaskToBoard(task, boardId) {
        const taskId = task.taskId;
        console.log(taskId)

        fetch(`http://localhost:9001/board/addTask/${boardId}?taskId=${taskId}`, {
            method: 'PUT',
            // body: JSON.stringify(board),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            response => response.json()
        ).then( function(data) {
            console.log(data)
            if (data === true){
                alert("Successfully added task to board.")
            } else {
                alert("Task already exists in this board.")
            }
        }).then(
            () => history.replace('/tasks/'+boardId)
        )
    }

    function createTaskHandler(task) {
        fetch(`http://localhost:9001/task/save`, {
            method: 'POST',
            body: JSON.stringify(task),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            response => response.json()
        ).then( (response) => {addTaskToBoard(response, boardId)});
    }

    return (
        <CreateTaskForm createTask={createTaskHandler} />
    );
}

export default CreateTask;
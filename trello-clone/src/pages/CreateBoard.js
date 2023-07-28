import React from 'react';
import { useHistory } from 'react-router-dom';
import CreateBoardForm from '../components/forms/CreateBoardForm';

function CreateBoard() {

    const history = useHistory();
    const workspaceId = localStorage.getItem('current_workspaceId')

    function addBoardToWorkspace(board, workspace_id) {
        const boardId = board.boardId;
        console.log(boardId)

        fetch(`http://localhost:9001/workspace/addBoard/${workspace_id}?boardId=${boardId}`, {
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
                alert("Successfully added board to workspace.")
            } else {
                alert("Board already exists in this workspace.")
            }
        }).then(
                () => history.replace('/boards/'+workspaceId)
        )
    }

    function createBoardHandler(board) {
        fetch(`http://localhost:9001/board/save`, {
            method: 'POST',
            body: JSON.stringify(board),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            response => response.json()
        ).then( (response) => {addBoardToWorkspace(response, workspaceId)});
    }

    return (
        <CreateBoardForm createBoard={createBoardHandler} />
    );
};

export default CreateBoard;
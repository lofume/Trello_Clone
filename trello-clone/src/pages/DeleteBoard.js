import React from 'react';
import { useHistory } from 'react-router-dom';
import DeleteBoardForm from "../components/forms/DeleteBoardForm";

const workspaceId = localStorage.getItem('current_workspaceId')

function DeleteBoard() {

    const history = useHistory();

    function deleteBoardFromDB(boardId) {
        // console.log(board)

        fetch(`http://localhost:9001/board/delete/${boardId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            function() {
                alert("Board Successfully Deleted!")
            }
        ).then(
            () => history.replace('/boards/'+workspaceId)
        )
    }

    function removeBoardHandler(boardId) {
    fetch(`http://localhost:9001/workspace/removeBoard/${workspaceId}?boardId=${boardId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(
        response => response.json()
    ).then(
        (response) => {deleteBoardFromDB(boardId)}
    );
}

    return(
        <DeleteBoardForm removeBoard = {removeBoardHandler}/>
    );
}
export default DeleteBoard;
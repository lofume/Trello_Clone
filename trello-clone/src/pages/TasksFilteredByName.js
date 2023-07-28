import React, { useEffect, useState } from 'react';
import {Typography} from "@mui/material";
import ViewTasksFilteredByName from "../components/ViewTasksFilteredByName";

function TasksFilteredByName() {
    // const {id, date} = useParams();
    const [tasksTODOData,  setTasksTODOData ] = useState([]);
    const [tasksDOINGData, setTasksDOINGData] = useState([]);
    const [tasksDONEData,  setTasksDONEData ] = useState([]);
    const boardId = localStorage.getItem('current_boardId')
    const search = localStorage.getItem('searchFilter')

    function getAllTODOTasks() {
        fetch(`http://localhost:9001/board/getFilteredName/${boardId}?filter=${search}&status=To-Do`)
            .then(response => response.json())
            .then(tasks => {
                setTasksTODOData(tasks);
            });
    };

    function getAllDOINGTasks() {
        fetch(`http://localhost:9001/board/getFilteredName/${boardId}?filter=${search}&status=Doing`)
            .then(response => response.json())
            .then(tasks => {
                setTasksDOINGData(tasks);
            });
    };

    function getAllDONETasks() {
        fetch(`http://localhost:9001/board/getFilteredName/${boardId}?filter=${search}&status=Done`)
            .then(response => response.json())
            .then(tasks => {
                setTasksDONEData(tasks);
            });
    };

    useEffect(function () {
        getAllTODOTasks();
        getAllDOINGTasks();
        getAllDONETasks();
    }, []);


    return (
        <section>

            <div>
                <Typography variant="h3" component="h3">To-Do</Typography>
                <ViewTasksFilteredByName tasks={tasksTODOData} />
            </div>
            <div>
                <Typography variant="h3" component="h3">Doing</Typography>
                <ViewTasksFilteredByName tasks={tasksDOINGData} />
            </div>
            <div>
                <Typography variant="h3" component="h3">Done</Typography>
                <ViewTasksFilteredByName tasks={tasksDONEData} />
            </div>
        </section>
    );
};

export default TasksFilteredByName;
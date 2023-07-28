import React, { useEffect, useState } from 'react';
import {Typography} from "@mui/material";
import ViewTasksFilteredByDate from "../components/ViewTasksFilteredByDate";
import {useParams} from "react-router-dom";

function TasksFilteredByDate() {
    const [tasksTODOData,  setTasksTODOData ] = useState([]);
    const [tasksDOINGData, setTasksDOINGData] = useState([]);
    const [tasksDONEData,  setTasksDONEData ] = useState([]);
    const boardId = localStorage.getItem('current_boardId')
    const dateType = localStorage.getItem('dateType')

    function getAllTODOTasks() {
        fetch(`http://localhost:9001/board/getFilteredDate/${boardId}?when=${dateType}&status=To-Do`)
            .then(response => response.json())
            .then(tasks => {
                setTasksTODOData(tasks);
            });
    };

    function getAllDOINGTasks() {
        fetch(`http://localhost:9001/board/getFilteredDate/${boardId}?when=${dateType}&status=Doing`)
            .then(response => response.json())
            .then(tasks => {
                setTasksDOINGData(tasks);
            });
    };

    function getAllDONETasks() {
        fetch(`http://localhost:9001/board/getFilteredDate/${boardId}?when=${dateType}&status=Done`)
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
                <ViewTasksFilteredByDate tasks={tasksTODOData} />
            </div>
            <div>
                <Typography variant="h3" component="h3">Doing</Typography>
                <ViewTasksFilteredByDate tasks={tasksDOINGData} />
            </div>
            <div>
                <Typography variant="h3" component="h3">Done</Typography>
                <ViewTasksFilteredByDate tasks={tasksDONEData} />
            </div>
        </section>
    );
};

export default TasksFilteredByDate;
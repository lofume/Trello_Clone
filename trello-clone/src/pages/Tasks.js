import React, { useEffect, useState } from 'react';
import ViewTasks from "../components/ViewTasks";
import {Typography} from "@mui/material";

function Tasks() {
    const [tasksTODOData,  setTasksTODOData ] = useState([]);
    const [tasksDOINGData, setTasksDOINGData] = useState([]);
    const [tasksDONEData,  setTasksDONEData ] = useState([]);
    const boardId = localStorage.getItem('current_boardId')

    function getAllTODOTasks() {
        fetch(`http://localhost:9001/board/getTasks/${boardId}?status=To-Do`)
            .then(response => response.json())
            .then(tasks => {
                setTasksTODOData(tasks);
            });
    };

    function getAllDOINGTasks() {
        fetch(`http://localhost:9001/board/getTasks/${boardId}?status=Doing`)
            .then(response => response.json())
            .then(tasks => {
                setTasksDOINGData(tasks);
            });
    };

    function getAllDONETasks() {
        fetch(`http://localhost:9001/board/getTasks/${boardId}?status=Done`)
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
                <ViewTasks tasks={tasksTODOData} />
            </div>
            <div>
                <Typography variant="h3" component="h3">Doing</Typography>
                <ViewTasks tasks={tasksDOINGData} />
            </div>
            <div>
                <Typography variant="h3" component="h3">Done</Typography>
                <ViewTasks tasks={tasksDONEData} />
            </div>
        </section>
    );
};

export default Tasks;
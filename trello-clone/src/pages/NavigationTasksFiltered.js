import {Link, useHistory} from "react-router-dom"
import React, {useRef, useState} from 'react';
import Select from "react-select";
import {Button, TextField} from "@mui/material";

/* functionality for stagnant registration */
function NavigationTasksFiltered(){

    const filterRef = useRef();
    const [dateType, setDateType] = useState({ label: "Today", value: 1 });
    const boardId = localStorage.getItem('current_boardId')
    const history = useHistory();
    const dateTypes = [
        { label: "Overdue",   value: 0 },
        { label: "Today",     value: 1 },
        { label: "This Week", value: 2 },
    ];

    function filterDates(e) {
        e.preventDefault();

        localStorage.setItem('dateType', dateType.value);

        console.log(dateType.value);
        console.log(boardId);

        history.replace(`/filteredDate/${boardId}/${dateType.label}`)
        history.go(0)
    }

    function unFilter(e) {
        e.preventDefault();

        console.log(boardId);

        history.replace(`/tasks/${boardId}`)
        history.go(0)
    }

    function filterName(e) {
        e.preventDefault();
        const search = filterRef.current.value;
        localStorage.setItem('searchFilter', filterRef.current.value);

        history.replace(`/filteredName/${boardId}/${search}`)
        history.go(0)
    }

    return(
        <nav>

            <ul>
                <li><Link to="/login">Logout</Link></li>
                <li><Link to="/tasks">Tasks</Link></li>
                <li><Link to="/create-task">Create Task</Link></li>
                <li><Link to="/boards">Boards</Link></li>
                <li><Link to="/workspaces">Workspaces</Link></li>
            </ul>
            <form onSubmit={filterDates}>
                <Select
                    options={dateTypes}
                    placeholder={dateType.label}
                    onChange={ (value) => setDateType(value)}
                />
                <Button type='submit' variant='contained' color='primary' sx={{ margin: '4px'}}>
                    Filter Date
                </Button>
            </form>
            <form onSubmit={filterName}>
                <TextField
                    id='filter'
                    placeholder='Search'
                    variant='outlined'
                    required
                    fullWidth
                    inputRef={filterRef} />
                <Button type='submit' variant='contained' color='primary' sx={{ margin: '4px'}}>
                    Search Tasks
                </Button>
            </form>
            <form onSubmit={unFilter}>
                <Button type='submit' variant='contained' color='primary' sx={{ margin: '4px'}}>
                    Unfilter Results
                </Button>
            </form>


        </nav>


    );
}
export default NavigationTasksFiltered;
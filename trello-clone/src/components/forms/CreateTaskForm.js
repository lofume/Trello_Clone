import { Grid, Card, Button, TextField, Typography } from '@mui/material';
import React, {useRef, useState} from 'react';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";


function CreateTaskForm(props) {
    const taskTitleRef = useRef();
    const taskDescRef = useRef();
    const [dueDate, setDueDate] = useState(new Date());


    function createTask(e) {
        e.preventDefault();
        const taskTitle = taskTitleRef.current.value;
        const taskDesc = taskDescRef.current.value;

        const task = {
            taskTitle: taskTitle,
            taskDesc: taskDesc,
            dueDate: dueDate,
            status: "To-Do",
            assigneeId: null
        };

        console.log(dueDate);

        props.createTask(task);
    }

    return (
        <section style={{ marginTop: '70px' }}>
            <Card elevation={6}>
                <Grid container
                      direction="column"
                      justifyContent="center"
                      alignItems="center"
                      padding={10}

                >
                    <Typography variant='h2'>Create New Task</Typography>
                    <br></br>
                    <form onSubmit={createTask}>
                        <TextField
                            id='taskTitle'
                            placeholder='Task Name'
                            variant='outlined'
                            required
                            fullWidth
                            inputRef={taskTitleRef} />
                        <TextField
                            id='taskDesc'
                            placeholder='Task Description'
                            variant='outlined'
                            multiline
                            rows={4}
                            required
                            fullWidth
                            margin='dense'
                            inputRef={taskDescRef} />
                        <div>
                            <DatePicker
                                style={{width: 200}}
                                placeholder='Task Due Date'
                                selected={dueDate}
                                onChange={(date) => setDueDate(date)}
                            />
                        </div>
                        <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px'}}>
                            Create Task
                        </Button>
                    </form>
                </Grid>
            </Card>
        </section>
    );
}

export default CreateTaskForm;
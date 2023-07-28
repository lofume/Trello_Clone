import { Grid, Card, Button, TextField, Typography } from '@mui/material';
import React, { useRef } from 'react';
import {Link} from "react-router-dom";

function AssignMemberToTaskForm(props) {
    const emailRef = useRef();
    const taskId = localStorage.getItem('assignTaskTo')

    function assignTaskToUser(e) {
        e.preventDefault();
        const email = emailRef.current.value;

        console.log(email);
        console.log(taskId);

        props.assignMemberToTask(taskId, email);
    };

    return (
        <section style={{ marginTop: '70px' }}>
            <Card elevation={6}>
                <Grid container
                      direction="column"
                      justifyContent="center"
                      alignItems="center"
                      padding={10}
                >
                    <Typography variant='h2'>Assign Task To User</Typography>
                    <br></br>
                    <form onSubmit={assignTaskToUser}>
                        <TextField
                            id='email'
                            placeholder='Email'
                            variant='outlined'
                            required
                            fullWidth
                            inputRef={emailRef} />
                        <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px'}}>
                            Assign User To Task
                        </Button>
                        <Link to={'/tasks/'+localStorage.getItem('current_boardId')}>
                            <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px'}}>
                                Back to Tasks
                            </Button>
                        </Link>
                    </form>
                </Grid>
            </Card>
        </section>
    );
};

export default AssignMemberToTaskForm;
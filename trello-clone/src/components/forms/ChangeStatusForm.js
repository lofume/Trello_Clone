import {Grid, Card, Button, Typography} from '@mui/material';
import React, { useState } from 'react';
import Select from 'react-select';
import {Link} from "react-router-dom";


function ChangeStatusForm(props) {
    const [status, setStatus] = useState({ label: "To-Do", value: "To-Do" });
    const taskId = localStorage.getItem('changeStatusOf')
    const statuses = [
        { label: "To-Do", value: "To-Do" },
        { label: "Doing", value: "Doing" },
        { label: "Done", value: "Done" },
    ];


    function changeStatus(e) {
        e.preventDefault();
        // const status = statusRef.current.valueOf();

        console.log(status.value);
        // console.log(e.target.);
        console.log(taskId);

        props.changeStatus(taskId, status.value);
    };

    return (
        <section style={{ marginTop: '30px' }}>
            <Card elevation={6}>
                <Grid container
                      direction="column"
                      justifyContent="center"
                      alignItems="center"
                      padding={20}
                >
                    <Typography variant='h2'>Change Status</Typography>
                    <br></br>
                    <form onSubmit={changeStatus}>
                        <Select
                            options={statuses}
                            placeholder={status.value}
                            onChange={ (value) => setStatus(value)}
                        />
                        <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px'}}>
                            Update Status
                        </Button>
                        <br></br>
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

export default ChangeStatusForm;
import React from 'react';
import { Grid, Card, CardContent, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';



function ViewTasks(props) {

    return (
        <section style={{ marginTop: '25px' }}>
        {/*<Typography variant='h2' component='h2' align='center' paddingBottom={2}>Tasks</Typography>*/}
            <Card elevation={6}>
                <Grid container
                      direction="row"
                      justifyContent="center"
                      spacing={{ xs: 1, md: 2 }}
                      column={{ xs: 3, sm: 7, md: 10 }}
                      alignItems="center"
                      paddingBottom={5}
                      paddingTop={5}
                >

                    {props.tasks.map((task) => {
                        return (
                            <Grid item xs={8} sm={9} md={2.5} lg={2.75} key={task.taskId}>
                                <Card elevation={5}>
                                    <CardContent>
                                        <Typography component='h4' variant='h4'>
                                            {task.taskTitle}
                                        </Typography>
                                        <Typography component='h6' variant='h6'>
                                            {task.taskDesc}
                                        </Typography>
                                        <br/>
                                        <Typography component='p' variant='p'>
                                            Due Date - {task.dueDate}
                                        </Typography>
                                        <Typography component='p' variant='p'>
                                            Assigned - {task.assigneeId}
                                        </Typography>
                                        <Typography component='p' variant='p'>
                                            Status - {task.status}
                                        </Typography>
                                        <Link to={'/assign-task'}>
                                            <Button variant='contained' sx={{ marginTop: '16px' }} onClick={() => localStorage.setItem('assignTaskTo', task.taskId)}>
                                                Assign User
                                            </Button>
                                        </Link>
                                        <Link to={'/change-status'}>
                                            <Button variant='contained' sx={{ marginTop: '16px' }} onClick={() => localStorage.setItem('changeStatusOf', task.taskId)}>
                                                Change Status
                                            </Button>
                                        </Link>
                                    </CardContent>
                                </Card>
                            </Grid>
                        );
                    })}
                </Grid>
            </Card>
        </section>
    );
};

export default ViewTasks;
import React from 'react';
import { Grid, Card, CardContent, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';



function ViewTasksFilteredByDate(props) {

    return (
        <section style={{ marginTop: '16px', marginBottom: '20px', marginLeft: '20px', marginRight: '20px' }}>
            <Card elevation={6}>
                <Grid container
                      direction="row"
                      justifyContent="left"
                      alignItems="center"
                      paddingBottom={5}
                      paddingTop={5}
                >
                    {/*<Typography variant='h2' component='h2'>Tasks</Typography>*/}
                    {props.tasks.map((task) => {
                        return (
                            <Grid marginLeft={2} marginRight={2} item xs={12} sm={12} md={4} lg={3.5} key={task.taskId}>
                                <Card elevation={6}>
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

export default ViewTasksFilteredByDate;
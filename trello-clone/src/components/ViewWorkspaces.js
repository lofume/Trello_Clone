import React from 'react';
import { Grid, Card, CardContent, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';

function ViewWorkspaces(props) {
    return (
        <section style={{ marginTop: '64px' }}>
         <Typography variant='h2' component='h2' align='center' paddingBottom={2}>Workspaces</Typography>
            <Card elevation={6}>
                <Grid container 
                    direction="row"
                    justifyContent="center"
                    spacing={{ xs: 1, md: 2 }}
                    column={{ xs: 3, sm: 6, md: 10 }}
                    alignItems="center"
                    paddingBottom={55}
                    paddingTop={5}
                >
                <br></br>
                    {props.workspaces.map((workspaces) => {
                        return (
                            <Grid item xs={8} sm={9} md={2.5} lg={2.75} key={workspaces.workspaceId}>
                                <Card elevation={5}>
                                <br></br>
                                    <CardContent>
                                        <Typography component='h4' variant='h4'>
                                            {workspaces.workspaceTitle}
                                        </Typography>
                                        <Typography component='p' variant='p'>
                                            {workspaces.workspaceDesc}
                                        </Typography>
                                        <Link to={'/boards/'+workspaces.workspaceId}>
                                            <Button variant='contained' sx={{ marginTop: '16px' }} onClick={() => localStorage.setItem("current_workspaceId", workspaces.workspaceId)}>
                                                View Boards
                                            </Button>
                                        </Link>
                                        <br></br>
                                        <Link to={'/addUser-workspace'}>
                                            <Button variant='contained' sx={{ marginTop: '16px' }} onClick={() => localStorage.setItem("current_workspaceId", workspaces.workspaceId)}>
                                                Add User
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

export default ViewWorkspaces;
import { Grid, Card, Button, TextField, Typography } from '@mui/material';
import React, { useRef } from 'react';
import {Link} from "react-router-dom";

function AddMemberToWorkspaceForm(props) {
    const emailRef = useRef();
    const workspaceId = localStorage.getItem('current_workspaceId')

    function addUserToWorkspace(e) {
        e.preventDefault();
        const email = emailRef.current.value;

        props.addUserToWorkspace(workspaceId, email);
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
                    <Typography variant='h2'>Add User To Workspace</Typography>
                    <br></br>
                    <form onSubmit={addUserToWorkspace}>
                        <TextField
                            id='email'
                            placeholder='Email'
                            variant='outlined'
                            required
                            fullWidth
                            inputRef={emailRef} />
                        <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px'}}>
                            Add User
                        </Button>
                        <Link to={'/workspaces/'+localStorage.getItem('userId')}>
                            <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px'}}>
                                Back to Workspaces
                            </Button>
                        </Link>
                    </form>
                </Grid>
            </Card>
        </section>
    );
};

export default AddMemberToWorkspaceForm;
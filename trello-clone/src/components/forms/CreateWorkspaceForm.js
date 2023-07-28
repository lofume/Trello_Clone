import { Grid, Card, Button, TextField, Typography } from '@mui/material';
import React, { useRef } from 'react';

function CreateWorkspaceForm(props) {
    const workspaceNameRef = useRef();
    const workspaceDespRef = useRef();

    function createWorkspace(e) {
        e.preventDefault();
        const workspaceName = workspaceNameRef.current.value;
        const workspaceDesp = workspaceDespRef.current.value;

        const workspace = {
            workspaceTitle: workspaceName,
            workspaceDesc: workspaceDesp
        };

        props.createWorkspace(workspace);
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
                    <Typography variant='h2'>Create New Workspace</Typography>
                    <br></br>
                    <form onSubmit={createWorkspace}>
                        <TextField
                            id='workspaceName'
                            placeholder='Workspace Name'
                            variant='outlined'
                            required
                            fullWidth
                            inputRef={workspaceNameRef} />
                        <TextField
                            id='workspaceDesp'
                            placeholder='Workspace Description'
                            variant='outlined'
                            multiline
                            rows={4}
                            required
                            fullWidth
                            margin='dense'
                            inputRef={workspaceDespRef} />

                        <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px'}}>
                            Create workspace
                        </Button>
                    </form>
                </Grid>
            </Card>
        </section>
    );
};

export default CreateWorkspaceForm;
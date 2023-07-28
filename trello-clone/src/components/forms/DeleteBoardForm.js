import {Grid, Card, Button, Typography} from '@mui/material';
import React from 'react';
import { Link } from 'react-router-dom';

function DeleteBoardForm(props) {

    function deleteBoard(e) {
        e.preventDefault();

        props.removeBoard(localStorage.getItem('boardIdToDelete'));
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
                    <Typography variant='h2'>Delete Board</Typography>
                    <br></br>
                    <form onSubmit={deleteBoard}>
                        <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px'}}>
                            Confirm Delete
                        </Button>
                    </form>
                    <Link to={'/boards/'+localStorage.getItem('current_workspaceId')}>
                        <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px'}}>
                            Do Not Delete
                        </Button>
                    </Link>


                </Grid>
            </Card>
        </section>
    );
};

export default DeleteBoardForm;
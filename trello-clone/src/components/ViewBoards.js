import React from 'react';
import { Grid, Card, CardContent, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';



function ViewBoards(props) {

    return (
        <section style={{ marginTop: '25px' }}>
        <Typography variant='h2' component='h2' align='center' paddingBottom={2}>Boards</Typography>
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
                    {props.boards.map((board) => {
                        return (
                            <Grid item xs={8} sm={9} md={2.5} lg={2.75} key={board.boardId}>
                                <Card elevation={5}>
                                    <CardContent>
                                        <Typography component='h4' variant='h4'>
                                            {board.boardTitle}
                                        </Typography>
                                        <Typography component='p' variant='p'>
                                            {board.boardDesc}
                                        </Typography>
                                        <Link to={'/tasks/'+board.boardId}>
                                            <Button variant='contained' sx={{ marginTop: '16px' }} onClick={() => localStorage.setItem('current_boardId', board.boardId)}>
                                                View Tasks
                                            </Button>
                                        </Link>

                                        <Link to={'/delete-board'}>
                                            <Button variant='contained' sx={{ marginTop: '16px' }} onClick={() => localStorage.setItem('boardIdToDelete', board.boardId)}>
                                                Delete Board
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

export default ViewBoards;
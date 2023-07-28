import { Grid, Card, Button, TextField, Typography } from '@mui/material';
import React, { useRef } from 'react';

function CreateBoardForm(props) {
    const boardNameRef = useRef();
    const boardDespRef = useRef();

    function createBoard(e) {
        e.preventDefault();
        const boardName = boardNameRef.current.value;
        const boardDesp = boardDespRef.current.value;

        const board = {
            boardTitle: boardName,
            boardDesc: boardDesp
        };

        props.createBoard(board);
    };

    return (
        <section style={{ marginTop: '70px' }}>
            <Card elevation={6}>
                <Grid container 
                    direction="column" 
                    justifyContent="center"
                    alignItems="center"
                    padding={10}
                    fullWidth

                >
                    <Typography variant='h2'>Create New Board</Typography>
                    <br></br>
                    <form onSubmit={createBoard}>
                        <TextField
                            id='boardName'
                            placeholder='Board Name'
                            variant='outlined'
                            required
                            fullWidth
                            inputRef={boardNameRef} />
                        <TextField
                            id='boardDesp'
                            placeholder='Board Description'
                            variant='outlined'
                            multiline
                            rows={4}
                            required
                            fullWidth
                            margin='dense'
                            inputRef={boardDespRef} />

                        <Button type='submit' variant='contained' color='primary' sx={{ marginTop: '16px'}}>
                            Create Board
                        </Button>
                    </form>
                </Grid>
            </Card>
        </section>
    );
};

export default CreateBoardForm;
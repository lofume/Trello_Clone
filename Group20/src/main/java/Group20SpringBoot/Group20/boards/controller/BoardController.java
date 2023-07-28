package Group20SpringBoot.Group20.boards.controller;

import Group20SpringBoot.Group20.boards.entity.BoardModel;
import Group20SpringBoot.Group20.boards.service.IBoardService;
import Group20SpringBoot.Group20.task.entity.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/board")
public class BoardController {
    private final IBoardService boardService;

    @Autowired
    public BoardController(IBoardService boardService){
        this.boardService = boardService;
    }

    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:3000")
    public BoardModel createBoard(@RequestBody BoardModel boardModel)
    {
        return boardService.createBoard(boardModel);
    }

    @GetMapping("/get/{boardId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public BoardModel findBoardByID(@PathVariable int boardId){
        return boardService.findBoardByID(boardId);
    }

    @GetMapping("/getTasks/{boardId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<TaskModel> getTasks(@PathVariable int boardId, @RequestParam String status)
    {
        return boardService.getTasks(boardId, status);
    }

    @GetMapping("/getFilteredDate/{boardId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<TaskModel> getFilteredDate(@PathVariable int boardId, @RequestParam int when, @RequestParam String status)
    {
        return boardService.getDateFiltered(boardId, status, when);
    }

    @GetMapping("/getFilteredName/{boardId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<TaskModel> getFilteredSearch(@PathVariable int boardId, @RequestParam String filter, @RequestParam String status)
    {
        return boardService.getNameFiltered(boardId, status, filter);
    }


    @PutMapping("/addTask/{boardId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean addTask(@PathVariable int boardId, @RequestParam int taskId)
    {
        return boardService.addTaskToBoard(boardId, taskId);
    }

    @DeleteMapping("/delete/{boardId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void deleteTask(@PathVariable int boardId)
    {
        boardService.deleteBoard(boardId);
    }

}

package Group20SpringBoot.Group20.workspaces.controller;

import Group20SpringBoot.Group20.boards.entity.BoardModel;
import Group20SpringBoot.Group20.workspaces.entity.WorkspaceModel;
import Group20SpringBoot.Group20.workspaces.service.IWorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/workspace")
public class WorkspaceController {

    @Autowired
    IWorkspaceService workspaceService;

    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:3000")
    public WorkspaceModel addWorkspace(@RequestBody WorkspaceModel workspaceModel) {
        return workspaceService.createWorkspace(workspaceModel);
    }

    @PutMapping("/addBoard/{workspaceId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean addBoard(@PathVariable int workspaceId, @RequestParam int boardId) {
        return workspaceService.addBoard(workspaceId, boardId);
    }

    @GetMapping("/getBoards/{workspaceId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<BoardModel> getBoards(@PathVariable int workspaceId) {
        return workspaceService.getBoardsOfWorkspace(workspaceId);
    }

    @DeleteMapping("/removeBoard/{workspaceId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public int removeBoard(@PathVariable int workspaceId, @RequestParam int boardId) {
        return workspaceService.removeBoard(workspaceId, boardId);
    }

    //sanjay
    @PostMapping("/addUser/{workspaceId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean addUserToWorkspace(@PathVariable int workspaceId, @RequestParam String email){
        return workspaceService.addUserToWorkspace(workspaceId, email);
    }

//    @PutMapping ("/deleteUser/{userId}")
//    @CrossOrigin(origins = "http://localhost:3000")
//    public void deleteUserFromWorkspace(@PathVariable int workspaceId, @RequestParam int userId){
//        workspaceService.deleteUserFromWorkspace(workspaceId, userId);
//    }


}

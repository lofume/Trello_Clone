package Group20SpringBoot.Group20.task.controller;

import Group20SpringBoot.Group20.task.entity.TaskModel;
import Group20SpringBoot.Group20.task.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;
    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:3000")
    public TaskModel createBoard(@RequestBody TaskModel taskModel)
    {
        return taskService.createTask(taskModel);
    }

    @GetMapping("/get/{taskId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public TaskModel findBoardByID(@PathVariable int taskId){
        return taskService.findTaskByID(taskId);
    }

    @PostMapping("/assign/{taskId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean assignTask(@PathVariable int taskId, @RequestParam String email, @RequestParam int workspaceId){
        return taskService.assignTask(taskId, email, workspaceId);
    }

    @PostMapping("/changeStatus/{taskId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean changeStatus(@PathVariable int taskId, @RequestParam String status){
        return taskService.changeStatus(taskId, status);
    }

}

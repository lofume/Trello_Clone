package Group20SpringBoot.Group20.task.service;

import Group20SpringBoot.Group20.task.entity.TaskModel;

public interface ITaskService {

    TaskModel createTask(TaskModel taskModel);

    TaskModel findTaskByID(int taskId);

    boolean assignTask(int taskId, String email, int workspaceId);

    boolean changeStatus(int taskId, String status);

}

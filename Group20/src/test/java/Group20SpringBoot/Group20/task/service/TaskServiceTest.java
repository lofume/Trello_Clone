package Group20SpringBoot.Group20.task.service;

import Group20SpringBoot.Group20.task.entity.TaskModel;
import Group20SpringBoot.Group20.task.repository.TaskRepository;
import Group20SpringBoot.Group20.user.entity.UserModel;
import Group20SpringBoot.Group20.user.service.UserService;
import Group20SpringBoot.Group20.workspaces.entity.WorkspaceModel;
import Group20SpringBoot.Group20.workspaces.service.WorkspaceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    @Autowired
    private TaskRepository taskRepository;

    @Mock
    UserService userService;

    @Mock
    WorkspaceService workspaceService;

    @InjectMocks
    TaskService taskService;

    @Test
    void createTask() {

        TaskModel taskModel = Mockito.mock(TaskModel.class);
        Mockito.when(taskRepository.save(taskModel)).thenReturn(taskModel);

        TaskModel task = taskService.createTask(taskModel);
        assertNotNull(task);


    }

    @Test
    void findTaskByID() {

        TaskModel taskModel = Mockito.mock(TaskModel.class);
        Mockito.when(taskRepository.findById(taskModel.getTaskId())).thenReturn(Optional.of(taskModel));

        TaskModel task = taskService.findTaskByID(taskModel.getTaskId());
        assertNotNull(task);


    }

    @Test
    void findTaskByID_taskModelNotPresent() {

        TaskModel taskModel = Mockito.mock(TaskModel.class);
        Mockito.when(taskRepository.findById(taskModel.getTaskId())).thenReturn(Optional.empty());

        TaskModel task = taskService.findTaskByID(taskModel.getTaskId());
        assertNull(task);


    }

    @Test
    void assignTask() {

        WorkspaceModel workspaceModel = new WorkspaceModel();
        workspaceModel.setWorkspaceTitle("Test");
        workspaceModel.setWorkspaceDesc("Testing the Tests");

        List<WorkspaceModel> workspaceModels = new ArrayList<>();
        workspaceModels.add(workspaceModel);

        UserModel user = new UserModel();
        user.setFirstName("Dani");
        user.setLastName("Rojas");
        user.setEmailId("daniaka@yahoo.com");
        user.setPassword("Password");
        user.setSecretKey("SecretKey");
        user.setWorkspaces(workspaceModels);

        TaskModel taskModel = Mockito.mock(TaskModel.class);
        Mockito.when(taskRepository.findById(taskModel.getTaskId())).thenReturn(Optional.of(taskModel));
        Mockito.when(userService.findUserByEmail(user.getEmailId())).thenReturn(user);


        boolean result = taskService.assignTask(taskModel.getTaskId(),user.getEmailId(),workspaceModel.getWorkspaceId());
        assertTrue(result);

    }

    @Test
    void assignTaskFail_TaskModelAbsent() {

        TaskModel taskModel = Mockito.mock(TaskModel.class);
        Mockito.when(taskRepository.findById(taskModel.getTaskId())).thenReturn(Optional.empty());

        UserModel user = Mockito.mock(UserModel.class);
        WorkspaceModel workspaceModel = Mockito.mock(WorkspaceModel.class);

        boolean result = taskService.assignTask(taskModel.getTaskId(),user.getEmailId(),workspaceModel.getWorkspaceId());
        assertFalse(result);

    }

    @Test
    void assignTaskFail_UserModelAbsent() {

        TaskModel taskModel = Mockito.mock(TaskModel.class);
        UserModel user = Mockito.mock(UserModel.class);
        WorkspaceModel workspaceModel = Mockito.mock(WorkspaceModel.class);

        Mockito.when(taskRepository.findById(taskModel.getTaskId())).thenReturn(Optional.of(taskModel));
        Mockito.when(userService.findUserByEmail(user.getEmailId())).thenReturn(null);

        boolean result = taskService.assignTask(taskModel.getTaskId(),user.getEmailId(),workspaceModel.getWorkspaceId());
        assertFalse(result);

    }


    @Test
    void assignTaskFail_WorkspaceAbsentForUser() {

        TaskModel taskModel = Mockito.mock(TaskModel.class);
        UserModel user = Mockito.mock(UserModel.class);
        WorkspaceModel workspaceModel = Mockito.mock(WorkspaceModel.class);

        Mockito.when(taskRepository.findById(taskModel.getTaskId())).thenReturn(Optional.of(taskModel));
        Mockito.when(userService.findUserByEmail(user.getEmailId())).thenReturn(user);

        boolean result = taskService.assignTask(taskModel.getTaskId(),user.getEmailId(),workspaceModel.getWorkspaceId());
        assertFalse(result);

    }


    @Test
    void changeStatusSuccess() {

        TaskModel taskModel = Mockito.mock(TaskModel.class);
        Mockito.when(taskRepository.findById(taskModel.getTaskId())).thenReturn(Optional.of(taskModel));

        boolean result = taskService.changeStatus(taskModel.getTaskId(),"Closed");
        assertTrue(result);

    }

    @Test
    void changeStatusFail() {

        TaskModel taskModel = Mockito.mock(TaskModel.class);
        Mockito.when(taskRepository.findById(taskModel.getTaskId())).thenReturn(Optional.empty());

        boolean result = taskService.changeStatus(taskModel.getTaskId(),"Closed");
        assertFalse(result);

    }
}
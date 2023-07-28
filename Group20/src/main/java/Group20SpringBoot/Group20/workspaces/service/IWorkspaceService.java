package Group20SpringBoot.Group20.workspaces.service;

import Group20SpringBoot.Group20.boards.entity.BoardModel;
import Group20SpringBoot.Group20.workspaces.entity.WorkspaceModel;

import java.util.List;
//import org.springframework.stereotype.Service;

public interface IWorkspaceService {
    WorkspaceModel createWorkspace(WorkspaceModel workspaceModel);

    WorkspaceModel findWorkspaceByID(int workspaceId);

    boolean addBoard(int workspaceId, int boardId);

    int removeBoard(int workspaceId, int boardId);

    List<BoardModel> getBoardsOfWorkspace(int workspaceId);

    void deleteWorkspace(int workspaceId);

    //sanjay
//    void deleteUserFromWorkspace(int workspaceId, int userId);

//    List<WorkspaceModel> getUserWorkspaces(int userId);

    boolean addUserToWorkspace(int workspaceId, String email);


}

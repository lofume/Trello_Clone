package Group20SpringBoot.Group20.workspaces.service;

import Group20SpringBoot.Group20.boards.entity.BoardModel;
import Group20SpringBoot.Group20.boards.service.BoardService;
import Group20SpringBoot.Group20.user.entity.UserModel;
import Group20SpringBoot.Group20.user.service.UserService;
import Group20SpringBoot.Group20.workspaces.entity.WorkspaceModel;
import Group20SpringBoot.Group20.workspaces.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkspaceService implements IWorkspaceService{
    private final WorkspaceRepository workspaceRepository;
    private final BoardService boardService;
    private final UserService userService;
    @Autowired
    public WorkspaceService(WorkspaceRepository workspaceRepository, BoardService boardService, UserService userService){
        this.workspaceRepository = workspaceRepository;
        this.boardService = boardService;
        this.userService = userService;
    }

    @Override
    public WorkspaceModel createWorkspace(WorkspaceModel workspaceModel) {
        return workspaceRepository.save(workspaceModel);
    }

    @Override
    public WorkspaceModel findWorkspaceByID(int workspaceId) {
        WorkspaceModel workspaceModel = null;

        Optional<WorkspaceModel> optionalWorkspaceModel = workspaceRepository.findById(workspaceId);
        if(optionalWorkspaceModel.isPresent())
        {
            workspaceModel = optionalWorkspaceModel.get();
        }

        return workspaceModel;
    }

    @Override
    public boolean addBoard(int workspaceId, int boardId){
        WorkspaceModel updatedWorkspace = null;
        Optional<WorkspaceModel> workspace = null;
        boolean result = false;

        try {
            workspace = workspaceRepository.findById(workspaceId);
            if (workspace.isPresent()){
                WorkspaceModel workspaceModel = workspace.get();
                BoardModel boardModel = boardService.findBoardByID(boardId);

                List<BoardModel> boards = workspaceModel.getBoards();
                if (boards == null){
                    boards = new ArrayList<>();
                }

                boards.add(boardModel);

                workspaceModel.setBoards(boards);
                workspaceRepository.save(workspaceModel);

                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int removeBoard(int workspaceId, int boardId){
        WorkspaceModel updatedWorkspace = null;
        Optional<WorkspaceModel> workspace = null;

        try {
            workspace = workspaceRepository.findById(workspaceId);
            if (workspace.isPresent()){
                WorkspaceModel workspaceModel = workspace.get();
                BoardModel boardModel = boardService.findBoardByID(boardId);

                List<BoardModel> boards = workspaceModel.getBoards();
                if (boards == null){
                    return -1;
                }

                boards.remove(boardModel);

                workspaceModel.setBoards(boards);

                updatedWorkspace = workspaceRepository.save(workspaceModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return boardId;
    }
    
    @Override
    public List<BoardModel> getBoardsOfWorkspace(int workspaceId) {
        WorkspaceModel workspace = findWorkspaceByID(workspaceId);
        return workspace.getBoards();
    }

    @Override
    public void deleteWorkspace(int workspaceId) {
        workspaceRepository.deleteById(workspaceId);
    }


    //sanjay
    @Override
    public boolean addUserToWorkspace(int workspaceId, String email) {

        WorkspaceModel workspace = null;
        boolean result = false;

        Optional<WorkspaceModel> optionalWorkspaceModel = workspaceRepository.findById(workspaceId);
        if(optionalWorkspaceModel.isPresent()){
            workspace = optionalWorkspaceModel.get();
            result = userService.addUserToWorkspace(email, workspace);
        }
        return result;
    }

//    @Override
//    public void deleteUserFromWorkspace(int workspaceId, int userId){
//        Optional<WorkspaceModel> workspace = null;
//        try{
//            workspace = workspaceRepository.findById(workspaceId);
//            if(workspace.isPresent()){
//
//                WorkspaceModel workspacemodel = workspace.get();
//                UserModel userModel = userService.findUserById(userId);
//
//                List<UserModel> user_List = workspacemodel.getUser_List();
//
//                assert user_List != null;
//                user_List.remove(userModel);
//                workspacemodel.setUser_List(user_List);
//                workspaceRepository.save(workspacemodel);
//            }
//        } catch (Exception err){
//            System.out.println("Cannot remove the user from the workspace");
//        }
//    }

}

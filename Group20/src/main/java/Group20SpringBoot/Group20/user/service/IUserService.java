package Group20SpringBoot.Group20.user.service;

import Group20SpringBoot.Group20.user.entity.UserModel;
import Group20SpringBoot.Group20.workspaces.entity.WorkspaceModel;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

public interface IUserService {
    boolean registerUser(UserModel usermodel);
    int loginUser(String email, String password);
    int resetPassword(String email, String securityAnswer);

    boolean changePassword(int userId, String newPass);

    boolean addUserToWorkspace(String email, WorkspaceModel workspace);
    List<WorkspaceModel> getWorkspaces(int userId);

    //sanjay
//    List<WorkspaceModel> getUserWorkspaces(int userId);
    UserModel findUserById(int userId);

    UserModel findUserByEmail(String email);

    void deleteWorkspaceFromUser(int userId, WorkspaceModel workspace);
//    void addWorkspaceToUser(int userId, int workspaceId);

}

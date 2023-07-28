package Group20SpringBoot.Group20.user.service;

import Group20SpringBoot.Group20.user.entity.UserModel;
import Group20SpringBoot.Group20.user.repository.UserRepository;
import Group20SpringBoot.Group20.workspaces.entity.WorkspaceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean registerUser(UserModel usermodel) {
        Optional<UserModel> optionalUserModel = userRepository.findByEmailId(usermodel.getEmailId());
        if (optionalUserModel.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists With that Email.");
        }
        userRepository.save(usermodel);
        return true;
    }

    @Override
    public int loginUser(String email, String password) {

        UserModel user = null;
        Optional<UserModel> optionalUserModel = userRepository.findByEmailId(email);
        if (optionalUserModel.isPresent()) {
            user = optionalUserModel.get();
            if (user.getPassword().equals(password)) {
                return user.getUserId();
            } else {
                return -1;
            }
        }
        // User with sent in email string does not exist in database
        else {
            return -1;
        }
    }

    @Override
    public int resetPassword(String email, String securityAnswer) {

        UserModel user = null;
        Optional<UserModel> optionalUserModel = userRepository.findByEmailId(email);
        System.out.println(email);
        if (optionalUserModel.isPresent()) {
            user = optionalUserModel.get();
            // If security answer matches, register the new password
            if (user.getSecretKey().equals(securityAnswer)) {
                return user.getUserId();
            } else {
                return -1;
            }
        }
        // User with sent in email string does not exist in database
        else {
            return -1;
        }

    }

    @Override
    public boolean changePassword(int userId, String newPass) {
        UserModel user = null;
        Optional<UserModel> optionalUserModel = userRepository.findById(userId);
        if (optionalUserModel.isPresent()) {
            user = optionalUserModel.get();
            user.setPassword(newPass);
            userRepository.save(user);
        }
        return true;
    }

    @Override
    public boolean addUserToWorkspace(String email, WorkspaceModel workspace) {
        UserModel user = null;
        Optional<UserModel> optionalUserModel = userRepository.findByEmailId(email);

        if (optionalUserModel.isPresent()) {
            user = optionalUserModel.get();

            List<WorkspaceModel> workspaces = user.getWorkspaces();
            workspaces = getWorkspaceModels(workspaces);

            if (!workspaces.contains(workspace)) {
                workspaces.add(workspace);
                user.setWorkspaces(workspaces);
                userRepository.save(user);

                return true;
            }
        }

        return false;
    }

    private List<WorkspaceModel> getWorkspaceModels(List<WorkspaceModel> workspaces) {
        if (workspaces == null) {
            workspaces = new ArrayList<>();
        }
        return workspaces;
    }

    @Override
    public List<WorkspaceModel> getWorkspaces(int userId) {
        UserModel user = findUserById(userId);
        return user.getWorkspaces();
    }

    @Override
    public UserModel findUserById(int userId) {
        UserModel user = null;
        Optional<UserModel> optionalUserModel = userRepository.findById(userId);

        if (optionalUserModel.isPresent()) {
            user = optionalUserModel.get();
        }
        return user;
    }

    @Override
    public UserModel findUserByEmail(String email) {
        UserModel user = null;
        Optional<UserModel> optionalUserModel = userRepository.findByEmailId(email);

        if (optionalUserModel.isPresent()) {
            user = optionalUserModel.get();
        }
        return user;
    }

    @Override
    public void deleteWorkspaceFromUser(int userId, WorkspaceModel workspace) {

            Optional<UserModel> user;
            try {
                user = userRepository.findById(userId);
                if (user.isPresent()) {

                    UserModel usermodel = user.get();

                    List<WorkspaceModel> workspace_list = usermodel.getWorkspaces();

                    assert workspace_list != null;
                    workspace_list.remove(workspace);
                    usermodel.setWorkspaces(workspace_list);
                    userRepository.save(usermodel);

                }

            } catch (Exception err) {
                System.out.println("Cannot remove workspace from user");

            }
    }

}

package Group20SpringBoot.Group20.workspaces.entity;

import Group20SpringBoot.Group20.boards.entity.BoardModel;
import Group20SpringBoot.Group20.user.entity.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Workspaces")
public class WorkspaceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int workspaceId;

    private String workspaceTitle;

    private String workspaceDesc;

    @JsonIgnore
    @OneToMany(targetEntity = BoardModel.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "boardMap")
    private List<BoardModel> boards;

    public WorkspaceModel(String workspaceTitle, String workspaceDesc) {
        this.workspaceTitle = workspaceTitle;
        this.workspaceDesc = workspaceDesc;
    }

    public WorkspaceModel() {

    }

    public int getWorkspaceId() {
        return workspaceId;
    }
    public String getWorkspaceTitle() {
        return workspaceTitle;
    }

    public void setWorkspaceTitle(String workspaceTitle) {
        this.workspaceTitle = workspaceTitle;
    }

    public String getWorkspaceDesc() {
        return workspaceDesc;
    }

    public void setWorkspaceDesc(String workspaceDesc) {
        this.workspaceDesc = workspaceDesc;
    }

    public List<BoardModel> getBoards() {
        return boards;
    }

    public void setBoards(List<BoardModel> boards) {
        this.boards = boards;
    }


    //Sanjay
//    @ManyToMany(targetEntity = UserModel.class)
//    @JoinColumn(name = "user_listMapping")
//    private List<UserModel> user_List;
//
//    public List<UserModel> getUser_List() {
//        return user_List;
//    }
//
//    public void setUser_List(List<UserModel> user_List) {
//        this.user_List = user_List;
//    }

}

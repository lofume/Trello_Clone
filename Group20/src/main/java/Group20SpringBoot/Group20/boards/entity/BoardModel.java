package Group20SpringBoot.Group20.boards.entity;
import Group20SpringBoot.Group20.task.entity.TaskModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Boards")
public class BoardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardId;

    private String boardTitle;

    private String boardDesc;

    @JsonIgnore
    @OneToMany(targetEntity = TaskModel.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "taskMap")
    private List<TaskModel> tasks;



    public BoardModel(String boardTitle, String boardDesc) {
        this.boardTitle = boardTitle;
        this.boardDesc = boardDesc;
    }

    public BoardModel() {

    }

    public int getBoardId() {
        return boardId;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardDesc() {
        return boardDesc;
    }

    public void setBoardDesc(String boardDesc) {
        this.boardDesc = boardDesc;
    }

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }
}

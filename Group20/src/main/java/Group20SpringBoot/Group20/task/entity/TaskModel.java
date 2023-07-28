package Group20SpringBoot.Group20.task.entity;

import Group20SpringBoot.Group20.user.entity.UserModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    private String taskTitle;

    private String taskDesc;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    private String assigneeId;

    private String status;



    public TaskModel(String taskTitle, String taskDesc, Date dueDate, String status) {
        this.taskTitle = taskTitle;
        this.taskDesc = taskDesc;
        this.dueDate = dueDate;
        this.status = status;
    }

    public TaskModel() {}

    public int getTaskId() {
        return taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

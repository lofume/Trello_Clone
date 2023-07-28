package Group20SpringBoot.Group20.boards.service;

import Group20SpringBoot.Group20.boards.entity.BoardModel;
import Group20SpringBoot.Group20.boards.repository.BoardRepository;
import Group20SpringBoot.Group20.task.entity.TaskModel;
import Group20SpringBoot.Group20.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService implements IBoardService {


    private static final int overdue_tasks = 0;
    private static final int today_tasks = 1;
    private static final int this_week_tasks = 2;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    TaskService taskService;

    @Override
    public BoardModel createBoard(@RequestBody BoardModel boardModel) {
        return boardRepository.save(boardModel);
    }

    @Override
    public BoardModel findBoardByID(int boardId) {
        BoardModel boardModel = null;

        Optional<BoardModel> optionalBoardModel = boardRepository.findById(boardId);
        if(optionalBoardModel.isPresent())
        {
            boardModel = optionalBoardModel.get();
        }

        return boardModel;
    }


    @Override
    public void deleteBoard(int boardId) {
        boardRepository.deleteById(boardId);
    }


    @Override
    public boolean addTaskToBoard(int boardId, int taskId) {
        BoardModel updatedBoard = null;
        Optional<BoardModel> optionalBoardModel = null;
        boolean result = false;

        try {
            optionalBoardModel = boardRepository.findById(boardId);
            if (optionalBoardModel.isPresent()){
                updatedBoard = optionalBoardModel.get();
                TaskModel task = taskService.findTaskByID(taskId);

                List<TaskModel> tasks = updatedBoard.getTasks();
                if (tasks == null){
                    tasks = new ArrayList<>();
                }

                tasks.add(task);
                updatedBoard.setTasks(tasks);

                boardRepository.save(updatedBoard);

                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<TaskModel> getTasks(int boardId, String status) {
        BoardModel board = findBoardByID(boardId);
        List<TaskModel> tasks = board.getTasks();

        if (tasks == null){
            tasks = new ArrayList<>();
        }

        return tasks.stream().filter(task -> task.getStatus().equals(status)).toList();
    }

    @Override
    public List<TaskModel> getDateFiltered(int boardId, String status, int when) {
        BoardModel board = null;
        Optional<BoardModel> optionalBoardModel = boardRepository.findById(boardId);

        if (optionalBoardModel.isPresent()){
            board = optionalBoardModel.get();

            List<TaskModel> tasks = board.getTasks();
            tasks = tasks.stream().filter(task -> task.getStatus().equals(status)).toList();
            LocalDate today = LocalDate.now();

            // Overdue - 0
            if (when == overdue_tasks){
                return overDueTasks(tasks, today);
            }
            // Due Today - 1
            if (when == today_tasks){
                return todayDueTasks(tasks, today);
            }
            // Due This Week - 2
            if (when == this_week_tasks){
                return thisWeeksTasks(tasks);
            }

        }

        return null;
    }

    private List<TaskModel> thisWeeksTasks(List<TaskModel> tasks) {
        LocalDate now = LocalDate.now();
        LocalDate firstDayOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));
        LocalDate lastDayOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        return tasks.stream().filter(task -> withinWeek(firstDayOfWeek, lastDayOfWeek, task)).toList();
    }

    private boolean withinWeek(LocalDate firstDayOfWeek, LocalDate lastDayOfWeek, TaskModel taskModel) {
        LocalDate taskDate = Instant.ofEpochMilli(taskModel.getDueDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        boolean flag1 = taskDate.isAfter(firstDayOfWeek);
        boolean flag2 = taskDate.isBefore(lastDayOfWeek);
        return flag1 && flag2;
    }

    private List<TaskModel> todayDueTasks(List<TaskModel> tasks, LocalDate today) {
        return tasks.stream().filter(task -> todayTasks(today, task)).toList();
    }

    private boolean todayTasks(LocalDate today, TaskModel task) {
        LocalDate taskDate = Instant.ofEpochMilli(task.getDueDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        return taskDate.isEqual(today);
    }

    private List<TaskModel> overDueTasks(List<TaskModel> tasks, LocalDate today) {
        return tasks.stream().filter(task -> pastDueDate(today, task)).toList();
    }

    private boolean pastDueDate(LocalDate today, TaskModel task) {
        LocalDate taskDate = Instant.ofEpochMilli(task.getDueDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        return taskDate.isBefore(today);
    }

    @Override
    public List<TaskModel> getNameFiltered(int boardId, String status, String filter) {
        BoardModel board = null;
        Optional<BoardModel> optionalBoardModel = boardRepository.findById(boardId);

        if (optionalBoardModel.isPresent()){
            board = optionalBoardModel.get();

            List<TaskModel> tasks = board.getTasks();
            tasks = tasks.stream().filter(task -> task.getStatus().equals(status)).toList();
            return tasks.stream().filter(task -> task.getTaskTitle().contains(filter)).toList();
        }

        return null;
    }

}

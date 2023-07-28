package Group20SpringBoot.Group20.boards.service;

import Group20SpringBoot.Group20.boards.entity.BoardModel;
import Group20SpringBoot.Group20.boards.repository.BoardRepository;

import Group20SpringBoot.Group20.task.entity.TaskModel;
import Group20SpringBoot.Group20.task.service.TaskService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {


    @Mock
    @Autowired
    private BoardRepository boardRepository;

    @InjectMocks
    BoardService boardService;

    @Mock
    private TaskService taskService;

    @Test
    void createBoardTest() {
        BoardModel boardmodel = new BoardModel();
        boardmodel.setBoardTitle("Test");

        when(boardRepository.save(boardmodel)).thenReturn(boardmodel);

        BoardModel savedBoard = boardService.createBoard(boardmodel);
        assertNotNull(savedBoard);
    }

    @Test
    void findBoardByIDTest() {
        BoardModel boardmodel = new BoardModel();
        boardmodel.setBoardTitle("Test");

        Mockito.when(boardRepository.findById(boardmodel.getBoardId())).thenReturn(Optional.of(boardmodel));
        BoardModel board = boardService.findBoardByID(boardmodel.getBoardId());
        assertNotNull(board);
    }

    @Test
    void deleteBoardTest() {

        BoardModel boardmodel = new BoardModel();
        boardmodel.setBoardTitle("Test");
        boardRepository.save(boardmodel);

        boardService.deleteBoard(boardmodel.getBoardId());
        assertFalse(boardRepository.existsById(boardmodel.getBoardId()));

    }

    @Test
    void findBoardByID_BoardModelAbsentTest() {
        BoardModel boardmodel = new BoardModel();
        boardmodel.setBoardTitle("Test");

        Mockito.when(boardRepository.findById(boardmodel.getBoardId())).thenReturn(Optional.empty());
        BoardModel board = boardService.findBoardByID(boardmodel.getBoardId());
        assertNull(board);
    }

    @Test
    void addTaskToBoardTest() {

        BoardModel boardModel = mock(BoardModel.class);
        TaskModel taskModel = mock(TaskModel.class);

        Mockito.when(boardRepository.findById(boardModel.getBoardId())).thenReturn(Optional.of(boardModel));
        Mockito.when(taskService.findTaskByID(taskModel.getTaskId())).thenReturn(taskModel);

        boolean result = boardService.addTaskToBoard(boardModel.getBoardId(),taskModel.getTaskId());

        assertTrue(result);

    }

    @Test
    void addTaskToBoard_BoardModelAbsentTest() {

        BoardModel boardModel = mock(BoardModel.class);
        TaskModel taskModel = mock(TaskModel.class);

        Mockito.when(boardRepository.findById(boardModel.getBoardId())).thenReturn(Optional.empty());
        boolean result = boardService.addTaskToBoard(boardModel.getBoardId(),taskModel.getTaskId());

        assertFalse(result);

    }

    @Test
    void addTaskToBoard_BoardContainingOneTask() {

        BoardModel boardModel = mock(BoardModel.class);
        TaskModel taskModel = mock(TaskModel.class);

        Mockito.when(boardRepository.findById(boardModel.getBoardId())).thenReturn(Optional.of(boardModel));
        boardService.addTaskToBoard(boardModel.getBoardId(),taskModel.getTaskId());

        assertTrue(boardService.addTaskToBoard(boardModel.getBoardId(),taskModel.getTaskId()));


    }

    @Test
    void getTasksTest() throws ParseException {

        TaskModel taskModel = new TaskModel();
        taskModel.setTaskDesc("Testing Service Layer");
        taskModel.setTaskTitle("Tests");
        taskModel.setAssigneeId("as@gmail.com");
        taskModel.setStatus("Open");

        String dueDateStr ="19/09/2022";
        Date dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(dueDateStr);
        taskModel.setDueDate(dueDate);

        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);

        BoardModel boardModel = new BoardModel();
        boardModel.setBoardDesc("Testing");
        boardModel.setBoardTitle("TestPhase");
        boardModel.setTasks(tasks);


        Mockito.when(boardRepository.findById(boardModel.getBoardId())).thenReturn(Optional.of(boardModel));
        Assertions.assertThat(boardService.getTasks(boardModel.getBoardId(),"Open")).hasSameElementsAs(tasks);

    }

    @Test
    void getDateFiltered_TodayTest(){

        TaskModel taskModel = new TaskModel();
        taskModel.setTaskDesc("Testing Service Layer");
        taskModel.setTaskTitle("Tests");
        taskModel.setAssigneeId("as@gmail.com");
        taskModel.setStatus("Open");

        Date date = new Date();
        taskModel.setDueDate(date);

        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);

        BoardModel boardModel = new BoardModel();
        boardModel.setBoardDesc("Testing");
        boardModel.setBoardTitle("TestPhase");
        boardModel.setTasks(tasks);

        Mockito.when(boardRepository.findById(boardModel.getBoardId())).thenReturn(Optional.of(boardModel));

        int dueToday = 1;
        Assertions.assertThat(boardService.getDateFiltered(boardModel.getBoardId(),"Open",dueToday)).hasSameElementsAs(tasks);

    }

    @Test
    void getDateFiltered_OverdueTest(){

        TaskModel taskModel = new TaskModel();
        taskModel.setTaskDesc("Testing Service Layer");
        taskModel.setTaskTitle("Tests");
        taskModel.setAssigneeId("as@gmail.com");
        taskModel.setStatus("Open");

        LocalDate yesterday = LocalDate.now().minusDays(1);
        Date date = Date.from(yesterday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        taskModel.setDueDate(date);

        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);

        BoardModel boardModel = new BoardModel();
        boardModel.setBoardDesc("Testing");
        boardModel.setBoardTitle("TestPhase");
        boardModel.setTasks(tasks);

        Mockito.when(boardRepository.findById(boardModel.getBoardId())).thenReturn(Optional.of(boardModel));

        int overDue = 0;
        Assertions.assertThat(boardService.getDateFiltered(boardModel.getBoardId(),"Open",overDue)).hasSameElementsAs(tasks);

    }

    @Test
    void getDateFiltered_thisWeekTest(){

        TaskModel taskModel = new TaskModel();
        taskModel.setTaskDesc("Testing Service Layer");
        taskModel.setTaskTitle("Tests");
        taskModel.setAssigneeId("as@gmail.com");
        taskModel.setStatus("Open");

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Date date = Date.from(tomorrow.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        taskModel.setDueDate(date);

        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);

        BoardModel boardModel = new BoardModel();
        boardModel.setBoardDesc("Testing");
        boardModel.setBoardTitle("TestPhase");
        boardModel.setTasks(tasks);

        Mockito.when(boardRepository.findById(boardModel.getBoardId())).thenReturn(Optional.of(boardModel));

        int this_Week = 2;
        Assertions.assertThat(boardService.getDateFiltered(boardModel.getBoardId(),"Open",this_Week)).hasSameElementsAs(tasks);

    }

    @Test
    void getDateFiltered_BoardModelAbsent() {

        BoardModel boardModel = mock(BoardModel.class);
        TaskModel taskModel = mock(TaskModel.class);

        Mockito.when(boardRepository.findById(boardModel.getBoardId())).thenReturn(Optional.empty());
        assertNull(boardService.getDateFiltered(boardModel.getBoardId(),"Closed",0));

    }

    @Test
    void getNameFilteredTestSuccess(){

        TaskModel taskModel = new TaskModel();
        taskModel.setTaskDesc("Testing Service Layer");
        taskModel.setTaskTitle("Tests");
        taskModel.setAssigneeId("as@gmail.com");
        taskModel.setStatus("Open");

        Date date = new Date();
        taskModel.setDueDate(date);

        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);

        BoardModel boardModel = new BoardModel();
        boardModel.setBoardDesc("Testing");
        boardModel.setBoardTitle("TestPhase");
        boardModel.setTasks(tasks);

        Mockito.when(boardRepository.findById(boardModel.getBoardId())).thenReturn(Optional.of(boardModel));

        List<TaskModel> boardTasks = boardService.getNameFiltered(boardModel.getBoardId(),"Open","Tests");
        assertEquals(boardTasks,tasks);

    }

    @Test
    void getNameFilteredTestFail_StatusDiff(){

        TaskModel taskModel = new TaskModel();
        taskModel.setTaskDesc("Testing Service Layer");
        taskModel.setTaskTitle("Tests");
        taskModel.setAssigneeId("as@gmail.com");
        taskModel.setStatus("Open");

        Date date = new Date();
        taskModel.setDueDate(date);

        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);

        BoardModel boardModel = new BoardModel();
        boardModel.setBoardDesc("Testing");
        boardModel.setBoardTitle("TestPhase");
        boardModel.setTasks(tasks);

        Mockito.when(boardRepository.findById(boardModel.getBoardId())).thenReturn(Optional.of(boardModel));

        List<TaskModel> boardTasks = boardService.getNameFiltered(boardModel.getBoardId(),"Closed","Tests");
        assertNotEquals(boardTasks,tasks);
        assertEquals(boardTasks.size(),0);

    }

    @Test
    void getNameFilteredTestFail_FilterDiff(){

        TaskModel taskModel = new TaskModel();
        taskModel.setTaskDesc("Testing Service Layer");
        taskModel.setTaskTitle("Tests");
        taskModel.setAssigneeId("as@gmail.com");
        taskModel.setStatus("Open");

        Date date = new Date();
        taskModel.setDueDate(date);

        List<TaskModel> tasks = new ArrayList<>();
        tasks.add(taskModel);

        BoardModel boardModel = new BoardModel();
        boardModel.setBoardDesc("Testing");
        boardModel.setBoardTitle("TestPhase");
        boardModel.setTasks(tasks);

        Mockito.when(boardRepository.findById(boardModel.getBoardId())).thenReturn(Optional.of(boardModel));

        List<TaskModel> boardTasks = boardService.getNameFiltered(boardModel.getBoardId(),"Open","Coding");
        assertNotEquals(boardTasks,tasks);
        assertEquals(boardTasks.size(),0);

    }

    @Test
    void getNameFilteredTest_BoardModelAbsent(){

        BoardModel boardModel = mock(BoardModel.class);
        Mockito.when(boardRepository.findById(boardModel.getBoardId())).thenReturn(Optional.empty());

        assertNull(boardService.getNameFiltered(boardModel.getBoardId(),"Status","Filter"));

    }







//    @Test
//    void getBoardsTest() {
//
//        boardRepository.save(new BoardModel("Test", "Test"));
//        List<BoardModel> boards = boardService.getBoards();
//        assertNotNull(boards);
//
//    }
}
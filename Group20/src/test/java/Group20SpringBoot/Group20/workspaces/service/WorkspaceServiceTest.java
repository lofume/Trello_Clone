package Group20SpringBoot.Group20.workspaces.service;

import Group20SpringBoot.Group20.boards.entity.BoardModel;
import Group20SpringBoot.Group20.boards.service.BoardService;
import Group20SpringBoot.Group20.user.entity.UserModel;
import Group20SpringBoot.Group20.user.service.UserService;
import Group20SpringBoot.Group20.workspaces.entity.WorkspaceModel;
import Group20SpringBoot.Group20.workspaces.repository.WorkspaceRepository;
import org.hibernate.jdbc.Work;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class WorkspaceServiceTest {

    @Mock
    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Mock
    @Autowired
    private BoardService boardService = new BoardService();

    @Mock
    private UserService userService = new UserService();

    @InjectMocks
    WorkspaceService workspaceService;

    @Test
    void createWorkspaceTest(){

        WorkspaceModel workspaceModel = new WorkspaceModel();
        workspaceModel.setWorkspaceTitle("Test");
        workspaceModel.setWorkspaceDesc("TestingTests");

        Mockito.when(workspaceRepository.save(workspaceModel)).thenReturn(workspaceModel);
        WorkspaceModel savedWorkspace = workspaceService.createWorkspace(workspaceModel);
        assertNotNull(savedWorkspace);


    }


    @Test
    void findWorkspaceByID() {
        WorkspaceModel workspaceModel = new WorkspaceModel();
        workspaceModel.setWorkspaceTitle("Test");
        workspaceModel.setWorkspaceDesc("TestingTests");

        Mockito.when(workspaceRepository.findById(workspaceModel.getWorkspaceId())).thenReturn(Optional.of(workspaceModel));

        WorkspaceModel demoWorkspaceModel = workspaceService.findWorkspaceByID(workspaceModel.getWorkspaceId());
        assertNotNull(demoWorkspaceModel);
    }

    @Test
    void addBoardTest() {
        WorkspaceModel workspaceModel = new WorkspaceModel();
        workspaceModel.setWorkspaceTitle("Test");
        workspaceModel.setWorkspaceDesc("TestingTests");

        Mockito.when(workspaceRepository.findById(workspaceModel.getWorkspaceId())).thenReturn(Optional.of(workspaceModel));

        BoardModel boardmodel = new BoardModel();
        boardmodel.setBoardTitle("Test");

        Mockito.when(boardService.findBoardByID(boardmodel.getBoardId())).thenReturn(boardmodel);

        workspaceService.addBoard(workspaceModel.getWorkspaceId(), boardmodel.getBoardId());
        assertEquals(boardmodel, workspaceModel.getBoards().get(0));
    }

    @Test
    void removeBoardTest() {
        WorkspaceModel workspaceModel = new WorkspaceModel();
        workspaceModel.setWorkspaceTitle("Test");
        workspaceModel.setWorkspaceDesc("TestingTests");

        BoardModel boardmodel = new BoardModel();
        boardmodel.setBoardTitle("Test");
        List<BoardModel> boards = new ArrayList<>();
        boards.add(boardmodel);
        workspaceModel.setBoards(boards);

        Mockito.when(workspaceRepository.findById(workspaceModel.getWorkspaceId())).thenReturn(Optional.of(workspaceModel));
        Mockito.when(boardService.findBoardByID(boardmodel.getBoardId())).thenReturn(boardmodel);

        workspaceService.removeBoard(workspaceModel.getWorkspaceId(), boardmodel.getBoardId());

        assertEquals(0, workspaceModel.getBoards().size());
    }

    @Test
    void getBoardsOfWorkspaceTest() {
        WorkspaceModel workspaceModel = new WorkspaceModel();
        workspaceModel.setWorkspaceTitle("Test");
        workspaceModel.setWorkspaceDesc("TestingTests");

        BoardModel boardmodel = new BoardModel();
        boardmodel.setBoardTitle("Test");
        List<BoardModel> boards = new ArrayList<>();
        boards.add(boardmodel);
        workspaceModel.setBoards(boards);

        Mockito.when(workspaceRepository.findById(workspaceModel.getWorkspaceId())).thenReturn(Optional.of(workspaceModel));

        assertEquals(boards, workspaceService.getBoardsOfWorkspace(workspaceModel.getWorkspaceId()));
    }

    @Test
    void deleteWorkspace() {
        WorkspaceModel workspaceModel = new WorkspaceModel();
        workspaceModel.setWorkspaceTitle("Test");
        workspaceModel.setWorkspaceDesc("TestingTests");
        workspaceRepository.save(workspaceModel);

        workspaceService.deleteWorkspace(workspaceModel.getWorkspaceId());
        assertFalse((workspaceRepository.existsById(workspaceModel.getWorkspaceId())));

    }

    @Test
    void addUserToWorkspace() {
        WorkspaceModel workspaceModel = new WorkspaceModel();
        workspaceModel.setWorkspaceTitle("Test");
        workspaceModel.setWorkspaceDesc("TestingTests");

        Mockito.when(workspaceRepository.findById(workspaceModel.getWorkspaceId())).thenReturn(Optional.of(workspaceModel));

        UserModel user = new UserModel();
        user.setFirstName("Dani");
        user.setLastName("Rojas");
        user.setEmailId("daniaka@yahoo.com");
        List<WorkspaceModel> workspaces = new ArrayList<>();
        user.setWorkspaces(workspaces);


        Mockito.when(userService.addUserToWorkspace(user.getEmailId(), workspaceModel)).thenReturn(true);

        boolean result = workspaceService.addUserToWorkspace(workspaceModel.getWorkspaceId(), user.getEmailId());
        assertTrue(result);

    }
    @Test
    void findWorkspaceByID_NullWorkspaceTest() {

        WorkspaceModel workspaceModel = mock(WorkspaceModel.class);
        Mockito.when(workspaceRepository.findById(workspaceModel.getWorkspaceId())).thenReturn(Optional.empty());

        WorkspaceModel demoWorkspaceModel = workspaceService.findWorkspaceByID(workspaceModel.getWorkspaceId());
        assertNull(demoWorkspaceModel);
    }

    @Test
    void addBoard_WorkspaceModelAbsentTest() {

        WorkspaceModel workspaceModel = mock(WorkspaceModel.class);
        Mockito.when(workspaceRepository.findById(workspaceModel.getWorkspaceId())).thenReturn(Optional.empty());

        BoardModel boardModel = mock(BoardModel.class);

        assertFalse(workspaceService.addBoard(workspaceModel.getWorkspaceId(), boardModel.getBoardId()));

    }

    @Test
    void removeBoard_NullBoards() {

        WorkspaceModel workspaceModel = new WorkspaceModel();
        workspaceModel.setWorkspaceTitle("Test");
        workspaceModel.setWorkspaceDesc("TestingTests");
        workspaceModel.setBoards(null);

        BoardModel boardmodel = mock(BoardModel.class);

        Mockito.when(workspaceRepository.findById(workspaceModel.getWorkspaceId())).thenReturn(Optional.of(workspaceModel));
        Mockito.when(boardService.findBoardByID(boardmodel.getBoardId())).thenReturn(boardmodel);

        workspaceService.removeBoard(workspaceModel.getWorkspaceId(), boardmodel.getBoardId());

        assertEquals(-1, workspaceService.removeBoard(workspaceModel.getWorkspaceId(), boardmodel.getBoardId()));
    }

    @Test
    void addUserToWorkspace_WorkspaceModelAbsent() {

        WorkspaceModel workspaceModel = mock(WorkspaceModel.class);
        UserModel userModel = mock(UserModel.class);

        Mockito.when(workspaceRepository.findById(workspaceModel.getWorkspaceId())).thenReturn(Optional.empty());

        assertFalse(workspaceService.addUserToWorkspace(workspaceModel.getWorkspaceId(), userModel.getEmailId()));

    }

}

import React from "react";
import {Route, Switch} from "react-router-dom"
import Registration from "./pages/Register";
import Loginpage from "./pages/Login";
import Home from "./pages/Home";
import NavigationWorkspace from "./pages/NavigationWorkspace";
import Boards from "./pages/Boards";
import CreateBoard from "./pages/CreateBoard";
import ForgotPass from "./pages/Forgotpassword";
import ResetPass from "./pages/ResetPassword";
import CreateWorkspace from "./pages/CreateWorkspace";
import Workspaces from "./pages/Workspaces";
import DeleteBoard from "./pages/DeleteBoard";
import AddMemberToWorkspace from "./pages/AddMemberToWorkspace";
import NavigationLogin from "./pages/NavigationLogin";
import NavigationBoards from "./pages/NavigationBoards";
import Tasks from "./pages/Tasks";
import NavigationTasks from "./pages/NavigationTasks";
import CreateTask from "./pages/CreateTask";
import AssignMemberToTask from "./pages/AssignMemberToTask";
import ChangeStatus from "./pages/ChangeStatus";
import TasksFilteredByDate from "./pages/TasksFilteredByDate";
import TasksFilteredByName from "./pages/TasksFilteredByName";
import NavigationTasksFiltered from "./pages/NavigationTasksFiltered";

function App() {
  return (   
    <div>

      <React.Fragment>
          <Switch>

            <Route path={["/", "/login"]} exact>
              <NavigationLogin/>
              <Loginpage/>
            </Route>

            <Route path="/home">
              <Home/>
            </Route>

            <Route path="/register">
              <NavigationLogin/>
              <Registration/>
            </Route>

            <Route path="/forgotpassword">
              <NavigationLogin/>
              <ForgotPass/>
            </Route>

            <Route path="/resetpassword">
              <NavigationLogin/>
              <ResetPass/>
            </Route>

            <Route path="/tasks">
              <NavigationTasks/>
              <Tasks/>
            </Route>

            <Route path="/create-task">
              <NavigationTasks/>
              <CreateTask/>
            </Route>

            <Route path="/assign-task">
              <NavigationTasks/>
              <AssignMemberToTask/>
            </Route>

            <Route path="/filteredDate/:id/:date">
              <NavigationTasksFiltered/>
              <TasksFilteredByDate/>
            </Route>

            <Route path="/filteredName/:id/:key">
              <NavigationTasksFiltered/>
              <TasksFilteredByName/>
            </Route>

            <Route path="/change-status">
              <NavigationTasks/>
              <ChangeStatus/>
            </Route>

            <Route path="/boards">
              <NavigationBoards/>
              <Boards/>
            </Route>

            <Route path="/create-board">
              <NavigationBoards/>
              <CreateBoard/>
            </Route>

            <Route path="/delete-board">
              <NavigationBoards/>
              <DeleteBoard/>
            </Route>

            <Route path="/create-workspace">
              <NavigationWorkspace/>
              <CreateWorkspace/>
            </Route>

            <Route path="/addUser-workspace">
              <NavigationWorkspace/>
              <AddMemberToWorkspace/>
            </Route>

            <Route path="/workspaces">
              <NavigationWorkspace/>
              <Workspaces/>
            </Route>

          </Switch>
      </React.Fragment>
    </div>
  );
}

export default App;

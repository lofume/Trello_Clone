import { useHistory } from "react-router-dom";
import ResetPassForm from "../components/forms/ResetPasswordForm";
import r from "../pages/Login.module.css";

/* functionality for reset password page */
function ResetPass(){

  const history = useHistory();
  const userId = localStorage.getItem('user');

//updated front to back end : http://localhost:9001/changePassword/{userId}
  function ResetHandler(user, newPass){
    fetch(`http://localhost:9001/user/changePassword/${userId}?newPass=`+newPass , {
      method: 'PUT',
      headers: {'Content-Type': 'application/json'}
    }).then(
        response => response.json()
    )
        .then( function(data) {
          console.log(data);
          if (data === true) {
            history.replace('/login');
            alert("Password saved successfully.")
          } else {
            alert("Password did not save.")
          }
        })
  }

  return (

      <div className={r.forms}>
        <h1 className={r.loginColor}> Reset Password </h1>
        <ResetPassForm ResetHand={ResetHandler}/>
      </div>
  );
}

export default ResetPass;


import { useHistory } from "react-router-dom";
import ForgotPassForm from "../components/forms/ForgotpassForm";
import f from "../pages/Login.module.css";

/* functionality for forgot password page */
function ForgotPass(){

  const history = useHistory();

  function ForgotHandler(email, securityAnswer){
    fetch(`http://localhost:9001/user/resetPassword/${email}?securityAnswer=${securityAnswer}` , {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json'
      }
    })
        .then(
            response => response.json()
        )
        .then( function(data) {
            console.log(data)
            if (data === -1) {
                alert("Incorrect Security Answer, Or No User Exists With that Email.");
            } else {
                localStorage.setItem('user', data)
                history.replace('/resetpassword')
            }
        })
  }

  return (

      <div className={f.forms}>
        <h1 className={f.loginColor}> Forgot Password </h1>
        <ForgotPassForm forgotPasswordHandler={ForgotHandler}/>
      </div>
  );
}

export default ForgotPass;


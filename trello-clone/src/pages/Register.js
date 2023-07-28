import { useHistory } from "react-router-dom";
import Registerform from "../components/forms/RegisterForm";
import e from "./Register.module.css";

/* functionality for registration page */
function Registration(){

  const history = useHistory();

  function userHandler(user){
    fetch('http://localhost:9001/user/register' , {
      method: 'POST',
      body: JSON.stringify(user),
      headers: {'Content-Type': 'application/json'}
    }).then(
      response => response.json()
    )
    .then( function(data) {
      // console.log(data);
      if (data === true) {
        history.replace('/login');
        alert("User registered successfully.")
      } else {
        alert("User with that email is already registered.")
      }
      return;
    })
  }

  return (
  <section className={e.forms}>
    <h1 className={e.registerColor}> Register Page</h1>
      <Registerform userHand={userHandler}/>
  </section>
  );
}
export default Registration;


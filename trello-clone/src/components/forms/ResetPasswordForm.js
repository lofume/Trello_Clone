import React, { useRef } from 'react';

/* reset form */
function ResetPassForm(props){

    const passwordRef = useRef();

    function ResetPassHandler(event){
        event.preventDefault();

        const newPass = passwordRef.current.value;
        const user = {newPass};


        props.ResetHand(user, newPass);
    }

    return (
        <form onSubmit={ResetPassHandler}>
            <input pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,64}"
                   title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 characters."
                   type="password" required placeholder="Reset Password" ref={passwordRef} />
            <br></br>
            <button> Reset Password </button>
        </form>
    );

}
export default ResetPassForm;
  
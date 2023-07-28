import React, { useRef } from 'react';

/* Login form */
function LoginForm(props){

    const emailRef = useRef();
    const passwordRef = useRef();

    function submitHandler(event){
        event.preventDefault();

        const email = emailRef.current.value;
        const password = passwordRef.current.value;
        const user = {password};

        props.LoginHand(user, email, password);
    }

    return (
        <form onSubmit={submitHandler}>
            <input type="email" required placeholder="Email Address" ref={emailRef} />
            <br></br>
            <input type="password" required placeholder="Password" ref={passwordRef} />
            <br></br>
            <button
            style={{ color: "white", background: "green", padding:"15px" }}> Login </button>
        </form>
    );

}
export default LoginForm;
  
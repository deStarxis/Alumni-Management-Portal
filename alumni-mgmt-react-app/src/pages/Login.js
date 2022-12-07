import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router";
import { Link } from "react-router-dom";
import { processToken } from "../helpers/auth";

function Login() {
  const navigate = useNavigate();

  async function login(event) {
    event.preventDefault();
    const response = await axios.post("/user/login", loginState);
    console.log(response);
    if (response.status === 200) {
      if (processToken(response.data.accessToken)) {
        navigate(process.env.REACT_APP_HOME_ROUTE);
      }
    }
  }

  const initialState = {
    email: "",
    password: "",
  };
  const [loginState, setLoginState] = useState(initialState);

  return (
    <form
      method="POST"
      style={{ width: "50%", margin: "auto", marginTop: "30px" }}
      onSubmit={login}
    >
      <div className="form-group">
        <label htmlFor="email">Username</label>
        <input
          type="email"
          className="form-control"
          id="username"
          name="username"
          onChange={(e) =>
            setLoginState({ ...loginState, username: e.target.value })
          }
          placeholder="Enter Email"
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="password">Password</label>
        <input
          type="password"
          className="form-control"
          id="password"
          name="password"
          onChange={(e) =>
            setLoginState({ ...loginState, password: e.target.value })
          }
          placeholder="Enter Password"
          required
        />
      </div>
      <div className="form-group" style={{ float: "right" }}>
        <Link to={"/forgot-password"}> Forgot Password ?</Link>
      </div>
      <button type="submit" className="btn btn-primary">
        Login
      </button>
    </form>
  );
}

export default Login;

import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router";

function Signup() {
  const [signupState, setSignupState] = useState({});
  const [roles, setRoles] = useState([]);
  const [roleStatus, setRoleStatus] = useState({ roleS: "" });
  const navigate = useNavigate();
  const onSignupClicked = async (event) => {
    event.preventDefault();
    try {
      signupState.role = JSON.parse(signupState.role);
      const response = await axios.post("/user/register", signupState);
      console.log(response.data);
      navigate("/login");
    } catch (error) {
      console.log(error);
    }
  };

  const getRoles = async () => {
    const response = await axios.get("/user/roles");
    setRoles(response.data);
  };

  useEffect(() => {
    getRoles();
  }, []);
  return (
    <form
      method="POST"
      onSubmit={onSignupClicked}
      style={{ width: "50%", margin: "auto", marginTop: "30px" }}
    >
      <div className="form-group">
        <label htmlFor="firstname">First Name</label>
        <input
          type="text"
          className="form-control"
          id="firstname"
          name="firstname"
          onChange={(e) =>
            setSignupState({ ...signupState, firstname: e.target.value })
          }
          placeholder="Enter First Name"
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="lastname">Last Name</label>
        <input
          type="text"
          className="form-control"
          id="lastname"
          name="lastname"
          onChange={(e) =>
            setSignupState({ ...signupState, lastname: e.target.value })
          }
          placeholder="Enter Last Name"
          required
        />
      </div>
      <div className="form-group">
        <label htmlFor="username">Username</label>
        <input
          type="email"
          className="form-control"
          id="username"
          name="username"
          onChange={(e) =>
            setSignupState({ ...signupState, username: e.target.value })
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
            setSignupState({ ...signupState, password: e.target.value })
          }
          placeholder="Enter Password"
          required
        />
      </div>

      <div className="form-group">
        <label htmlFor="role">Role</label>
        <select
          id="role"
          className="form-control"
          name="role"
          value={signupState.role}
          onChange={(e) => {
            setSignupState({ ...signupState, role: e.target.value });
            e.target.value !== ""
              ? setRoleStatus(JSON.parse(e.target.value))
              : setRoleStatus("");
          }}
        >
          <option value="">Select Role</option>
          {roles.map((data) => {
            return (
              <option key={data.id} value={JSON.stringify(data)}>
                {data.role}
              </option>
            );
          })}
        </select>
      </div>

      {roleStatus.role === "Student" && (
        <>
          <div className="row">
            <div className="form-group col-md-6">
              <label htmlFor="firstname">University Id</label>
              <input
                type="number"
                className="form-control"
                id="universityId"
                name="universityId"
                onChange={(e) =>
                  setSignupState({
                    ...signupState,
                    universityId: e.target.value,
                  })
                }
                placeholder="Enter University Id"
                required
              />
            </div>
            <div className="form-group col-md-6">
              <label htmlFor="major">Major</label>
              <select
                id="major"
                className="form-control"
                name="major"
                value={signupState.major}
                onChange={(e) => {
                  setSignupState({ ...signupState, major: e.target.value });
                }}
                required
              >
                <option value="">Select Major</option>
                <option value={"MSCS"}>MSCS</option>
                <option value={"MSD"}>MSD</option>
                <option value={"MBA"}>MBA</option>
                <option value={"ASTROLOGY"}>ASTROLOGY</option>
                <option value={"VS"}>Vedic Science</option>
              </select>
            </div>
          </div>

          <div className="row">
            <div className="form-group col-md-6">
              <label htmlFor="graduatedYear">Graduated Year</label>
              <input
                type="number"
                className="form-control"
                id="graduatedYear"
                name="graduatedYear"
                placeholder="Enter Graduated Year"
                onChange={(e) =>
                  setSignupState({
                    ...signupState,
                    graduatedYear: e.target.value,
                  })
                }
                required
              />
            </div>

            <div className="form-group col-md-6">
              <label htmlFor="firstname">GPA</label>
              <input
                type="number"
                max={4}
                min={0}
                className="form-control"
                id="gpa"
                name="gpa"
                onChange={(e) =>
                  setSignupState({ ...signupState, gpa: e.target.value })
                }
                placeholder="Enter GPA"
                required
              />
            </div>
          </div>
        </>
      )}
      {roleStatus.role === "Faculty" && (
        <div className="form-group">
          <label htmlFor="majorSubject">Major Subject</label>
          <input
            type="text"
            className="form-control"
            id="majorSubject"
            name="majorSubject"
            placeholder="Enter Major Subject"
            onChange={(e) =>
              setSignupState({ ...signupState, majorSubject: e.target.value })
            }
            required
          />
        </div>
      )}
      <br />

      <button type="submit" className="btn btn-primary">
        Signup
      </button>
    </form>
  );
}

export default Signup;

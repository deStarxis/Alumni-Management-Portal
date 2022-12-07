import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { NavLink } from "react-router-dom";
import { getUserRole } from "../../helpers/auth";
import { getStates, getStudentFilterOptions } from "../../helpers/data";
import { toTitleCase } from "../../helpers/utility";

export default function Students() {
  const role = getUserRole();
  console.log(role);
  const filterOptions = getStudentFilterOptions();
  const states = getStates();

  const [students, setStudents] = useState([{}]);

  async function fetchStudents() {
    let route = "/students";
    if (filter !== "all") {
      route += `?filter=${filter}&value=${filterValueRef.current.value}`;
    }
    const response = await axios.get(route);
    let obj = response.data.map((data) => {
      if (role !== "Admin") {
        delete data.active;
      } else {
        data.active = data.active ? (
          <NavLink
            className="blue-dot"
            title="Click to Deactivate"
            onClick={() => {
              handleStatus(data.id, true);
            }}
          ></NavLink>
        ) : (
          <NavLink
            className="grey-dot"
            title="Click to Activate"
            onClick={() => {
              handleStatus(data.id, false);
            }}
          ></NavLink>
        );
      }
      return data;
    });

    setStudents(obj);
    console.log(response.data);
  }

  async function handleStatus(id, status) {
    try {
      console.log(id);
      const response = await axios.put(
        `/persons/changeStatus/${id}/active/${status}`
      );
      if(response.status===200){
        fetchStudents()
      }
      const updatedStudents = students;
      updatedStudents.map((x) => {
        if (x.id === id) {
          x.active = !status;
        }
        return x;
      });
      console.log(updatedStudents);
      setStudents(
        updatedStudents.filter((student) => student.status !== status)
      );
        
      // setStudents(students.map((s)=>s.id!==id))
    } catch (error) {
      console.log(error);
    }
  }

  useEffect(() => {
    fetchStudents();
  }, []);

  const [filter, setFilter] = useState(filterOptions[0]);

  const filterValueRef = useRef();

  const filterStateRef = useRef();

  const onFilter = () => {
    fetchStudents();
  };

  const onfilterChange = (event) => {
    setFilter(event.target.value);
  };

  const getFilterValueField = () => {
    let field;
    switch (filter.toLowerCase()) {
      case "name":
      case "major":
        field = (
          <input
            type="text"
            ref={filterValueRef}
            className="form-control col-3"
          />
        );
        break;
      case "universityid":
        field = (
          <input
            type="number"
            ref={filterValueRef}
            className="form-control col-3"
          />
        );
        break;
      case "state":
        field = (
          <select ref={filterValueRef} className="form-control col-3">
            {states.map((s) => (
              <option key={s.name} value={s.name}>
                {s.name}
              </option>
            ))}
          </select>
        );
        break;
      case "city":
        field = (
          <>
            <select ref={filterStateRef} className="form-control col-3">
              {states.map((s) => (
                <option key={s.name} value={s.name}>
                  {s.name}
                </option>
              ))}
            </select>
            <input
              type="text"
              ref={filterValueRef}
              className="form-control col-3"
            />
          </>
        );
        break;
      default:
        field = <></>;
        break;
    }
    return field;
  };

  return (
    <div className="students container">
      <h1>Students</h1>
      <div
        className="row"
        style={{
          margin: "0px",
          marginBottom: "15px",
          justifyContent: "left",
        }}
      >
        <h5 style={{ margin: "0px", lineHeight: "1.5" }}>Filter By</h5>
        <select
          className="form-control col-3"
          value={filter}
          onChange={onfilterChange}
        >
          {filterOptions.map((f) => (
            <option key={f} value={f}>
              {toTitleCase(f)}
            </option>
          ))}
        </select>
        {filter ? getFilterValueField() : <></>}
        <button onClick={onFilter} className="btn btn-primary col-2">
          Filter
        </button>
      </div>
      <table className="table table-bordered" style={{ marginTop: "10px" }}>
        <thead>
          <tr key="student-row-head">
            {Object.keys(students[0]).map((k) => {
              return (
                <th key={"student-header-cell" + k} scope="col">
                  {toTitleCase(k)}
                </th>
              );
            })}
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {students.map((p) => {
            return (
              <tr key={"student-row-head" + p.id}>
                {Object.keys(p).map((k) => {
                  return <td key={"student-colum" + k + p[k]}>{p[k]}</td>;
                })}
                <td>
                  <NavLink to={"/students/" + p.id} title="View Details">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="16"
                      height="16"
                      fill="currentColor"
                      className="bi bi-eye"
                      viewBox="0 0 16 16"
                    >
                      <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z" />
                      <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z" />
                    </svg>{" "}
                  </NavLink>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}

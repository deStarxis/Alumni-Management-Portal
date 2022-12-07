import axios from "axios";
import { useEffect, useRef, useState } from "react";
import { Button } from "react-bootstrap";
import { Link, NavLink, useParams } from "react-router-dom";
import { getUserRole } from "../../helpers/auth";
import { getJobFilterOptions, getStates } from "../../helpers/data";
import { toTitleCase } from "../../helpers/utility";
import AsyncCreatableSelect from 'react-select/async-creatable';

export default function Jobs() {
  let selectedTag = "";
  const params = useParams();
  const [jobs, setJobs] = useState([{}]);
  const userRole = getUserRole();
  const states = getStates();
  const filterOptions = getJobFilterOptions();
  let title = "Job List";

  if (params.task === "my-jobs") { title = "My Jobs"; }
  else if (params.task === "applied-jobs") { title = "Your Job Application"; }

  async function fetchMyJobs() {
    const response = await axios.get("/jobs/my-jobs");
    let obj = response.data.map((data) => {
      return {
        id: data.id,
        jobTitle: data.jobTitle,
        jobType: data.jobType,
        companyName: data.companyName,
        city: data.address.city,
        state: data.address.state,
      };
    });
    setJobs(obj);
  }

  async function fetchAppliedJobs() {
    const response = await axios.get("/jobs/my-applied-jobs");
    console.log(response.data);
    let obj = response.data.map((data) => {
      return {
        id: data.job.id,
        jobTitle: data.job.jobTitle,
        jobType: data.job.jobType,
        companyName: data.job.company.name,
        city: data.job.address.city,
        state: data.job.address.state,
      };
    });
    setJobs(obj);
  }
  async function fetchJobs() {
    let route = "/jobs";
    if (filter == "all") { }
    else if (filter !== "tags") {
      route += `?filter=${filter}&value=${filterValueRef.current.value}`;
    } else {
      route += `?filter=${filter}&value=${selectedTag}`;
    }
    const response = await axios.get(route);
    let obj = response.data.map((data) => {
      return {
        id: data.id,
        jobTitle: data.jobTitle,
        jobType: data.jobType,
        companyName: data.companyName,
        city: data.address.city,
        state: data.address.state,
      };
    });
    setJobs(obj);
  }

  useEffect(() => {
    if (params.task === "my-jobs") {
      fetchMyJobs();
    } else if (params.task === "applied-jobs") {
      fetchAppliedJobs();
    } else {
      fetchJobs();
    }
  }, [params.task]);

  const [filter, setFilter] = useState(filterOptions[0]);

  const filterValueRef = useRef();
  const filterStateRef = useRef();

  const onFilter = () => {
    fetchJobs();
  };
  const onfilterChange = (event) => {
    setFilter(event.target.value);
  };

  const onTagSelected = (event) => {
    console.log("tagselected", event);
    selectedTag = event.value;
  }

  const getTags = async (filter) => {
    const response = await axios.get(`/jobs/tags/${filter}`);
    console.log("tag result", response.data);
    const tags = response.data.map(t => {
      return {
        label: t.tag,
        value: t.tag
      }
    });
    return tags;
  }

  const onTagFilterChange = (filter) =>
    new Promise((resolve) => {
      resolve(getTags(filter));
    });

  const getFilterValueField = () => {
    let field;
    switch (filter.toLowerCase()) {
      case "tags":
        field = (
          <AsyncCreatableSelect
            // isMulti
            cacheOptions
            defaultOptions
            onChange={onTagSelected}
            loadOptions={onTagFilterChange}
          />
        );
        break;
      case "companyname":
        field = (
          <input
            type="text"
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
    <div className="jobs container">
      <h2>{title}</h2>
      {params.task === "my-jobs" && (
        <>

          {userRole.toLowerCase() === "student" ? (
            <Link to={"/jobs/add"}>
              <Button>Add job</Button>
            </Link>
          ) : null}
        </>
      )

      }
      {params.task === "my-jobs" || params.task === "applied-jobs" ? null : (
        <>
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
                <option value={f}>{toTitleCase(f)}</option>
              ))}
            </select>
            {filter ? getFilterValueField() : <></>}
            <button onClick={onFilter} className="btn btn-primary col-2">
              Filter
            </button>
          </div>
        </>
      )}

      {
        jobs && jobs[0] ?
          <table className="table table-bordered" style={{ marginTop: "10px" }}>
            <thead>
              <tr key={"job-row-head"}>
                {Object.keys(jobs[0])?.map((k) => {
                  return (
                    <th key={"job-header-cell" + k} scope="col">
                      {toTitleCase(k)}
                    </th>
                  );
                })}
                {params.task !== 'applied-jobs' && <th>Action</th>}
              </tr>
            </thead>
            <tbody>
              {jobs.map((p) => {
                return (

                  <tr>
                    {Object.keys(p)?.map((k) => {
                      return <td key={"jobcolumn" + k + p[k]}>{p[k]}</td>;
                    })}
                    {params.task !== 'applied-jobs' &&
                      <td>
                        {params.task === "my-jobs" ? (
                          <>
                            <NavLink to={"/jobs/update/" + p.id}>Update</NavLink>
                          </>
                        ) : (
                          <>
                            <Link to={"/jobs/all/" + p.id}>View</Link>
                          </>
                        )}
                      </td>
                    }
                  </tr>

                );
              })}
            </tbody>
          </table> :
          <div>
            {params.task === "my-jobs" ?(
              <span>You have not posted any jobs</span>

            ):(
              <span>You have not applied to any jobs</span>
            )}
          </div>
      }
    </div>
  );
}

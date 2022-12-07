import React, { useState, useEffect, useRef } from "react";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Container from "react-bootstrap/Container";
import axios from "axios";
import { useNavigate, useParams } from "react-router";
import { getStates } from "../../helpers/data";
import AsyncCreatableSelect from 'react-select/async-creatable';


function JobForm() {
  let selectedTag = [];
  const navigate = useNavigate();
  const states = getStates();
  const params = useParams();
  const [job, setJob] = useState({ company: {}, address: {} });
  const tagRef = useRef();

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    if (name.includes(".")) {
      const [prop1, prop2] = name.split(".");
      setJob((values) => {
        let oldVal = { ...values };
        oldVal[prop1][prop2] = value;
        return { ...values, ...oldVal };
      });
    } else {
      setJob((values) => ({ ...values, [name]: value }));
    }
  };

  const onJobSubmit = async (event) => {
    event.preventDefault();
    const newJob = { ...job };
    // newJob.tags = tagRef.current.getValue().map(x => {
    //   const tag = {};
    //   if (x.id) tag.id = x.id; else tag.id = null;
    //   tag.tag = x.tag ?? x.value;
    //   return tag;
    // });
    if (params.id) {
      delete newJob.student;
      const response = await axios.put(`/jobs/${params.id}`, newJob);
    }
    else {
      newJob.company.address = newJob.address;
      const response = await axios.post("/jobs/", newJob);
    }

    navigate("/jobs/my-jobs");
  };

  const onTagSelected = (event) => {
    console.log("tagselected", event);
    selectedTag = event;
  }

  const getTags = async (filter) => {
    const response = await axios.get(`/jobs/tags/${filter}`);
    console.log("tag result", response.data);
    return response.data;
    // const tags = response.data.map(t => {
    //   return {
    //     label: t.tag,
    //     value: t.tag,
    //     id: t.id,
    //     tag: t.tag
    //   }
    // });
    // return tags;
  }

  const onTagFilterChange = (filter) =>
    new Promise((resolve) => {
      resolve(getTags(filter));
    });

  async function fetchJob() {
    const response = await axios.get(`/jobs/${params.id}`);
    console.log(response.data);
    setJob(response.data);
  }

  useEffect(() => {
    if (params.id) {
      fetchJob();
    }
  }, [params.id]);

  return (
    <>
      <Container style={{ margin: "auto", marginTop: "30px" }}>
        <Form onSubmit={onJobSubmit}>
          <Row>
            <Col>
              <Form.Group controlId="formGridJobTitle">
                <Form.Label
                  style={{ fontWeight: "bolder", fontSize: "1.5rem" }}
                >
                  Add Job
                </Form.Label>
              </Form.Group>
              <Form.Group controlId="formGridJobDescription">
                <Form.Label>Job Title</Form.Label>
                <Form.Control
                  as={"input"}
                  placeholder="Enter Job Title"
                  required
                  onChange={handleChange}
                  name="jobTitle"
                  value={job.jobTitle}
                />
              </Form.Group>
              <Form.Group controlId="formGridJobDescription">
                <Form.Label>Job Type</Form.Label>
                <Form.Control
                  as={"input"}
                  placeholder="Enter Job Type"
                  required
                  onChange={handleChange}
                  name="jobType"
                  value={job.jobType}
                />
              </Form.Group>
              <Form.Group controlId="formGridJobDescription">
                <Form.Label>Tags</Form.Label>
                <AsyncCreatableSelect
                  ref={tagRef}
                  isMulti
                  getOptionLabel={(option) => option.tag ?? option.label}
                  getOptionValue={(option) => option.id}
                  cacheOptions
                  defaultOptions
                  onChange={onTagSelected}
                  loadOptions={onTagFilterChange}
                />
              </Form.Group>
              <Form.Group controlId="formGridJobDescription">
                <Form.Label>Job Description</Form.Label>
                <Form.Control
                  as={"textarea"}
                  rows={6}
                  placeholder="Enter Job Description"
                  required
                  onChange={handleChange}
                  name="description"
                  value={job.description}
                />
              </Form.Group>
            </Col>
          </Row>
          {/* JOB COMPANY */}
          <Row>
            <Col sm={6}>
              <Form.Group controlId="formGridCompanyTitle">
                <Form.Label
                  style={{ fontWeight: "bolder", fontSize: "1.5rem" }}
                >
                  Company Details
                </Form.Label>
              </Form.Group>
              <Form.Group controlId="formGridCompany">
                <Form.Label>Name</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Enter Company Name"
                  required
                  onChange={handleChange}
                  name="company.name"
                  value={job.company.name}
                />
              </Form.Group>
            </Col>
          </Row>
          <Row>
            <Col sm={6}>
              <Form.Group controlId="formGridCompanyTitle">
                <Form.Label
                  style={{ fontWeight: "bolder", fontSize: "1.5rem" }}
                >
                  Address Details
                </Form.Label>
              </Form.Group>
            </Col>
          </Row>
          <Row>
            <Col sm={3}>
              <Form.Group controlId="formGridZip">
                <Form.Label>Street</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Enter Street"
                  required
                  onChange={handleChange}
                  name="address.street"
                  value={job.address.street}
                />
              </Form.Group>
            </Col>

            <Col sm={3}>
              <Form.Group controlId="formGridCity">
                <Form.Label>City</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Enter City"
                  required
                  onChange={handleChange}
                  name="address.city"
                  value={job.address.city}
                />
              </Form.Group>
            </Col>

            <Col sm={3}>
              <Form.Group controlId="formGridState">
                <Form.Label>State</Form.Label>
                <select
                  className="form-control"
                  name="address.state"
                  value={job.address.state}
                  onChange={handleChange}
                >
                  {states.map((s) => (
                    <option key={s.name} value={s.name}>
                      {s.name}
                    </option>
                  ))}
                </select>
              </Form.Group>
            </Col>

            <Col sm={3}>
              <Form.Group controlId="formGridZip">
                <Form.Label>Zip</Form.Label>
                <Form.Control
                  type="number"
                  placeholder="Enter zip"
                  required
                  onChange={handleChange}
                  name="address.zip"
                  value={job.address.zip}
                />
              </Form.Group>
            </Col>
          </Row>
          <Row>
            <Col>
              <input className="btn btn-primary" type="submit" value="Save" />
            </Col>
          </Row>
        </Form>
      </Container>
    </>
  );
}

export default JobForm;

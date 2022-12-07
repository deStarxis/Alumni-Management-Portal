import React, { useState } from "react";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Container from "react-bootstrap/Container";
import { useNavigate } from "react-router";
import axios from "axios";
import { getStates } from "../../helpers/data";
function JobExperienceForm() {
  const states = getStates();
  const [jobExperience, setJobExperience] = useState({
    company: {
      address: {},
    },
  });
  const navigate = useNavigate();
  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    if (name.includes(".")) {
      const props = name.split(".");
      if (props.length == 2) {
        setJobExperience((values) => {
          let oldVal = { ...values };
          oldVal[props[0]][props[1]] = value;
          return { ...values, ...oldVal };
        });
      } else if (props.length == 3) {
        setJobExperience((values) => {
          let oldVal = { ...values };
          oldVal[props[0]][props[1]][props[2]] = value;
          return { ...values, ...oldVal };
        });
      }
    } else {
      setJobExperience((values) => ({ ...values, [name]: value }));
    }
  };
  const onJobExperienceSubmit = async (event) => {
    event.preventDefault();
    // console.log("Job Experience", jobExperience);
    const newJobExperience = { ...jobExperience };
    newJobExperience.company.address = newJobExperience.company.address;
    const response = await axios.post("/job-experiences", newJobExperience);
    console.log("saved", response);
    navigate("/profile");
  };
  return (
    <>
      <Container style={{ margin: "auto", marginTop: "30px" }}>
        <Form onSubmit={onJobExperienceSubmit}>
          <Row>
            <Col>
              <Form.Group controlId="formGridJobExperienceTitle">
                <Form.Label
                  style={{ fontWeight: "bolder", fontSize: "1.5rem" }}
                >
                  Add Job Experience
                </Form.Label>
              </Form.Group>
              <Form.Group controlId="formGridJobType">
                <Form.Label>Job Type</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Enter Job Type"
                  required
                  onChange={handleChange}
                  name="jobType"
                  value={jobExperience.jobType}
                />
              </Form.Group>
              <Form.Group controlId="formGridJobType">
                <Form.Label>Job Position</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Enter Job Position"
                  required
                  onChange={handleChange}
                  name="jobPosition"
                  value={jobExperience.jobPosition}
                />
              </Form.Group>
              <Form.Group controlId="formGridJobDescription">
                <Form.Label>Job Description</Form.Label>
                <Form.Control
                  as={"textarea"}
                  rows={10}
                  placeholder="Enter Job Description"
                  required
                  onChange={handleChange}
                  name="jobDescription"
                  value={jobExperience.jobDescription}
                />
              </Form.Group>
            </Col>
          </Row>
          <Row>
            <Col sm={6}>
              <Form.Group controlId="formGridJobExperienceStartDate">
                <Form.Label>Start Date</Form.Label>
                <Form.Control
                  type={"date"}
                  placeholder="Select Start Date"
                  required
                  onChange={handleChange}
                  name="startDate"
                  value={jobExperience.startDate}
                />
              </Form.Group>
            </Col>
            <Col sm={6}>
              <Form.Group controlId="formGridJobExperienceEndDate">
                <Form.Label>End Date</Form.Label>
                <Form.Control
                  type={"date"}
                  placeholder="Select End Date"
                  required
                  onChange={handleChange}
                  name="endDate"
                  value={jobExperience.endDate}
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
                  value={jobExperience.company.name}
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
                  name="company.address.street"
                  value={jobExperience.company.address.street}
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
                  name="company.address.city"
                  value={jobExperience.company.address.city}
                />
              </Form.Group>
            </Col>

            <Col sm={3}>
              <Form.Group controlId="formGridState">
                <Form.Label>State</Form.Label>
                <select
                  className="form-control"
                  name="company.address.state"
                  value={jobExperience.company.address.state}
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
                  name="company.address.zip"
                  value={jobExperience.company.address.zip}
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

export default JobExperienceForm;

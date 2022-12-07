import React, { useState, useEffect } from "react";
import { getUser } from "../helpers/auth";
import axios from "axios";
import Card from "react-bootstrap/Card";
import { Container } from "react-bootstrap";
import { Link } from "react-router-dom";

function Profile() {
  const user = getUser();
  const [profile, setProfile] = useState({});
  const [jobExperiences, setJobExperience] = useState([]);

  async function getProfile() {
    const response = await axios.get(`/persons/profile`);
    setProfile(response.data);
    console.log(profile);
  }

  async function fetchJobExperiences() {
    const response = await axios.get(
      `/job-experiences/getExperiences/${user.userId}`
    );
    console.log(response.data);
    let obj = response.data.map((data) => {
      return {
        companyName: data.company.name,
        jobPosition: data.jobPosition,
        jobType: data.jobType,
        city: data.company.address.city,
        state: data.company.address.state,
        startDate: data.startDate,
        endDate: data.endDate,
        jobDescription: data.jobDescription,
      };
    });

    setJobExperience(obj);
  }
  useEffect(() => {
    getProfile();
    if (user.rol[0].role === "Student") {
      fetchJobExperiences();
    }
  }, [user.userId]);
  return (
    <Container
      style={{ margin: "auto", marginTop: "30px", marginBottom: "30px" }}
    >
      <Card>
        <Card.Header style={{ fontWeight: "bold" }}>My Profile</Card.Header>
        <Card.Body>
          <Card.Title>
            {profile.firstname} {profile.lastname}
          </Card.Title>

          <div className="row">
            <div className="col">
              <b>Personal Information</b>
              <hr />
              Email: {profile.username} <br />
              Address:{" "}
              {profile.address ? (
                <>
                  {profile.address.street},{profile.address.city},
                  {profile.address.zip},{profile.address.state}
                  <br />
                  <Link
                    to={`/profile/address/update/${profile.address.id}`}
                    className="btn btn-success"
                  >
                    &#9998; Change Address
                  </Link>
                </>
              ) : (
                <>
                  <br />
                  <Link
                    to={`/profile/address/add/${profile.id}`}
                    className="btn btn-success"
                  >
                    &#10009; Add Address
                  </Link>
                </>
              )}{" "}
              <br />
            </div>
          </div>
          <hr />
          {user.rol[0].role !== "Admin" && (
            <>
              <div className="row">
                <div className="col">
                  <b>Other Information </b>
                  <hr />

                  {user.rol[0].role === "Student" && (
                    <>
                      University Id: {profile.universityId} <br />
                      Major: {profile.major} <br />
                      Graduation Year: {profile.graduatedYear} <br />
                      GPA : {profile.gpa} <br />
                    </>
                  )}
                  {user.rol[0].role === "Faculty" && (
                    <>
                      Major Subject: {profile.majorSubject} <br />
                    </>
                  )}
                </div>
              </div>
            </>
          )}
        </Card.Body>
      </Card>
      {user.rol[0].role === "Student" && (
        <>
          <Card>
            <Card.Header style={{ fontWeight: "bold" }}>
              My CV
              <Link
                to={"/add-cv"}
                className="btn btn-primary"
                style={{ float: "right", margin: "auto" }}
              >
                Add New CV
              </Link>
            </Card.Header>
            <Card.Body>//add cv code here</Card.Body>
          </Card>
          <Card>
            <Card.Header style={{ fontWeight: "bold" }}>
              Job Experiences
              <Link
                to={"/add-job-experience"}
                className="btn btn-primary"
                style={{ float: "right", margin: "auto" }}
              >
                Add Job Experience
              </Link>
            </Card.Header>
            <Card.Body>
              {jobExperiences.length ? (
                <>
                  {jobExperiences.map((je) => {
                    return (
                      <>
                        <Card.Title>
                          {je.jobPosition} (From:{" "}
                          {je.startDate.substring(0, 10)}
                          -To:{je.endDate.substring(0, 10)} )
                        </Card.Title>
                        <Card.Text>
                          Job Type:{je.jobType}
                          <br />
                          Company Name: {je.companyName}
                          <br />
                          Company Address:{je.city} , {je.state}
                          <br />
                          Job Description: {je.jobDescription}
                        </Card.Text>

                        <hr />
                      </>
                    );
                  })}
                </>
              ) : (
                <>No Job Experiences</>
              )}
            </Card.Body>
          </Card>
        </>
      )}
    </Container>
  );
}

export default Profile;

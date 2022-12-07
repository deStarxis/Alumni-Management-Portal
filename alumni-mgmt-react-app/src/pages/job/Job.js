import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import Card from "react-bootstrap/Card";
import { Button, Container, Form, Spinner } from "react-bootstrap";
import axios from "axios";
import { getUser, getUserRole } from "../../helpers/auth";

function Job() {
  const params = useParams();
  const user = getUser();
  const role = getUserRole();
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");
  const [jobState, setJobState] = useState({
    company: {
      address: {},
    },
    student: {},
    address: {},
  });
  async function fetchJob() {
    const response = await axios.get("/jobs/" + params.id);
    setJobState(response.data);
  }
  console.log(jobState);
  useEffect(() => {
    fetchJob();
  }, []);

  const onButtonClick = async () => {
    const response = await axios.post(
      `/jobs/${params.id}/apply/${user.userId}`
    );
    const message = `Hi, ${user.firstname} Your job application for the position of ${jobState.jobTitle} at ${jobState.company.name} has been submitted successfully!!`;
    const notification = `Hi, ${jobState.student.firstname} Your have a new application submitted for the position of ${jobState.jobTitle} at ${jobState.company.name} by ${user.firstname}!!`;
    if (response.status === 200) {
      setLoading(true);
      const response2 = await axios.post(
        `/jobs/send-email/${user.sub}/${message}`
      );
      const response3 = await axios.post(
        `/jobs/send-notification/${jobState.student.username}/${message}`
      );
      if (response2.status === 200 && response3.status === 200) {
        setMessage("Job Application Submitted Successfully");
        setLoading(false);
      } else {
        setLoading(false);
        setMessage(response.data);
      }
    }
  };

  return (
    <Container
      style={{ margin: "auto", marginTop: "30px", marginBottom: "30px" }}
    >
      <Card>
        <Card.Body>
          <Card.Title>{jobState.jobTitle}</Card.Title>
          <Card.Text>
            {jobState.company.name}: {jobState.address.city},{" "}
            {jobState.address.state}
          </Card.Text>
          <Card.Text>{jobState.jobType}</Card.Text>
          <Card.Img
            variant="top"
            title={jobState.jobTitle}
            src="https://sites.google.com/a/yannix.com/jobs_201605/_/rsrc/1577096337293/job-positions/software-developer/ads_software_dev_newlogo.jpg"
          />
          <Card.Text>Job Description</Card.Text>
          <Card.Text>{jobState.description}</Card.Text>
        </Card.Body>
        <Card.Body>
          <Card.Text>
            <strong>Posted By:</strong> {jobState.student.firstname}{" "}
            {jobState.student.lastname}
          </Card.Text>
          {role === "Student" && (
            <>
              <Button
                variant="primary"
                onClick={onButtonClick}
                disabled={loading}
              >
                {loading ? (
                  <>
                    <Spinner style={{ width: "1.5rem", height: "1.5rem" }} />
                    Your job application is being submitted....
                  </>
                ) : (
                  <> Apply</>
                )}
              </Button>
              {message && (
                <>
                  <Form.Text className="text-muted">
                    {message} Kindly go and check your email for its
                    verification!!
                  </Form.Text>
                </>
              )}
            </>
          )}
        </Card.Body>
      </Card>
    </Container>
  );
}

export default Job;

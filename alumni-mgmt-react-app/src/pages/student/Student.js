import axios from "axios";
import { useEffect, useState } from "react";
import { Card, Col, Container, Form, Row } from "react-bootstrap";
import { useParams } from "react-router";
import { getUser } from "../../helpers/auth";

export default function Student() {
  const { id } = useParams();
  const [comment, setComment] = useState("");
  const [person, setPerson] = useState({
    address: {},
    roles: [{}],
  });
  const [comments, setComments] = useState([]);
  const user = getUser();
  const getPerson = async () => {
    const response = await axios.get("/persons/" + id);
    setPerson(response.data);
  };
  const addComment = async (e) => {
    e.preventDefault();
    const response = await axios.post("/comments", {
      student: person,
      comment: comment,
    });
    setComment("");
    getComments();
  };
  const getComments = async () => {
    const response = await axios.get("/comments/students/" + id);
    setComments(response.data);
  };
  const onPasswordReset=async()=>{
    const response = await axios.post(`/persons/send-password-reset-email/${person.id}/${person.username}`);
    console.log(response.data);
  }
  useEffect(() => {
    getPerson();
    if (user.rol[0].role === "Faculty") {
      getComments();
    }
  }, [id]);
  return (
    <Container
      style={{ margin: "auto", marginTop: "30px", marginBottom: "30px" }}
    >
      <Card>
        <Card.Header style={{ fontWeight: "bold" }}>
          {person.firstname} {person.lastname}
          {user.rol[0].role === "Admin" && (
            <>
              <button
                className="btn btn-danger"
                style={{ float: "right", margin: "auto" }}
                onClick={onPasswordReset}
              >
                Reset Password
              </button>
            </>
          )}
        </Card.Header>
        <Card.Body>

          <Card.Text>
            Address:
            {person.address ? (
              <>
                {person.address ? (
                  <>
                    {person.address.street},{person.address.city},{person.address.zip},
                    {person.address.state}
                  </>) : (" ")}
              </>
            ):(
              <span>{" "}No address</span>
            )}

          </Card.Text>

          <div className="row">
            <div className="col">
              <b>Other Information </b>
              <hr />

              {person.roles[0].role === "Student" && (
                <>
                  University Id: {person.universityId} <br />
                  Major: {person.major} <br />
                  Graduation Year: {person.graduatedYear} <br />
                  GPA : {person.gpa} <br />
                </>
              )}
              {person.roles[0].role === "Faculty" && (
                <>
                  Major Subject: {person.majorSubject} <br />
                </>
              )}
            </div>
          </div>
        </Card.Body>
      </Card>
      {user.rol[0].role === "Faculty" && (
        <Card>
          <Card.Header style={{ fontWeight: "bold" }}>Comments</Card.Header>
          <Card.Body>
            <Form method="POST">
              <Row>
                <Col sm={3}>
                  <Form.Group controlId="formGridZip">
                    <Form.Label>Your Comment</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder="Enter your comment"
                      name="comment"
                      required
                      value={comment}
                      onChange={(e) => setComment(e.target.value)}
                    />
                  </Form.Group>
                </Col>
              </Row>
              <button className="btn btn-primary" onClick={addComment}>
                Add Comment
              </button>
            </Form>
            <br />
            <hr />
            {comments.map((c) => {
              return (
                <>
                  <Card.Text style={{ fontWeight: "bold" }}>
                    <span className="text-muted">Comment: {c.comment}</span>
                    <br />
                    Posted By:{" "}
                    {c.faculty.firstname.concat(" ", c.faculty.lastname)}
                    <br />
                  </Card.Text>
                  <hr />
                </>
              );
            })}
          </Card.Body>
        </Card>
      )}
    </Container>
  );
}

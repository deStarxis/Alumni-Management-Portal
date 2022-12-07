import React, { useState } from "react";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import axios from "axios";
import { Spinner } from "react-bootstrap";

function ForgotPassword() {
  const [email, setEmail] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");
  const sendPasswordResetEmail = async (e) => {
    e.preventDefault();
    setLoading(true);
    const response = await axios.post("/user/sendPasswordResetLink", {
      recipient: email,
    });
    if (response.status === 200) {
      setMessage(response.data);
      setLoading(false);
      setEmail("");
    } else {
      setLoading(false);
      setMessage(response.data);
    }
  };

  return (
    <Container style={{ margin: "auto", marginTop: "30px" }}>
      <Form onSubmit={sendPasswordResetEmail}>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label style={{ fontWeight: "bolder", fontSize: "1.5rem" }}>
            Forgot Password
          </Form.Label>
          <Form.Text className="text-muted">
            Provide your email address. We will send you a link to reset your
            password.
          </Form.Text>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Control
            type="email"
            placeholder="Enter your email"
            required
            name="email"
            id="email"
            value={email}
            onChange={(e) => {
              setEmail(e.target.value);
            }}
          />
        </Form.Group>
        <Button variant="primary" type="submit" disabled={loading}>
          {loading ? (
            <>
              <Spinner style={{ width: "1.5rem", height: "1.5rem" }} />
              Sending....
            </>
          ) : (
            <> Send Link</>
          )}
        </Button>
        {message && (
          <>
            <Form.Text className="text-muted">
              {message} Kindly go and check your mailbox!!
            </Form.Text>
          </>
        )}
      </Form>
    </Container>
  );
}

export default ForgotPassword;

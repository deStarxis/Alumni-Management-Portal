import React, { useEffect, useState } from "react";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import axios from "axios";
import { useNavigate, useParams } from "react-router";

function ResetPassword() {
  const params = useParams();
  const navigate = useNavigate();
  const [resetState, setResetState] = useState({});
  const onFormSubmit = async (e) => {
    e.preventDefault();
    if (resetState.newPassword === resetState.verifyNewPassword) {
      const response = await axios.post(
        "/user/reset-password/" + params.resetToken,
        {
          password: resetState.verifyNewPassword,
        }
      );
      if (response.status == 200) {
        navigate("/login");
      }
    }
  };
  useEffect(() => {
    console.log(resetState.newPassword);
  });
  return (
    <Container style={{ margin: "auto", marginTop: "30px" }}>
      <Form onSubmit={onFormSubmit} method="POST">
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label style={{ fontWeight: "bolder", fontSize: "1.5rem" }}>
            Reset Password
          </Form.Label>
          <Form.Text className="text-muted">
            Create a new password for your account
          </Form.Text>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>New Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="New Password"
            name={"newPassword"}
            value={resetState.newPassword}
            required
            onChange={(e) =>
              setResetState({ ...resetState, [e.target.name]: e.target.value })
            }
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Verify New Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Verify New Password"
            name={"verifyNewPassword"}
            required
            value={resetState.verifyNewPassword}
            onChange={(e) =>
              setResetState({ ...resetState, [e.target.name]: e.target.value })
            }
          />
          {resetState.newPassword !== resetState.verifyNewPassword && (
            <>
              <Form.Text className="text-muted">
                Password does not match
              </Form.Text>
            </>
          )}
        </Form.Group>
        <Button variant="primary" type="submit">
          Reset Password
        </Button>
      </Form>
    </Container>
  );
}

export default ResetPassword;

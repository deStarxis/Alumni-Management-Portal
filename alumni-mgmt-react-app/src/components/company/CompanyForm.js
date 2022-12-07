import React, { useEffect, useRef, useState } from "react";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Container from "react-bootstrap/Container";
function CompanyForm(props) {
  const companyFormRef = useRef();
  const [company, setCompany] = useState({});

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;

    setCompany((values) => ({ ...values, [name]: value }));
    setTimeout(() => {
      props.onCompanyUpdate({ name: event.target.value });
    }, 10)
  };

  useEffect(() => {
    props.setJobFormRef(companyFormRef);
  }, []);
  return (
    <>
      <Container style={{ margin: "auto", marginTop: "30px" }}>
        <Form ref={companyFormRef}>
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
                  name="name"
                  value={company.name}
                />
              </Form.Group>
            </Col>
          </Row>
        </Form>
      </Container>
    </>
  );
}

export default CompanyForm;

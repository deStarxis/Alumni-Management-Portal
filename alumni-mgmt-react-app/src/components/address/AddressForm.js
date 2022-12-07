import React, { useState,useEffect} from "react";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Container from "react-bootstrap/Container";
import { Button } from "react-bootstrap";
import {getStates} from  "../../helpers/data";
import axios from "axios";
import { toTitleCase } from "../../helpers/utility";
import { useParams ,useNavigate} from "react-router";
function AddressForm() {
  const navigate=useNavigate();
  const states= getStates();
  const [address, setAddress] = useState({});
  const params=useParams();
  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setAddress((values) => ({ ...values, [name]: value }));
  };

   async function handleSubmit(event){
    event.preventDefault();
    let url=(params.task==='update')? `/addresses/${params.id}`:`/persons/updateAddress/${params.id}`;
    try{
      const response=await axios.put(url,address);
      navigate("/profile");
    }catch(error){
      console.log(error);
    }
  }

  async function fetchAddress(){
    const response =await axios.get(`/addresses/${params.id}`);
    setAddress(response.data);
    console.log(response.data);

  }

  useEffect(() => {
    if(params.task==='update')
    fetchAddress()

  }, [params.task]);



  return (
    <Container style={{ margin: "auto", marginTop: "30px" }}>
      <Form onSubmit={handleSubmit}>
        <Row>
          <Col sm={3}>
            <Form.Group controlId="formGridCompanyTitle">
              <Form.Label style={{ fontWeight: "bolder", fontSize: "1.5rem" }}>
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
                
                onChange={handleChange}
                name="street"
                value={address.street}
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
                name="city"
                value={address.city}
              />
            </Form.Group>
          </Col>

          <Col sm={3}>
            <Form.Group controlId="formGridState">
              <Form.Label>State</Form.Label>
              <select className="form-control" 
              name="state" 
              value={address.state} 
              onChange={handleChange}>
                {
                   states.map(s => <option  key={s.name} value={s.name}>{s.name}</option>)
                }
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
                name="zip"
                value={address.zip}
              />
            </Form.Group>
          </Col>

          
        </Row>
        <Row>
          <Col>
          <Button type="submit" variant="primary">{toTitleCase(params.task)}</Button>
          </Col>
        </Row>
        
      </Form>
    </Container>
  );
}

export default AddressForm;

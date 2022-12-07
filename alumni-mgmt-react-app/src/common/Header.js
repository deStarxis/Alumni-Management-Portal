import { roles } from "aria-query";
import React from "react";
import { NavDropdown } from "react-bootstrap";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Link, useNavigate } from "react-router-dom";
import { getUser, getUserRole, isLoggedIn, logout } from "../helpers/auth";
import routes from "../helpers/routes";
function Header() {
  let loggedIn = isLoggedIn();
  let fullname;
  let role;
  if (loggedIn) {
    const user = getUser();
    fullname = user.firstname.concat(" ", user.lastname);
    role = getUserRole();
  }
  const navigate = useNavigate();
  let myRoutes = loggedIn ? routes.privateRoutes : routes.publicRoutes;

  const routesComponents = myRoutes.map(
    ({ name, route, key, onNavBar, roles }) =>
      onNavBar && (!roles || roles?.includes(role.toLowerCase())) ? (
        <Nav.Link key={key} as={Link} to={route}>
          {name}
        </Nav.Link>
      ) : null
  );

  const onLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <Navbar bg="dark" expand="lg" variant="dark">
      <Container>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Navbar.Brand as={Link} to="/">
              MIU Alumni
            </Navbar.Brand>
            {routesComponents}
          </Nav>
          {loggedIn ? (
            <Nav className="ml-auto">
              <NavDropdown title={`${fullname}`}>
                <NavDropdown.Item as={Link} to="/profile">
                  Profile
                </NavDropdown.Item>
                {role === "Student" && (
                  <>
                    <NavDropdown.Item as={Link} to="/jobs/my-jobs">
                      My Jobs
                    </NavDropdown.Item>
                    <NavDropdown.Item as={Link} to="/jobs/applied-jobs">
                      Applied Jobs
                    </NavDropdown.Item>
                  </>
                )}

                <NavDropdown.Divider />
                <NavDropdown.Item onClick={onLogout}>Logout</NavDropdown.Item>
              </NavDropdown>
            </Nav>
          ) : (
            <></>
          )}
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Header;

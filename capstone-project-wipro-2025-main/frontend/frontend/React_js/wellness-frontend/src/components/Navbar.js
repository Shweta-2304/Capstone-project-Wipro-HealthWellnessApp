import React from "react";
import { NavLink } from "react-router-dom";
import { Navbar, Nav, Container, NavDropdown } from "react-bootstrap";

const NavigationBar = () => {
  const getNavLinkClass = ({ isActive }) =>
    isActive
      ? "fw-semibold nav-link-active px-3 py-2"
      : "fw-medium nav-link-base px-3 py-2";

  return (
    <Navbar expand="lg" sticky="top" className="custom-navbar shadow-sm py-2">
      <Container>
        {/* Brand */}
        <Navbar.Brand
          as={NavLink}
          to="/"
          end
          className="fw-bold fs-4 text-dark text-decoration-none"
          style={{ gap: "8px" }}
        >
          🌸 WellnessApp
        </Navbar.Brand>

        <Navbar.Toggle aria-controls="main-navbar-nav" />
        <Navbar.Collapse id="main-navbar-nav">
          <Nav className="mx-auto gap-2">
            <Nav.Link as={NavLink} to="/patients" end className={getNavLinkClass}>
              🧑‍🤝‍🧑 Patients
            </Nav.Link>
            <Nav.Link as={NavLink} to="/providers" end className={getNavLinkClass}>
              👩‍⚕️ Providers
            </Nav.Link>
            <Nav.Link as={NavLink} to="/appointments" className={getNavLinkClass}>
              📅 Appointments
            </Nav.Link>
            <NavDropdown
              title="🌿 Services"
              id="services-enrollments-dropdown"
              menuVariant="light"
              className="nav-dropdown"
            >
              <NavDropdown.Item as={NavLink} to="/wellness">
                🧘 Wellness Services
              </NavDropdown.Item>
              <NavDropdown.Item as={NavLink} to="/enrollments">
                📝 Enrollments
              </NavDropdown.Item>
            </NavDropdown>
            <Nav.Link as={NavLink} to="/payments" className={getNavLinkClass}>
              💳 Payments
            </Nav.Link>
          </Nav>

          <Nav className="ms-auto gap-2">
            {/* <Nav.Link as={NavLink} to="/login" className={getNavLinkClass}>
              🔑 Login
            </Nav.Link>
            <Nav.Link as={NavLink} to="/register" className={getNavLinkClass}>
              ✍️ Register
            </Nav.Link> */}
          </Nav>
        </Navbar.Collapse>
      </Container>

      {/* Custom styles */}
      <style>{`
        .custom-navbar {
          background-color: #ffffff;
          border-bottom: 1px solid #e5e5e5;
        }

        .nav-link-base {
          color: #333 !important;
          border-radius: 6px;
          transition: all 0.3s ease;
        }

        .nav-link-base:hover {
          background-color: #f8f9fa;
          color: #20c997 !important;
        }

        .nav-link-active {
          background-color: #20c997;
          color: white !important;
          border-radius: 6px;
          box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .dropdown-menu {
          border-radius: 8px;
          border: 1px solid #ddd;
        }

        .dropdown-item:hover {
          background-color: #20c997;
          color: #fff !important;
        }
      `}</style>
    </Navbar>
  );
};

export default NavigationBar;

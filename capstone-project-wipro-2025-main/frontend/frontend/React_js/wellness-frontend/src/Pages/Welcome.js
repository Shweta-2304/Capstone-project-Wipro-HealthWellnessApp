import React from "react";
import { Container } from "react-bootstrap";

const Welcome = () => {
  return (
    <div className="welcome-page d-flex flex-column min-vh-100">
      {/* Hero Section */}
      <header className="hero-section flex-grow-1 d-flex flex-column justify-content-center align-items-center text-center text-dark">
        <img
          src="https://cdn-icons-png.flaticon.com/512/2966/2966327.png"
          alt="Hospital Icon"
          className="mb-4 hero-icon"
        />
        <h1 className="fw-bold display-4">Welcome to</h1>
        <h2 className="fw-bolder text-primary mt-2">PARAS HOSPITAL</h2>
        <p className="lead mt-3 text-muted">
          Compassion. Care. Commitment.
        </p>
      </header>

      {/* Footer */}
      <footer className="footer text-center py-4">
        <Container>
          <img
            src="https://cdn-icons-png.flaticon.com/512/1483/1483336.png"
            alt="Healthcare Logo"
            width="40"
            className="mb-2"
          />
          <h6 className="fw-bold">Paras Hospital, New Delhi</h6>
          <p className="mb-1">
            📍 45 Ring Road, New Delhi, India
          </p>
          <p className="mb-1">
            📞 +91 99999 88888 | ✉️ contact@parashospital.com
          </p>
          <small className="text-muted">
            © 2025 Paras Hospital. All rights reserved.
          </small>
        </Container>
      </footer>

      {/* Custom Styles */}
      <style>{`
        .hero-section {
          background: linear-gradient(135deg, #f9f9f9, #e9f7f7);
          padding: 60px 20px;
        }
        .hero-icon {
          width: 100px;
          height: 100px;
          opacity: 0.9;
        }
        .footer {
          background: #f1f1f1;
          border-top: 1px solid #ddd;
        }
      `}</style>
    </div>
  );
};

export default Welcome;

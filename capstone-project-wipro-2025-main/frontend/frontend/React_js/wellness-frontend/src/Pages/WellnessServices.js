import React, { useState, useEffect } from "react";
import {
  getWellnessServices,
  createWellnessService,
  updateWellnessService,
  deleteWellnessService,
} from "../services/Api";
import {
  Container,
  Card,
  Form,
  Button,
  Table,
  Alert,
  Spinner,
  Collapse,
} from "react-bootstrap";

const WellnessServices = () => {
  const [services, setServices] = useState([]);
  const [form, setForm] = useState({
    serviceName: "",
    description: "",
    duration: "",
    fee: "",
  });
  const [editId, setEditId] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [showServices, setShowServices] = useState(false);

  useEffect(() => {
    fetchServices();
  }, []);

  async function fetchServices() {
    setError("");
    try {
      const response = await getWellnessServices();
      setServices(response.data);
    } catch {
      setError("Failed to load services.");
    }
  }

  function handleChange(e) {
    setForm({ ...form, [e.target.name]: e.target.value });
  }

  async function handleSubmit(e) {
    e.preventDefault();
    if (!form.serviceName || !form.description || !form.duration || !form.fee) {
      setError("All fields are required.");
      return;
    }
    setLoading(true);
    setError("");
    setSuccess("");
    const payload = {
      name: form.serviceName,
      description: form.description,
      duration: form.duration,
      fee: form.fee,
    };
    try {
      if (editId) {
        await updateWellnessService(editId, payload);
        setSuccess("Service updated successfully.");
        setEditId(null);
      } else {
        await createWellnessService(payload);
        setSuccess("Service created successfully.");
      }
      setForm({ serviceName: "", description: "", duration: "", fee: "" });
      fetchServices();
    } catch {
      setError("Failed to save service.");
    }
    setLoading(false);
  }

  function handleEdit(service) {
    setEditId(service.id);
    setForm({
      serviceName: service.name || "",
      description: service.description || "",
      duration: service.duration || "",
      fee: service.fee || "",
    });
  }

  async function handleDelete(id) {
    if (!window.confirm("Are you sure you want to delete this service?")) return;
    setLoading(true);
    setError("");
    setSuccess("");
    try {
      await deleteWellnessService(id);
      if (editId === id) setEditId(null);
      setSuccess("Service deleted successfully.");
      fetchServices();
    } catch {
      setError("Failed to delete service.");
    }
    setLoading(false);
  }

  return (
    <Container className="py-5">
      <Card
        className="shadow border-0"
        style={{
          background: "#ffffff",
          borderRadius: "16px",
          padding: "20px",
        }}
      >
        <Card.Body>
          {/* Header */}
          <div className="text-center mb-4">
            <div
              style={{
                width: "70px",
                height: "70px",
                borderRadius: "50%",
                background: "#6c63ff",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                margin: "0 auto 15px",
              }}
            >
              <span style={{ fontSize: "32px", color: "white" }}>🌿</span>
            </div>
            <h2 className="fw-bold" style={{ color: "#6c63ff" }}>
              Wellness Services
            </h2>
            <p className="text-muted">Easily manage all wellness programs</p>
          </div>

          {/* Alerts */}
          {error && <Alert variant="danger">{error}</Alert>}
          {success && <Alert variant="success">{success}</Alert>}

          {/* Form */}
          <Form onSubmit={handleSubmit} className="mb-4">
            <Form.Group className="mb-3">
              <Form.Control
                type="text"
                name="serviceName"
                placeholder="Service Name"
                value={form.serviceName}
                onChange={handleChange}
                className="rounded-pill"
                required
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Control
                type="text"
                name="description"
                placeholder="Description"
                value={form.description}
                onChange={handleChange}
                className="rounded-pill"
                required
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Control
                type="text"
                name="duration"
                placeholder="Duration (e.g., 5 days, 2 weeks)"
                value={form.duration}
                onChange={handleChange}
                className="rounded-pill"
                required
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Control
                type="number"
                step="0.01"
                name="fee"
                placeholder="Fee (e.g., 100.00)"
                value={form.fee}
                onChange={handleChange}
                className="rounded-pill"
                required
              />
            </Form.Group>
            <Button
              type="submit"
              variant="primary"
              className="w-100 rounded-pill"
              disabled={loading}
            >
              {loading ? (
                <>
                  <Spinner size="sm" animation="border" /> Saving...
                </>
              ) : editId ? (
                "Update Service"
              ) : (
                "Create Service"
              )}
            </Button>
          </Form>

          {/* Toggle Button */}
          <div className="text-center mb-3">
            <Button
              variant={showServices ? "secondary" : "outline-primary"}
              onClick={() => setShowServices((prev) => !prev)}
              className="rounded-pill px-4"
            >
              {showServices ? "Hide Services ▲" : "View Services ▼"}
            </Button>
          </div>

          {/* Services Table */}
          <Collapse in={showServices}>
            <div>
              <Table bordered hover responsive className="mt-3 shadow-sm">
                <thead style={{ background: "#6c63ff", color: "white" }}>
                  <tr>
                    <th>ID</th>
                    <th>Service Name</th>
                    <th>Description</th>
                    <th>Duration</th>
                    <th>Fee</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {services.length === 0 ? (
                    <tr>
                      <td colSpan={6} className="text-center p-4">
                        No wellness services found.
                      </td>
                    </tr>
                  ) : (
                    services.map((service, i) => (
                      <tr
                        key={service.id}
                        style={{
                          background: i % 2 === 0 ? "#f9f9f9" : "white",
                        }}
                      >
                        <td>{service.id}</td>
                        <td>{service.name}</td>
                        <td>{service.description}</td>
                        <td>{service.duration}</td>
                        <td>{service.fee}</td>
                        <td>
                          <Button
                            size="sm"
                            variant="outline-warning"
                            className="me-2 rounded-pill"
                            onClick={() => handleEdit(service)}
                            disabled={loading}
                          >
                            ✏️ Edit
                          </Button>
                          <Button
                            size="sm"
                            variant="outline-danger"
                            className="rounded-pill"
                            onClick={() => handleDelete(service.id)}
                            disabled={loading}
                          >
                            🗑️ Delete
                          </Button>
                        </td>
                      </tr>
                    ))
                  )}
                </tbody>
              </Table>
            </div>
          </Collapse>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default WellnessServices;

package com.payment.dto;


//import com.fasterxml.jackson.annotation.JsonProperty;
import com.payment.entity.Payment.PaymentStatus;

//import jakarta.persistence.Column;

import java.time.LocalDateTime;


public class PaymentDTO {
    private Long id;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
    private String transactionId;
    private Double amount;

    private PatientDTO patient;
    private AppointmentDTO appointment;
    private WellnessServiceDTO service;

    // Nested DTOs (only minimal fields we want)

    public static class PatientDTO {
        private Long id;
        private String name;
        private String email;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
    }


    public static class AppointmentDTO {
        private Long id;
        private String appointmentDate;
        private String status;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getAppointmentDate() {
			return appointmentDate;
		}
		public void setAppointmentDate(String appointmentDate) {
			this.appointmentDate = appointmentDate;
		}
    }


    public static class WellnessServiceDTO {
        private Long id;
        private String name;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public AppointmentDTO getAppointment() {
		return appointment;
	}

	public void setAppointment(AppointmentDTO appointment) {
		this.appointment = appointment;
	}

	public WellnessServiceDTO getService() {
		return service;
	}

	public void setService(WellnessServiceDTO service) {
		this.service = service;
	}
}

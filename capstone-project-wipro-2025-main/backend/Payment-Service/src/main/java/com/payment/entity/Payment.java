package com.payment.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private Long appointmentId;

    @Column(nullable = false)
    private Long serviceId;
    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)   // Saves as 'PENDING', 'SUCCESS', 'FAILED'
    @Column(nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(nullable = false, updatable = false)
    private LocalDateTime paymentDate = LocalDateTime.now();

    @Column(nullable = false, unique = true)
    private String transactionId;
    
    

    
    public enum PaymentStatus {
        PENDING,
        SUCCESS,
        FAILED
    }




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Long getPatientId() {
		return patientId;
	}




	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}




	public Long getAppointmentId() {
		return appointmentId;
	}




	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}




	public Long getServiceId() {
		return serviceId;
	}




	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}




	public Double getAmount() {
		return amount;
	}




	public void setAmount(Double amount) {
		this.amount = amount;
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
}

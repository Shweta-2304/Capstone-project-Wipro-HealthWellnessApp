package com.enrollment.dto;



import java.time.LocalDate;


public class EnrollmentDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer progress;

    private Long patientId;
    private Long serviceId;

    private PatientDTO patient;   // fetched from patient-service
    private WellnessServiceDTO service; // fetched from wellness-service

  
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

    
    public static class WellnessServiceDTO {
        private Long id;
        private String name;
        private String description;
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
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public Integer getProgress() {
		return progress;
	}


	public void setProgress(Integer progress) {
		this.progress = progress;
	}


	public Long getPatientId() {
		return patientId;
	}


	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}


	public Long getServiceId() {
		return serviceId;
	}


	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}


	public PatientDTO getPatient() {
		return patient;
	}


	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}


	public WellnessServiceDTO getService() {
		return service;
	}


	public void setService(WellnessServiceDTO service) {
		this.service = service;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
}

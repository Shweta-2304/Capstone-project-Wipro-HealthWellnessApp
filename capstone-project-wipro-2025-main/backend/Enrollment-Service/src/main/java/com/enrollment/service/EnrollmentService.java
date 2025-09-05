package com.enrollment.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.enrollment.dto.EnrollmentDTO;
import com.enrollment.entity.Enrollment;
import com.enrollment.repository.EnrollmentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;
    private final RestTemplate restTemplate;

    public EnrollmentService(EnrollmentRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public EnrollmentDTO getEnrollmentWithDetails(Long id) {
        Enrollment enrollment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        // Fetch patient details
        EnrollmentDTO.PatientDTO patient = restTemplate.getForObject(
                "http://localhost:9091/patients/" + enrollment.getPatientId(),
                EnrollmentDTO.PatientDTO.class
        );

        // Fetch service details
        EnrollmentDTO.WellnessServiceDTO service = restTemplate.getForObject(
                "http://localhost:9094/services/" + enrollment.getServiceId(),
                EnrollmentDTO.WellnessServiceDTO.class
        );

        // Convert to DTO
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getId());
        dto.setStartDate(enrollment.getStartDate());
        dto.setEndDate(enrollment.getEndDate());
        dto.setProgress(enrollment.getProgress());
        dto.setPatientId(enrollment.getPatientId());
        dto.setServiceId(enrollment.getServiceId());
        dto.setPatient(patient);
        dto.setService(service);

        return dto;
    }

    public List<EnrollmentDTO> getAllEnrollments() {
        return repository.findAll().stream()
                .map(e -> getEnrollmentWithDetails(e.getId()))
                .collect(Collectors.toList());
    }

    public EnrollmentDTO createEnrollment(EnrollmentDTO dto) {
        // Convert DTO to Entity
        Enrollment enrollment = new Enrollment();
        enrollment.setPatientId(dto.getPatientId());
        enrollment.setServiceId(dto.getServiceId());
        enrollment.setStartDate(dto.getStartDate());
        enrollment.setEndDate(dto.getEndDate());
        enrollment.setProgress(dto.getProgress());

        // Save in DB
        Enrollment saved = repository.save(enrollment);

        // Fetch external details
        EnrollmentDTO.PatientDTO patient = restTemplate.getForObject(
                "http://localhost:9091/patients/" + saved.getPatientId(),
                EnrollmentDTO.PatientDTO.class
        );

        EnrollmentDTO.WellnessServiceDTO service = restTemplate.getForObject(
                "http://localhost:9094/services/" + saved.getServiceId(),
                EnrollmentDTO.WellnessServiceDTO.class
        );

        // Convert back to DTO
        EnrollmentDTO savedDto = new EnrollmentDTO();
        savedDto.setId(saved.getId());
        savedDto.setStartDate(saved.getStartDate());
        savedDto.setEndDate(saved.getEndDate());
        savedDto.setProgress(saved.getProgress());
        savedDto.setPatientId(saved.getPatientId());
        savedDto.setServiceId(saved.getServiceId());
        savedDto.setPatient(patient);
        savedDto.setService(service);

        return savedDto;
    }

    public void deleteEnrollment(Long id) {
        Enrollment enrollment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        repository.delete(enrollment);
    }
}

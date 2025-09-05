package com.appointment.service;

import com.appointment.dto.AppointmentDTO;
import com.appointment.entity.Appointment;
import com.appointment.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;
    private final RestTemplate restTemplate;

    public AppointmentService(AppointmentRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public AppointmentDTO getAppointmentWithDetails(Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Fetch patient
        AppointmentDTO.PatientDTO patient = restTemplate.getForObject(
                "http://localhost:9091/patients/" + appointment.getPatientId(),
                AppointmentDTO.PatientDTO.class
        );

        // Fetch provider
        AppointmentDTO.ProviderDTO provider = restTemplate.getForObject(
                "http://localhost:9092/api/providers/" + appointment.getProviderId(),
                AppointmentDTO.ProviderDTO.class
        );

        // Convert to DTO
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setStatus(appointment.getStatus().name());
        dto.setNotes(appointment.getNotes());
        dto.setPatient(patient);
        dto.setProvider(provider);

        return dto;
    }

    public List<AppointmentDTO> getAllAppointments() {
        return repository.findAll().stream()
                .map(a -> getAppointmentWithDetails(a.getId()))
                .collect(Collectors.toList());
    }

    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        // Convert DTO to Entity
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setNotes(dto.getNotes());
        appointment.setStatus(Appointment.Status.valueOf(dto.getStatus()));
        appointment.setPatientId(dto.getPatient().getId());
        appointment.setProviderId(dto.getProvider().getId());

        // Save in DB
        Appointment saved = repository.save(appointment);

        // Fetch patient and provider again
        AppointmentDTO.PatientDTO patient = restTemplate.getForObject(
                "http://localhost:9091/patients/" + saved.getPatientId(),
                AppointmentDTO.PatientDTO.class
        );

        AppointmentDTO.ProviderDTO provider = restTemplate.getForObject(
                "http://localhost:9092/api/providers/" + saved.getProviderId(),
                AppointmentDTO.ProviderDTO.class
        );

        // Convert back to DTO
        AppointmentDTO savedDto = new AppointmentDTO();
        savedDto.setId(saved.getId());
        savedDto.setAppointmentDate(saved.getAppointmentDate());
        savedDto.setStatus(saved.getStatus().name());
        savedDto.setNotes(saved.getNotes());
        savedDto.setPatient(patient);
        savedDto.setProvider(provider);

        return savedDto;
    }

    public AppointmentDTO updateAppointment(Long id, AppointmentDTO dto) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Update fields
        if (dto.getAppointmentDate() != null) {
            appointment.setAppointmentDate(dto.getAppointmentDate());
        }
        if (dto.getNotes() != null) {
            appointment.setNotes(dto.getNotes());
        }
        if (dto.getStatus() != null) {
            appointment.setStatus(Appointment.Status.valueOf(dto.getStatus()));
        }
        if (dto.getPatient() != null && dto.getPatient().getId() != null) {
            appointment.setPatientId(dto.getPatient().getId());
        }
        if (dto.getProvider() != null && dto.getProvider().getId() != null) {
            appointment.setProviderId(dto.getProvider().getId());
        }

        Appointment updated = repository.save(appointment);

        // Fetch patient and provider again
        AppointmentDTO.PatientDTO patient = restTemplate.getForObject(
                "http://localhost:9091/patients/" + updated.getPatientId(),
                AppointmentDTO.PatientDTO.class
        );

        AppointmentDTO.ProviderDTO provider = restTemplate.getForObject(
                "http://localhost:9092/api/providers/" + updated.getProviderId(),
                AppointmentDTO.ProviderDTO.class
        );

        // Convert to DTO
        AppointmentDTO updatedDto = new AppointmentDTO();
        updatedDto.setId(updated.getId());
        updatedDto.setAppointmentDate(updated.getAppointmentDate());
        updatedDto.setStatus(updated.getStatus().name());
        updatedDto.setNotes(updated.getNotes());
        updatedDto.setPatient(patient);
        updatedDto.setProvider(provider);

        return updatedDto;
    }

    public void deleteAppointment(Long id) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        repository.delete(appointment);
    }
}

package com.wellness.service;

import com.wellness.dto.WellnessServiceDTO;
import com.wellness.entity.WellnessService;
import com.wellness.exception.ResourceNotFoundException;
import com.wellness.repository.WellnessServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WellnessServiceService {

    private final WellnessServiceRepository repository;

    public WellnessServiceService(WellnessServiceRepository repository) {
        this.repository = repository;
    }

    private WellnessServiceDTO mapToDTO(WellnessService entity) {
        WellnessServiceDTO dto = new WellnessServiceDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setDuration(entity.getDuration());
        dto.setFee(entity.getFee());
        return dto;
    }

    private WellnessService mapToEntity(WellnessServiceDTO dto) {
        WellnessService entity = new WellnessService();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDuration(dto.getDuration());
        entity.setFee(dto.getFee());
        return entity;
    }

    public WellnessServiceDTO createService(WellnessServiceDTO dto) {
        WellnessService saved = repository.save(mapToEntity(dto));
        return mapToDTO(saved);
    }

    public WellnessServiceDTO getService(Long id) {
        WellnessService service = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wellness Service not found with id: " + id));
        return mapToDTO(service);
    }

    public List<WellnessServiceDTO> getAllServices() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public WellnessServiceDTO updateService(Long id, WellnessServiceDTO dto) {
        WellnessService existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wellness Service not found with id: " + id));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setDuration(dto.getDuration());
        existing.setFee(dto.getFee());

        return mapToDTO(repository.save(existing));
    }

    public void deleteService(Long id) {
        WellnessService existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wellness Service not found with id: " + id));
        repository.delete(existing);
    }
}

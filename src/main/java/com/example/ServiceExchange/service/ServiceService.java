package com.example.ServiceExchange.service;

import com.example.ServiceExchange.model.Service;
import com.example.ServiceExchange.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Optional<Service> getServiceById(int id) {
        return serviceRepository.findById(id);
    }

    public Service createService(Service service) {
        return serviceRepository.save(service);
    }

    public Service updateService(int id, Service updatedService) {
        Optional<Service> existingServiceOptional = serviceRepository.findById(id);
        if (existingServiceOptional.isPresent()) {
            Service existingService = existingServiceOptional.get();
            existingService.setName(updatedService.getName());
            existingService.setDescription(updatedService.getDescription());
            return serviceRepository.save(existingService);
        } else {
            throw new RuntimeException("Service not found with id: " + id);
        }
    }

    public void deleteService(int id) {
        serviceRepository.deleteById(id);
    }
}

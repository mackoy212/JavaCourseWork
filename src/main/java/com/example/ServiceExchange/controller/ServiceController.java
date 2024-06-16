package com.example.ServiceExchange.controller;

import com.example.ServiceExchange.model.Service;
import com.example.ServiceExchange.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @PostMapping
    public Service createService(@RequestBody Service service) {
        return serviceService.createService(service);
    }

    @GetMapping("/{id}")
    public Optional<Service> getServiceById(@PathVariable int id) {
        return serviceService.getServiceById(id);
    }

    @PutMapping("/{id}")
    public Service updateService(@PathVariable int id, @RequestBody Service service) {
        return serviceService.updateService(id, service);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable int id) {
        serviceService.deleteService(id);
    }
}

package com.example.ServiceExchange;

import com.example.ServiceExchange.controller.ServiceController;
import com.example.ServiceExchange.model.Service;
import com.example.ServiceExchange.service.ServiceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ServiceControllerTest {

    @Autowired
    private ServiceController serviceController;

    @MockBean
    private ServiceService serviceService;

    @Test
    public void testGetServiceById() throws Exception {
        Service service = new Service();
        service.setId(1);
        service.setName("Test Service");
        service.setDescription("Test Description");

        when(serviceService.getServiceById(1)).thenReturn(Optional.of(service));

        Optional<Service> result = serviceController.getServiceById(1);

        assertTrue(result.isPresent());
        assertEquals("Test Service", result.get().getName());
        assertEquals("Test Description", result.get().getDescription());

        verify(serviceService, times(1)).getServiceById(1);
    }

    @Test
    public void testCreateService() throws Exception {
        Service service = new Service();
        service.setName("New Service");
        service.setDescription("New Description");

        when(serviceService.createService(service)).thenReturn(service);

        Service result = serviceController.createService(service);

        assertNotNull(result.getId());
        assertEquals("New Service", result.getName());
        assertEquals("New Description", result.getDescription());

        verify(serviceService, times(1)).createService(service);
    }

}

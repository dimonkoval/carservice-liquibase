package org.example.carservice.service.impl;

import org.example.carservice.model.Service;
import org.example.carservice.repository.ServiceRepository;
import org.example.carservice.service.ServiceService;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Service create(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Service update(Service service) {
        Service entityUpdated = serviceRepository.getById(service.getId());
        entityUpdated.setOrders(service.getOrders());
        entityUpdated.setCostService(service.getCostService());
        entityUpdated.setMasters(service.getMasters());
        entityUpdated.setStatusService(service.getStatusService());
        return serviceRepository.save(entityUpdated);
    }

    @Override
    public Service updateStatus(Service service) {
        Service entityUpdated = serviceRepository.getById(service.getId());
        entityUpdated.setStatusService(service.getStatusService());
        return serviceRepository.save(entityUpdated);
    }

    @Override
    public Service getById(Long id) {
        return serviceRepository.getById(id);
    }
}

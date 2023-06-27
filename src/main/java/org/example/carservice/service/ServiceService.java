package org.example.carservice.service;

import org.example.carservice.model.Service;

public interface ServiceService {
    Service create(Service service);

    Service update(Service service);

    Service updateStatus(Service service);

    Service getById(Long id);
}

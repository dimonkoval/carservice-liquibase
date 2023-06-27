package org.example.carservice.repository;

import org.example.carservice.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ServiceRepository extends JpaRepository<Service, Long> {
}

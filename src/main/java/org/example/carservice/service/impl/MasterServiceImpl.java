package org.example.carservice.service.impl;

import java.util.List;
import org.example.carservice.model.Master;
import org.example.carservice.model.Order;
import org.example.carservice.repository.MasterRepository;
import org.example.carservice.service.MasterService;
import org.springframework.stereotype.Service;

@Service
public class MasterServiceImpl implements MasterService {
    private final MasterRepository masterRepository;

    public MasterServiceImpl(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @Override
    public Master create(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public Master update(Master master) {
        Master entityUpdated = masterRepository.getById(master.getId());
        entityUpdated.setName(master.getName());
        entityUpdated.setOrders(master.getOrders());
        return masterRepository.save(entityUpdated);
    }

    @Override
    public List<Order> findAllOrdersByMasterId(Long masterId) {
        return masterRepository.findAllOrdersByMasterId(masterId);
    }

    @Override
    public double getSalaryOfMasterByOrder(Long masterId, Long orderId) {
        return masterRepository.getSalaryOfMasterByOrder(masterId, orderId);
    }

    @Override
    public Master getById(Long id) {
        return masterRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product by id " + id + " not found")
        );
    }

    @Override
    public List<Master> findAll() {
        return masterRepository.findAll();
    }
}

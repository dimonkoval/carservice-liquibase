package org.example.carservice.service.impl;

import java.util.List;
import org.example.carservice.model.Order;
import org.example.carservice.model.Owner;
import org.example.carservice.repository.OwnerRepository;
import org.example.carservice.service.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(Owner owner) {
        Owner entityUpdated = ownerRepository.getById(owner.getId());
        entityUpdated.setCars(owner.getCars());
        entityUpdated.setOrders(owner.getOrders());
        return ownerRepository.save(entityUpdated);
    }

    @Override
    public List<Order> findAllOrdersByOwnerId(Long id) {
        return ownerRepository.findAllOrdersByOwnerId(id);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner getById(Long id) {
        return ownerRepository.getById(id);
    }
}

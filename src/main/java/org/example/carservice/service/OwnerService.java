package org.example.carservice.service;

import java.util.List;
import org.example.carservice.model.Order;
import org.example.carservice.model.Owner;

public interface OwnerService {
    Owner create(Owner owner);

    Owner update(Owner owner);

    List<Order> findAllOrdersByOwnerId(Long id);

    List<Owner> findAll();

    Owner getById(Long id);
}

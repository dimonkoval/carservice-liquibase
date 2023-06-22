package org.example.carservice.repository;

import java.util.List;
import org.example.carservice.model.Order;
import org.example.carservice.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Order> getAllByOrders(long id);
}

package org.example.carservice.repository;

import java.util.List;
import org.example.carservice.model.Order;
import org.example.carservice.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("SELECT o FROM Owner owner JOIN owner.orders o WHERE owner.id = :ownerId")
    List<Order> findAllOrdersByOwnerId(@Param("ownerId") Long ownerId);
}

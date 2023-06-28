package org.example.carservice.repository;

import java.util.List;
import org.example.carservice.model.Master;
import org.example.carservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface MasterRepository extends JpaRepository<Master, Long> {
    @Query("SELECT o FROM Master m JOIN m.orders o WHERE m.id = :masterId")
    List<Order> findAllOrdersByMasterId(@Param("masterId") Long masterId);

    @Query("SELECT o.costTotal FROM Master m JOIN m.orders o "
            + "WHERE m.id = :masterId AND o.id = :orderId")
    double getSalaryOfMasterByOrder(@Param("masterId") Long masterId,
                                    @Param("orderId") Long orderId);

}

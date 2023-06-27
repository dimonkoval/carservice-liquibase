package org.example.carservice.service;

import java.util.List;
import org.example.carservice.model.Order;
import org.example.carservice.model.Product;

public interface OrderService {
    Order getById(Long id);

    Order create(Order order);

    Order update(Order order);

    Order updateStatus(Order order);

    double getTotalCost(Order order);

    Order addProduct(Order order, Product product);

    List<Order> findAll();

}

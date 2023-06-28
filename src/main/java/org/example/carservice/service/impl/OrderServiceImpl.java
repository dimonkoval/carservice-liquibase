package org.example.carservice.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.example.carservice.model.Order;
import org.example.carservice.model.Product;
import org.example.carservice.repository.OrderRepository;
import org.example.carservice.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        Order entityUpdated = orderRepository.getById(order.getId());
        entityUpdated.setCar(order.getCar());
        entityUpdated.setServices(order.getServices());
        entityUpdated.setCostTotal(order.getCostTotal());
        entityUpdated.setProducts(order.getProducts());
        entityUpdated.setStatusOrder(order.getStatusOrder());
        entityUpdated.setDateCompletion(order.getDateCompletion());
        entityUpdated.setDateOfAcceptance(order.getDateOfAcceptance());
        entityUpdated.setProblemDescription(order.getProblemDescription());
        return orderRepository.save(entityUpdated);
    }

    @Override
    public Order updateStatus(Order order) {
        Order entityUpdated = orderRepository.getById(order.getId());
        entityUpdated.setStatusOrder(order.getStatusOrder());
        return orderRepository.save(entityUpdated);
    }

    @Override
    public BigDecimal getTotalCost(Order order) {
        return order.getCostTotal();
    }

    @Override
    public Order addProduct(Order order, Product product) {
        Order entityUpdated = orderRepository.getById(order.getId());
        List<Product> products = entityUpdated.getProducts();
        products.add(product);
        entityUpdated.setProducts(products);
        return entityUpdated;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

}

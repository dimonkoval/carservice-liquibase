package org.example.carservice.dto.request;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.example.carservice.model.Car;
import org.example.carservice.model.Order.StatusOrder;
import org.example.carservice.model.Product;
import org.example.carservice.model.Service;

@Data
public class OrderRequestDto {
    private Car car;
    private String problemDescription;
    private LocalDateTime dateOfAcceptance;
    private LocalDateTime dateCompletion;
    private List<Service> services;
    private List<Product> products;
    private StatusOrder statusOrder;
    private double costTotal;
}

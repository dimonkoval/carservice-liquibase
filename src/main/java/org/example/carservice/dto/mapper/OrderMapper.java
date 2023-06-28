package org.example.carservice.dto.mapper;

import java.util.stream.Collectors;
import org.example.carservice.dto.request.OrderRequestDto;
import org.example.carservice.dto.response.OrderResponseDto;
import org.example.carservice.model.Order;
import org.example.carservice.model.Product;
import org.example.carservice.model.Service;
import org.example.carservice.service.CarService;
import org.example.carservice.service.ProductService;
import org.example.carservice.service.ServiceService;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements DtoMapper<Order, OrderResponseDto, OrderRequestDto> {
    private final ProductService productService;
    private final ServiceService serviceService;
    private final CarService carService;

    public OrderMapper(ProductService productService,
                       ServiceService serviceService,
                       CarService carService) {
        this.productService = productService;
        this.serviceService = serviceService;
        this.carService = carService;
    }

    @Override
    public Order toModel(OrderRequestDto requestDto) {
        Order order = new Order();
        order.setStatusOrder(requestDto.getStatusOrder());
        order.setProblemDescription(requestDto.getProblemDescription());
        order.setDateCompletion(requestDto.getDateCompletion());
        order.setDateOfAcceptance(requestDto.getDateOfAcceptance());
        order.setCar(carService.getById(requestDto.getCarId()));
        order.setCostTotal(requestDto.getCostTotal());
        order.setProducts(requestDto.getProductIds()
                .stream().map(productService::getById)
                .collect(Collectors.toList()));
        order.setServices(requestDto.getServiceIds()
                .stream()
                .map(serviceService::getById)
                .collect(Collectors.toList()));
        return order;
    }

    @Override
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setDateCompletion(order.getDateCompletion());
        responseDto.setCostTotal(order.getCostTotal());
        responseDto.setStatusOrder(order.getStatusOrder());
        responseDto.setProblemDescription(order.getProblemDescription());
        responseDto.setDateOfAcceptance(order.getDateOfAcceptance());
        responseDto.setCarId(order.getCar().getId());
        responseDto.setServiceIds(order.getServices()
                .stream()
                .map(Service::getId)
                .collect(Collectors.toList()));
        responseDto.setProductIds(order.getProducts()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}

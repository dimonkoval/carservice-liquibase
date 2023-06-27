package org.example.carservice.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;

import org.example.carservice.dto.mapper.DtoMapper;
import org.example.carservice.dto.request.OrderRequestDto;
import org.example.carservice.dto.request.ProductRequestDto;
import org.example.carservice.dto.response.OrderResponseDto;
import org.example.carservice.dto.response.ProductResponseDto;
import org.example.carservice.model.Order;
import org.example.carservice.model.Product;
import org.example.carservice.service.CarService;
import org.example.carservice.service.OrderService;
import org.example.carservice.service.ProductService;
import org.example.carservice.service.ServiceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ServiceService serviceService;
    private final ProductService productService;
    private final CarService carService;
    private final DtoMapper<Order, OrderResponseDto, OrderRequestDto> dtoMapper;
    private final DtoMapper<Product, ProductResponseDto, ProductRequestDto> productDtoMapper;

    public OrderController(OrderService orderService,
                           ServiceService serviceService, ProductService productService, CarService carService, DtoMapper<Order, OrderResponseDto, OrderRequestDto> dtoMapper,
                           DtoMapper<Product, ProductResponseDto, ProductRequestDto> productDtoMapper) {
        this.orderService = orderService;
        this.serviceService = serviceService;
        this.productService = productService;
        this.carService = carService;
        this.dtoMapper = dtoMapper;
        this.productDtoMapper = productDtoMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create new order")
    public OrderResponseDto create(@RequestBody OrderRequestDto requestDto) {
        return dtoMapper.toDto(orderService.create(dtoMapper.toModel(requestDto)));
    }

    @PutMapping("/product/{id}")
    @ApiOperation(value = "Add new product in order")
    public OrderResponseDto add(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Order order = orderService.addProduct(orderService.getById(id),
                productDtoMapper.toModel(requestDto));
        return dtoMapper.toDto(order);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update order by id")
    public OrderResponseDto update(@PathVariable Long id,
                                     @RequestBody OrderRequestDto requestDto) {
        Order order = orderService.getById(id);
        order.setCar(carService.getById(requestDto.getCarId()));
        order.setProblemDescription(requestDto.getProblemDescription());
        order.setDateCompletion(requestDto.getDateCompletion());
        order.setDateOfAcceptance(requestDto.getDateOfAcceptance());
        order.setCostTotal(requestDto.getCostTotal());
        order.setStatusOrder(requestDto.getStatusOrder());
        order.setServices(requestDto.getServiceIds()
                .stream()
                .map(serviceService::getById)
                .collect(Collectors.toList()));
        order.setProducts(requestDto.getProductIds()
                .stream()
                .map(productService::getById)
                .collect(Collectors.toList()));
        return dtoMapper.toDto(orderService.update(order));
    }

    @PutMapping("/status/{id}")
    @ApiOperation(value = "Update status of order by id")
    public OrderResponseDto updateStatus(@PathVariable Long id,
                                   @RequestBody OrderRequestDto requestDto) {
        Order order = orderService.getById(id);
        order.setStatusOrder(requestDto.getStatusOrder());
        return dtoMapper.toDto(orderService.update(order));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get order by id")
    public Order getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping("/cost/{id}")
    @ApiOperation(value = "Get total cost of order by id")
    public double getTotalCost(@PathVariable Long id) {
        return orderService.getTotalCost(orderService.getById(id));
    }

    @GetMapping
    @ApiOperation(value = "Get all orders")
    public List<Order> findAll() {
        return orderService.findAll();
    }
}

package org.example.carservice.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.example.carservice.dto.mapper.DtoMapper;
import org.example.carservice.dto.request.OwnerRequestDto;
import org.example.carservice.dto.response.OwnerResponseDto;
import org.example.carservice.model.Order;
import org.example.carservice.model.Owner;
import org.example.carservice.service.CarService;
import org.example.carservice.service.OrderService;
import org.example.carservice.service.OwnerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final OrderService orderService;
    private final CarService carService;
    private final DtoMapper<Owner, OwnerResponseDto, OwnerRequestDto> dtoMapper;

    public OwnerController(OwnerService ownerService,
                           OrderService orderService, CarService carService,
                           DtoMapper<Owner, OwnerResponseDto, OwnerRequestDto> dtoMapper) {
        this.ownerService = ownerService;
        this.orderService = orderService;
        this.carService = carService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    @ApiOperation(value = "Get all owners")
    public List<Owner> findAll() {
        return ownerService.findAll();
    }

    @PostMapping
    @ApiOperation(value = "Create new owner")
    public OwnerResponseDto create(@RequestBody OwnerRequestDto requestDto) {
        Owner owner = ownerService.create(dtoMapper.toModel(requestDto));
        return dtoMapper.toDto(owner);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update owner")
    public OwnerResponseDto update(@PathVariable Long id, @RequestBody OwnerRequestDto requestDto) {
        Owner owner = ownerService.getById(id);
        owner.setCars(requestDto.getCarIds()
                .stream()
                .map(carService::getById)
                .collect(Collectors.toList()));
        owner.setOrders(requestDto.getOrderIds()
                .stream()
                .map(orderService::getById)
                .collect(Collectors.toList()));
        return dtoMapper.toDto(ownerService.update(owner));
    }

    @GetMapping("/orders/{id}")
    @ApiOperation(value = "Get all orders of owner by id")
    public List<Order> findAllOrdersByOwnerId(@PathVariable Long id) {
        return ownerService.findAllOrdersByOwnerId(id);
    }
}

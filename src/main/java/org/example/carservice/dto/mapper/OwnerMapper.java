package org.example.carservice.dto.mapper;

import java.util.stream.Collectors;
import org.example.carservice.dto.request.OwnerRequestDto;
import org.example.carservice.dto.response.OwnerResponseDto;
import org.example.carservice.model.Car;
import org.example.carservice.model.Order;
import org.example.carservice.model.Owner;
import org.example.carservice.service.CarService;
import org.example.carservice.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper implements DtoMapper<Owner, OwnerResponseDto, OwnerRequestDto> {
    private final OrderService orderService;
    private final CarService carService;

    public OwnerMapper(OrderService orderService, CarService carService) {
        this.orderService = orderService;
        this.carService = carService;
    }

    @Override
    public Owner toModel(OwnerRequestDto requestDto) {
        Owner owner = new Owner();
        owner.setCars(requestDto.getCarIds()
                .stream()
                .map(carService::getById)
                .collect(Collectors.toList()));
        owner.setOrders(requestDto.getOrderIds()
                .stream()
                .map(orderService::getById)
                .collect(Collectors.toList()));
        return owner;
    }

    @Override
    public OwnerResponseDto toDto(Owner owner) {
        OwnerResponseDto responseDto = new OwnerResponseDto();
        responseDto.setId(owner.getId());
        responseDto.setCarIds(owner.getCars()
                .stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
        responseDto.setOrderIds(owner.getOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}

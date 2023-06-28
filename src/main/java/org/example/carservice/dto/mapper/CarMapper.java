package org.example.carservice.dto.mapper;

import org.example.carservice.dto.request.CarRequestDto;
import org.example.carservice.dto.response.CarResponseDto;
import org.example.carservice.model.Car;
import org.example.carservice.service.OwnerService;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements DtoMapper<Car, CarResponseDto, CarRequestDto> {
    private final OwnerService ownerService;

    public CarMapper(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public Car toModel(CarRequestDto requestDto) {
        Car car = new Car();
        car.setModel(requestDto.getModel());
        car.setOwner(ownerService.getById(requestDto.getOwnerId()));
        car.setNumber(requestDto.getNumber());
        car.setYearOfIssue(requestDto.getYearOfIssue());
        car.setName(requestDto.getName());
        return car;
    }

    @Override
    public CarResponseDto toDto(Car car) {
        CarResponseDto responseDto = new CarResponseDto();
        responseDto.setId(car.getId());
        responseDto.setModel(car.getModel());
        responseDto.setOwnerId(car.getOwner().getId());
        responseDto.setNumber(car.getNumber());
        responseDto.setYearOfIssue(car.getYearOfIssue());
        responseDto.setName(car.getName());
        return responseDto;
    }
}

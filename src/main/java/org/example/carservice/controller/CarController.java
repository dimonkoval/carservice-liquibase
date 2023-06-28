package org.example.carservice.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.example.carservice.dto.mapper.DtoMapper;
import org.example.carservice.dto.request.CarRequestDto;
import org.example.carservice.dto.response.CarResponseDto;
import org.example.carservice.model.Car;
import org.example.carservice.service.CarService;
import org.example.carservice.service.OwnerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final OwnerService ownerService;
    private final DtoMapper<Car, CarResponseDto, CarRequestDto> dtoMapper;

    public CarController(CarService carService,
                         OwnerService ownerService,
                         DtoMapper<Car, CarResponseDto, CarRequestDto> dtoMapper) {
        this.carService = carService;
        this.ownerService = ownerService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create new car")
    public CarResponseDto create(@RequestBody CarRequestDto requestDto) {
        return dtoMapper.toDto(carService.create(dtoMapper.toModel(requestDto)));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update car by id")
    public CarResponseDto update(@PathVariable Long id,
                                     @RequestBody CarRequestDto requestDto) {
        Car car = carService.getById(id);
        car.setName(requestDto.getName());
        car.setModel(requestDto.getModel());
        car.setOwner(ownerService.getById(requestDto.getOwnerId()));
        car.setNumber(requestDto.getNumber());
        car.setYearOfIssue(requestDto.getYearOfIssue());
        return dtoMapper.toDto(carService.update(car));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get car by id")
    public CarResponseDto getById(@PathVariable Long id) {
        return dtoMapper.toDto(carService.getById(id));
    }

    @GetMapping
    @ApiOperation(value = "Get all cars")
    public List<CarResponseDto> findAll() {
        return carService.findAll()
                .stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

}

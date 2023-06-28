package org.example.carservice.controller;

import io.swagger.annotations.ApiOperation;
import java.util.stream.Collectors;
import org.example.carservice.dto.mapper.DtoMapper;
import org.example.carservice.dto.request.ServiceRequestDto;
import org.example.carservice.dto.response.ServiceResponseDto;
import org.example.carservice.model.Service;
import org.example.carservice.service.MasterService;
import org.example.carservice.service.OrderService;
import org.example.carservice.service.ServiceService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;
    private final MasterService masterService;
    private final OrderService orderService;
    private final DtoMapper<Service, ServiceResponseDto, ServiceRequestDto> dtoMapper;

    public ServiceController(ServiceService serviceService,
                             MasterService masterService, OrderService orderService,
                             DtoMapper<Service, ServiceResponseDto, ServiceRequestDto> dtoMapper) {
        this.serviceService = serviceService;
        this.masterService = masterService;
        this.orderService = orderService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create new service")
    public ServiceResponseDto create(@RequestBody ServiceRequestDto requestDto) {
        return dtoMapper.toDto(serviceService.create(dtoMapper.toModel(requestDto)));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update service by id")
    public ServiceResponseDto update(@PathVariable Long id,
                                     @RequestBody ServiceRequestDto requestDto) {
        Service entityUpdated = serviceService.getById(id);
        entityUpdated.setCostService(requestDto.getCostService());
        entityUpdated.setStatusService(requestDto.getStatusService());
        entityUpdated.setOrders(requestDto.getOrderIds()
                .stream()
                .map(orderService::getById)
                .collect(Collectors.toList()));
        entityUpdated.setMasters(requestDto.getMasterIds()
                .stream()
                .map(masterService::getById)
                .collect(Collectors.toList()));
        return dtoMapper.toDto(serviceService.update(entityUpdated));
    }

    @PutMapping("/status/{id}")
    @ApiOperation(value = "Update status of service by id")
    public ServiceResponseDto updateStatus(@PathVariable Long id,
                                           @RequestBody ServiceRequestDto requestDto) {
        Service entityUpdated = serviceService.getById(id);
        entityUpdated.setStatusService(requestDto.getStatusService());
        return dtoMapper.toDto(serviceService.update(entityUpdated));
    }
}

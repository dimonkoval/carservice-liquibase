package org.example.carservice.dto.mapper;

import java.util.stream.Collectors;
import org.example.carservice.dto.request.ServiceRequestDto;
import org.example.carservice.dto.response.ServiceResponseDto;
import org.example.carservice.model.Master;
import org.example.carservice.model.Order;
import org.example.carservice.model.Service;
import org.example.carservice.service.MasterService;
import org.example.carservice.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper implements DtoMapper<Service, ServiceResponseDto, ServiceRequestDto> {
    private final MasterService masterService;
    private final OrderService orderService;

    public ServiceMapper(MasterService masterService, OrderService orderService) {
        this.masterService = masterService;
        this.orderService = orderService;
    }

    @Override
    public Service toModel(ServiceRequestDto requestDto) {
        Service service = new Service();
        service.setStatusService(requestDto.getStatusService());
        service.setCostService(requestDto.getCostService());
        service.setMasters(requestDto.getMasterIds()
                .stream()
                .map(masterService::getById)
                .collect(Collectors.toList()));
        service.setOrders(requestDto.getOrderIds()
                .stream()
                .map(orderService::getById)
                .collect(Collectors.toList()));
        return service;
    }

    @Override
    public ServiceResponseDto toDto(Service service) {
        ServiceResponseDto responseDto = new ServiceResponseDto();
        responseDto.setId(service.getId());
        responseDto.setStatusService(service.getStatusService());
        responseDto.setCostService(service.getCostService());
        responseDto.setMasterIds(service.getMasters()
                .stream()
                .map(Master::getId)
                .collect(Collectors.toList()));
        responseDto.setOrderIds(service.getOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}

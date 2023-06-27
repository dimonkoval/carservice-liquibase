package org.example.carservice.dto.mapper;

import org.example.carservice.dto.request.MasterRequestDto;
import org.example.carservice.dto.response.MasterResponseDto;
import org.example.carservice.model.Master;
import org.example.carservice.model.Order;
import org.example.carservice.service.MasterService;
import org.example.carservice.service.OrderService;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class MasterMapper implements DtoMapper<Master, MasterResponseDto, MasterRequestDto>{
    private final MasterService masterService;
    private final OrderService orderService;

    public MasterMapper(MasterService masterService, OrderService orderService) {
        this.masterService = masterService;
        this.orderService = orderService;
    }

    @Override
    public Master toModel(MasterRequestDto requestDto) {
        Master master = new Master();
        master.setName(requestDto.getName());
        master.setOrders(requestDto.getOrderIds()
                .stream()
                .map(orderService::getById)
                .collect(Collectors.toList()));
        return master;
    }

    @Override
    public MasterResponseDto toDto(Master master) {
        MasterResponseDto responseDto = new MasterResponseDto();
        responseDto.setId(master.getId());
        responseDto.setName(master.getName());
        responseDto.setOrderIds(master.getOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}

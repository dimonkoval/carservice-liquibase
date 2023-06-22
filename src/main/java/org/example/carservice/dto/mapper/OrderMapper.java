package org.example.carservice.dto.mapper;

import org.example.carservice.dto.request.OrderRequestDto;
import org.example.carservice.dto.response.OrderResponseDto;
import org.example.carservice.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements DtoMapper<Order, OrderResponseDto, OrderRequestDto> {

    @Override
    public Order toModel(OrderRequestDto requestDto) {
        Order order = new Order();
        order.setProducts(requestDto.getProducts());
        order.setStatusOrder(requestDto.getStatusOrder());
        order.setProblemDescription(requestDto.getProblemDescription());
        order.setDateCompletion(requestDto.getDateCompletion());
        order.setDateOfAcceptance(requestDto.getDateOfAcceptance());
        order.setCar(requestDto.getCar());
        order.setServices(requestDto.getServices());
        order.setCostTotal(requestDto.getCostTotal());
        return order;
    }

    @Override
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setServices(order.getServices());
        responseDto.setCar(order.getCar());
        responseDto.setProducts(order.getProducts());
        responseDto.setDateCompletion(order.getDateCompletion());
        responseDto.setCostTotal(order.getCostTotal());
        responseDto.setStatusOrder(order.getStatusOrder());
        responseDto.setProblemDescription(order.getProblemDescription());
        responseDto.setDateOfAcceptance(order.getDateOfAcceptance());
        return responseDto;
    }
}

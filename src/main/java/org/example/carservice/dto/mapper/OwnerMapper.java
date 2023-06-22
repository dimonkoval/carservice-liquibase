package org.example.carservice.dto.mapper;

import org.example.carservice.dto.request.OwnerRequestDto;
import org.example.carservice.dto.response.OwnerResponseDto;
import org.example.carservice.model.Owner;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper implements DtoMapper<Owner, OwnerResponseDto, OwnerRequestDto> {
    @Override
    public Owner toModel(OwnerRequestDto requestDto) {
        Owner owner = new Owner();
        owner.setOrders(requestDto.getOrders());
        owner.setCars(requestDto.getCars());
        return owner;
    }

    @Override
    public OwnerResponseDto toDto(Owner owner) {
        OwnerResponseDto responseDto = new OwnerResponseDto();
        responseDto.setId(owner.getId());
        responseDto.setOrders(owner.getOrders());
        responseDto.setCars(owner.getCars());
        return responseDto;
    }
}

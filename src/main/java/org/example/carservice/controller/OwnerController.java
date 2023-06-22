package org.example.carservice.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.example.carservice.dto.mapper.DtoMapper;
import org.example.carservice.dto.request.OwnerRequestDto;
import org.example.carservice.dto.response.OwnerResponseDto;
import org.example.carservice.model.Order;
import org.example.carservice.model.Owner;
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
    private final DtoMapper<Owner, OwnerResponseDto, OwnerRequestDto> dtoMapper;

    public OwnerController(OwnerService ownerService,
                           DtoMapper<Owner, OwnerResponseDto, OwnerRequestDto> dtoMapper) {
        this.ownerService = ownerService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping
    @ApiOperation(value = "Get all owners")
    public List<Owner> findAll() {
        return ownerService.findAll();
    }

    @PostMapping
    @ApiOperation(value = "Create new owner")
    public Owner create(@RequestBody OwnerRequestDto requestDto){
        return ownerService.create(dtoMapper.toModel(requestDto));
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "Update owner")
    public OwnerResponseDto update(@PathVariable Long id, @RequestBody OwnerRequestDto requestDto) {
        Owner owner = ownerService.getById(id);
        owner.setCars(requestDto.getCars());
        owner.setOrders(requestDto.getOrders());
        return dtoMapper.toDto(ownerService.update(owner));
    }

    @GetMapping("/orders/{id}")
    @ApiOperation(value = "Get all orders of owner by id")
    public List<Order> getAllByOrders(@PathVariable Long id) {
        return ownerService.getAllByOrders(id);
    }

}

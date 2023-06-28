package org.example.carservice.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.example.carservice.dto.mapper.DtoMapper;
import org.example.carservice.dto.request.MasterRequestDto;
import org.example.carservice.dto.response.MasterResponseDto;
import org.example.carservice.model.Master;
import org.example.carservice.model.Order;
import org.example.carservice.service.MasterService;
import org.example.carservice.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/masters")
public class MasterController {
    private final MasterService masterService;
    private final OrderService orderService;
    private final DtoMapper<Master, MasterResponseDto, MasterRequestDto> dtoMapper;

    public MasterController(MasterService masterService,
                            OrderService orderService,
                            DtoMapper<Master, MasterResponseDto, MasterRequestDto> dtoMapper) {
        this.masterService = masterService;
        this.orderService = orderService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/orders/{id}")
    @ApiOperation(value = "Get all orders by master")
    public List<Order> findAllOrdersByMaster(@PathVariable Long id) {
        return masterService.findAllOrdersByMasterId(id);
    }

    @GetMapping("/salary")
    @ApiOperation(value = "Get salary of master by order")
    public double getSalaryOfMasterByOrder(@RequestParam Long masterId,
                                           @RequestParam Long orderId) {
        return masterService.getSalaryOfMasterByOrder(masterId, orderId);
    }

    @PostMapping
    @ApiOperation(value = "Create new master")
    public MasterResponseDto create(@RequestBody MasterRequestDto requestDto) {
        Master master = masterService.create(dtoMapper.toModel(requestDto));
        return dtoMapper.toDto(master);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update master by id")
    public MasterResponseDto update(@PathVariable Long id,
                         @RequestBody MasterRequestDto requestDto) {
        Master master = masterService.getById(id);
        master.setName(requestDto.getName());
        master.setOrders(requestDto.getOrderIds()
                .stream()
                .map(orderService::getById)
                .collect(Collectors.toList()));
        return dtoMapper.toDto(masterService.update(master));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get master by id")
    public Master getById(@PathVariable Long id) {
        return masterService.getById(id);
    }

    @GetMapping
    @ApiOperation(value = "Get all masters")
    public List<Master> findAll() {
        return masterService.findAll();
    }
}

package org.example.carservice.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.example.carservice.dto.mapper.DtoMapper;
import org.example.carservice.dto.request.ProductRequestDto;
import org.example.carservice.dto.response.ProductResponseDto;
import org.example.carservice.model.Product;
import org.example.carservice.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final DtoMapper<Product, ProductResponseDto, ProductRequestDto> dtoMapper;

    public ProductController(ProductService productService,
                             DtoMapper<Product, ProductResponseDto, ProductRequestDto> dtoMapper) {
        this.productService = productService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create new product")
    public ProductResponseDto create(@RequestBody ProductRequestDto requestDto) {
        return dtoMapper.toDto(productService.create(dtoMapper.toModel(requestDto)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get product by id")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete product")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update product by id")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productService.getById(id);
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        return dtoMapper.toDto(productService.update(product));
    }

    @GetMapping
    @ApiOperation(value = "Get all products with pagination and sort")
    public List<ProductResponseDto> getAll(
            @RequestParam(defaultValue = "10")
            @ApiParam(value = "default value is '10'") Integer count,
            @RequestParam(defaultValue = "0")
            @ApiParam(value = "default value is '0'") Integer page,
            @RequestParam(defaultValue = "id")
            @ApiParam(value = "default value is 'id'") String sortBy) {

        return productService.findAll(page, count, sortBy)
                .stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/price-between")
    @ApiOperation(value = "Get all products between price from-to "
            + "and sorting on [parameter]:[ASC|DESC]")
    public List<ProductResponseDto> findAllPriceBetween(
            @RequestParam(defaultValue = "0")
            @ApiParam(value = "default value is '0'") BigDecimal from,
            @RequestParam(defaultValue = "10000")
            @ApiParam(value = "default value is '10000'") BigDecimal to,
            @RequestParam(defaultValue = "10")
            @ApiParam(value = "default value is '10'") Integer count,
            @RequestParam(defaultValue = "0")
            @ApiParam(value = "default value is '0'") Integer page,
            @RequestParam(defaultValue = "id")
            @ApiParam(value = "default value is 'id'") String sortBy) {

        return productService.findAllByPriceBetween(from, to, page, count, sortBy)
                .stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());
    }
}

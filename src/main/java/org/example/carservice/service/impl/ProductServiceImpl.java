package org.example.carservice.service.impl;

import static org.example.carservice.util.Sorting.getSortFromRequestParam;

import java.math.BigDecimal;
import java.util.List;
import org.example.carservice.model.Product;
import org.example.carservice.repository.ProductRepository;
import org.example.carservice.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll(Integer page, Integer count, String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, count, getSortFromRequestParam(sortBy));
        return productRepository.findAll(pageRequest).toList();
    }

    @Override
    public List<Product> findAllByPriceBetween(BigDecimal from, BigDecimal to,
                                               Integer page, Integer count,
                                               String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, count, getSortFromRequestParam(sortBy));
        return productRepository.findAllByPriceBetween(from, to, pageRequest);
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product by id " + id + " not found")
        );
    }

    @Override
    public Product update(Product product) {
        Product productForUpdate = productRepository.getById(product.getId());
        productForUpdate.setTitle(product.getTitle());
        productForUpdate.setPrice(product.getPrice());
        return productRepository.save(productForUpdate);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}

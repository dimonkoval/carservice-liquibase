package org.example.carservice.service;

import java.util.List;
import org.example.carservice.model.Car;

public interface CarService {
    Car getById(Long id);

    Car create(Car car);

    Car update(Car car);

    List<Car> findAll();
}

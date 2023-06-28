package org.example.carservice.service.impl;

import java.util.List;
import org.example.carservice.model.Car;
import org.example.carservice.repository.CarRepository;
import org.example.carservice.service.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car getById(Long id) {
        return carRepository.getById(id);
    }

    @Override
    public Car create(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car) {
        Car entityUpdated = carRepository.getById(car.getId());
        entityUpdated.setName(car.getName());
        entityUpdated.setModel(car.getModel());
        entityUpdated.setOwner(car.getOwner());
        entityUpdated.setNumber(car.getNumber());
        entityUpdated.setYearOfIssue(car.getYearOfIssue());
        carRepository.save(entityUpdated);
        return entityUpdated;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }
}

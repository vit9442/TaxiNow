package org.top.taxinow.service;

import org.springframework.stereotype.Service;
import org.top.taxinow.entity.Car;

import java.util.Optional;

@Service
public interface CarService {

    Iterable<Car> findAll();

    Optional<Car> findById(Integer id);

    Optional<Car> deleteById(Integer id);

    Optional<Car> add(Car car);

    Optional<Car> updateById(Integer id, Car car);


}

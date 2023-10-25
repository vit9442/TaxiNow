package org.top.taxinow.service;

import org.springframework.stereotype.Service;
import org.top.taxinow.entity.Car;
import org.top.taxinow.entity.Driver;

import java.util.Optional;

@Service
public interface DriverService {
    Iterable<Driver> findAll();

    Optional<Driver> findById(Integer id);

    Optional<Driver> deleteById(Integer id);

    Optional<Driver> add(Driver driver);

    Optional<Driver> updateById(Integer id, Driver driver);
}

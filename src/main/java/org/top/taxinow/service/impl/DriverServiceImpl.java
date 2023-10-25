package org.top.taxinow.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.top.taxinow.entity.Car;
import org.top.taxinow.entity.Driver;
import org.top.taxinow.repository.DriverRepository;
import org.top.taxinow.service.DriverService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    @Override
    public Iterable<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        return driverRepository.findById(id);
    }

    @Override
    public Optional<Driver> deleteById(Integer id) {
        Optional<Driver> removableDriver = findById(id);
        if(findById(id).isPresent()){
            driverRepository.deleteById(id);
        }
        driverRepository.deleteById(id);
        return removableDriver;

    }

    @Override
    public Optional<Driver> add(Driver driver) {
        return Optional.of(driverRepository.save(driver));
    }

    @Override
    public Optional<Driver> updateById(Integer id, Driver driver) {
        Optional<Driver> driverUpdated = findById(id);

        if(driverUpdated.isPresent()){
            driver.setId(id);
            return Optional.of(driverRepository.save(driver));
        } else {
            return Optional.empty();
        }
    }
}

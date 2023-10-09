package org.top.taxinow.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.top.taxinow.entity.Car;
import org.top.taxinow.repository.CarRepository;
import org.top.taxinow.service.CarService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return carRepository.findById(id);
    }

    @Override
    public Optional<Car> deleteById(Integer id) {
        Optional<Car> removableCar = findById(id);
        if(findById(id).isPresent()){
             carRepository.deleteById(id);
        }
     carRepository.deleteById(id);
     return removableCar;
    }

    @Override
    public Optional<Car> add(Car car) {
        if(carRepository.findByRegistrationPlate(car.getRegistrationPlate()).isPresent()){
            return Optional.empty();
        }
        return Optional.of(carRepository.save(car));
    }

    @Override
    public Optional<Car> updateById(Integer id, Car car) {
         Optional<Car> carUpdated = findById(id);
         Optional<Car> duplicatedByRegistrationPlate = carRepository.findByRegistrationPlate(car.getRegistrationPlate());
         if(carUpdated.isPresent() &&
                 (duplicatedByRegistrationPlate.isEmpty()) || duplicatedByRegistrationPlate.get().getId() == car.getId()){
             car.setId(id);
             return Optional.of(carRepository.save(car));
         } else {
             return Optional.empty();
         }
    }
}

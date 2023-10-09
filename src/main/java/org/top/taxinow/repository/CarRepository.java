package org.top.taxinow.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.taxinow.entity.Car;

import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {

    Optional<Car> findByRegistrationPlate(String registrationPlate);
}

package org.top.taxinow.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.taxinow.entity.TaxiOrder;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaxiOrderRepository extends CrudRepository<TaxiOrder, Integer> {

}

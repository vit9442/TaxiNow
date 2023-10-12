package org.top.taxinow.service;

import org.springframework.stereotype.Service;
import org.top.taxinow.entity.Car;
import org.top.taxinow.entity.Driver;
import org.top.taxinow.entity.TaxiOrder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public interface TaxiOrderService {
    Iterable<TaxiOrder> findAll();

    Optional<TaxiOrder> findById(Integer id);

    Optional<TaxiOrder> deleteById(Integer  id);

    Optional<TaxiOrder> add(TaxiOrder taxiOrder, LocalDateTime time);
    Optional<TaxiOrder> updateById(Integer id, TaxiOrder taxiOrder);
     void checkOrders();
}

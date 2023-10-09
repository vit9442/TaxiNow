package org.top.taxinow.service;

import org.springframework.stereotype.Service;
import org.top.taxinow.entity.Driver;
import org.top.taxinow.entity.TaxiOrder;

import java.util.Optional;
import java.util.UUID;

@Service
public interface TaxiOrderService {
    Iterable<TaxiOrder> findAll();

    Optional<TaxiOrder> findById(UUID id);

    void deleteById(UUID  id);

    TaxiOrder add(TaxiOrder taxiOrder);
}

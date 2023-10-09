package org.top.taxinow.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.top.taxinow.entity.Driver;
import org.top.taxinow.entity.TaxiOrder;
import org.top.taxinow.repository.TaxiOrderRepository;
import org.top.taxinow.service.TaxiOrderService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaxiOrderServiceImpl implements TaxiOrderService {

    private final TaxiOrderRepository taxiOrderRepository;

    @Override
    public Iterable<TaxiOrder> findAll() {
        return taxiOrderRepository.findAll();
    }

    @Override
    public Optional<TaxiOrder> findById(UUID id) {
        return taxiOrderRepository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        taxiOrderRepository.deleteById(id);

    }

    @Override
    public TaxiOrder add(TaxiOrder taxiOrder) {
        return taxiOrderRepository.save(taxiOrder);
    }
}

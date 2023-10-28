package org.top.taxinow.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.top.taxinow.entity.Driver;
import org.top.taxinow.entity.TaxiOrder;
import org.top.taxinow.repository.TaxiOrderRepository;
import org.top.taxinow.service.TaxiOrderService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TaxiOrderServiceImpl implements TaxiOrderService {

    private final TaxiOrderRepository taxiOrderRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public Iterable<TaxiOrder> findAll() {
        return taxiOrderRepository.findAll();
    }

    @Override
    public Optional<TaxiOrder> findById(Integer id) {
        return taxiOrderRepository.findById(id);
    }

    @Override
    public Optional<TaxiOrder> deleteById(Integer id) {
        Optional<TaxiOrder> removableTaxiOrder = findById(id);
        if(findById(id).isPresent()){
            taxiOrderRepository.deleteById(id);
        }
        taxiOrderRepository.deleteById(id);
        return removableTaxiOrder;

    }

    @Override
    public Optional<TaxiOrder> add(TaxiOrder taxiOrder, LocalDateTime time) {
        if (taxiOrder.getDriver().getTaxiOrder() == null && (ChronoUnit.MINUTES.between(LocalDateTime.now(), time) > 10)) {
            taxiOrder.setTime(time.format(formatter));
            return Optional.of(taxiOrderRepository.save(taxiOrder));
        }

        return null;
    }

    @Override
    public Optional<TaxiOrder> updateById(Integer id, TaxiOrder taxiOrder) {
        Optional<TaxiOrder> taxiOrderUpdated = findById(id);

        if(taxiOrderUpdated.isPresent()){
            taxiOrder.setId(id);
            return Optional.of(taxiOrderRepository.save(taxiOrder));
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
    public void checkOrders() {
        if (taxiOrderRepository.findAll().iterator().hasNext()) {
            for (TaxiOrder order : taxiOrderRepository.findAll()) {
                LocalDateTime orderTime = LocalDateTime.parse(order.getTime(), formatter);
                if (ChronoUnit.MINUTES.between(orderTime, LocalDateTime.now()) > 60) {
                    taxiOrderRepository.deleteById(order.getId());
                }
            }
        }
    }
}

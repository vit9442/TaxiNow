package org.top.taxinow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "taxi_order_t")
@Data
public class TaxiOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "initial_adress_f", nullable = false)
    private String initialAdress;

    @Column(name = "final_adress_f", nullable = false)
    private String finalAdress;

    @Column(name = "price_f", nullable = false)
    private Double price;

    @Column(name = "time_F", nullable = false)
    private String time;

    @OneToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Driver driver;

}

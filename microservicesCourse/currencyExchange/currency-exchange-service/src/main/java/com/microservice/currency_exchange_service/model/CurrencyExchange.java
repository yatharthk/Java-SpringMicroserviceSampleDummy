package com.microservice.currency_exchange_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrencyExchange {

    @Id
    private Long id;
    @Column(name="from_currency")
    private String from;
    @Column(name="to_currency")
    private String to;
    @Column
    private BigDecimal conversionMultiple;
    @Transient
    private String environment;
}

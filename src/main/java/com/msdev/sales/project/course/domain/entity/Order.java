package com.msdev.sales.project.course.domain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Order {

    private Integer id;
    private Client client;
    private LocalDate orderDate;
    private BigDecimal total;

}

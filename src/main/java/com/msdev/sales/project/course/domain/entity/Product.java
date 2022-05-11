package com.msdev.sales.project.course.domain.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private Integer id;
    private String description;
    private BigDecimal price;

}

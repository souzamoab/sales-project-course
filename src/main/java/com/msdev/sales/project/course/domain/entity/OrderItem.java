package com.msdev.sales.project.course.domain.entity;

import lombok.Data;

@Data
public class OrderItem {

    private Integer id;
    private Order order;
    private Product product;
    private Integer quantity;

}

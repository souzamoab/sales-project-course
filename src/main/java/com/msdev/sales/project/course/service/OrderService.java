package com.msdev.sales.project.course.service;

import com.msdev.sales.project.course.api.controller.dto.OrderDTO;
import com.msdev.sales.project.course.domain.entity.Order;
import com.msdev.sales.project.course.domain.enums.OrderStatus;

import java.util.Optional;

public interface OrderService {

    Order save(OrderDTO orderDTO);
    Optional<Order> getOrderData(Integer id);
    void updateStatus(Integer id, OrderStatus orderStatus);

}

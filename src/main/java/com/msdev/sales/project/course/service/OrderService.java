package com.msdev.sales.project.course.service;

import com.msdev.sales.project.course.api.controller.dto.OrderDTO;
import com.msdev.sales.project.course.domain.entity.Order;

public interface OrderService {

    Order save(OrderDTO orderDTO);

}

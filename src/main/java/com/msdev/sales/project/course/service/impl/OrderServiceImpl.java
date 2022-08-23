package com.msdev.sales.project.course.service.impl;

import com.msdev.sales.project.course.domain.repository.OrderRepository;
import com.msdev.sales.project.course.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;



}

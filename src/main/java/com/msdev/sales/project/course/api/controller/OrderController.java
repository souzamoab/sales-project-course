package com.msdev.sales.project.course.api.controller;

import com.msdev.sales.project.course.api.controller.dto.OrderDTO;
import com.msdev.sales.project.course.domain.entity.Order;
import com.msdev.sales.project.course.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.save(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order.getId());
    }

}

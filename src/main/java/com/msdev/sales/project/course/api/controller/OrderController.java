package com.msdev.sales.project.course.api.controller;

import com.msdev.sales.project.course.api.controller.dto.OrderDTO;
import com.msdev.sales.project.course.api.controller.dto.OrderDataDTO;
import com.msdev.sales.project.course.api.controller.dto.OrderItemDataDTO;
import com.msdev.sales.project.course.api.controller.dto.UpdateStatusOrderDTO;
import com.msdev.sales.project.course.domain.entity.Order;
import com.msdev.sales.project.course.domain.entity.OrderItem;
import com.msdev.sales.project.course.domain.enums.OrderStatus;
import com.msdev.sales.project.course.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.save(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order.getId());
    }

    @GetMapping("{id}")
    public OrderDataDTO getOrderData(@PathVariable Integer id) {
        return orderService.getOrderData(id)
                .map(order -> convert(order))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
                             @RequestBody UpdateStatusOrderDTO updateStatusOrderDTO) {
        String updateStatus = updateStatusOrderDTO.getStatusUpdated();
        orderService.updateStatus(id, OrderStatus.valueOf(updateStatus));
    }

    private OrderDataDTO convert(Order order) {
        return OrderDataDTO.builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(order.getClient().getCpf())
                .clientName(order.getClient().getName())
                .total(order.getTotal())
                .orderStatus(order.getOrderStatus().name())
                .items(convert(order.getItems()))
                .build();
    }

    private List<OrderItemDataDTO> convert(List<OrderItem> items) {
        if(CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items.stream().map(item -> OrderItemDataDTO.builder()
                        .productDescription(item.getProduct().getDescription())
                        .unitPrice(item.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

}

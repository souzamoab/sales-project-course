package com.msdev.sales.project.course.service.impl;

import com.msdev.sales.project.course.api.controller.dto.OrderDTO;
import com.msdev.sales.project.course.api.controller.dto.OrderItemDTO;
import com.msdev.sales.project.course.domain.entity.Client;
import com.msdev.sales.project.course.domain.entity.Order;
import com.msdev.sales.project.course.domain.entity.OrderItem;
import com.msdev.sales.project.course.domain.entity.Product;
import com.msdev.sales.project.course.domain.enums.OrderStatus;
import com.msdev.sales.project.course.domain.repository.ClientRepository;
import com.msdev.sales.project.course.domain.repository.OrderItemRepository;
import com.msdev.sales.project.course.domain.repository.OrderRepository;
import com.msdev.sales.project.course.domain.repository.ProductRepository;
import com.msdev.sales.project.course.exception.BusinessRuleException;
import com.msdev.sales.project.course.exception.OrderNotFoundException;
import com.msdev.sales.project.course.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Order save(OrderDTO orderDTO) {
        Client client = clientRepository.findById(orderDTO.getClientId()).orElseThrow(() -> new BusinessRuleException("Invalid client id"));

        Order order = new Order();
        order.setTotal(orderDTO.getTotal());
        order.setOrderDate(LocalDate.now());
        order.setClient(client);
        order.setOrderStatus(OrderStatus.DONE);

        List<OrderItem> items = convertItems(order, orderDTO.getItems());
        orderRepository.save(order);
        orderItemRepository.saveAll(items);

        order.setItems(items);

        return order;
    }

    @Override
    public Optional<Order> getOrderData(Integer id) {
        return orderRepository.findByIdFetchItems(id);
    }

    @Override
    @Transactional
    public void updateStatus(Integer id, OrderStatus orderStatus) {
        orderRepository.findById(id)
                .map(order -> {
                    order.setOrderStatus(orderStatus);
                    return orderRepository.save(order);
                }).orElseThrow(() -> new OrderNotFoundException("Order not found."));
    }

    private List<OrderItem> convertItems(Order order, List<OrderItemDTO> items) {
        if(items.isEmpty()) {
            throw new BusinessRuleException("It is not possible to place an order without items");
        }

        return items.stream().map(dto -> {
            Product product = productRepository.findById(dto.getProductId()).orElseThrow(() -> new BusinessRuleException("Invalid product id: " + dto.getProductId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(dto.getQuantity());
            orderItem.setOrder(order);
            orderItem.setProduct(product);

            return orderItem;
        }).collect(Collectors.toList());

    }

}

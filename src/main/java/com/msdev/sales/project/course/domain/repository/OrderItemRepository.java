package com.msdev.sales.project.course.domain.repository;

import com.msdev.sales.project.course.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}

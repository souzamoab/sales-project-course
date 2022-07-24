package com.msdev.sales.project.course.domain.repository;

import com.msdev.sales.project.course.domain.entity.Client;
import com.msdev.sales.project.course.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByClient(Client client);

}

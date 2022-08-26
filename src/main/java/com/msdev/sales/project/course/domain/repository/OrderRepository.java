package com.msdev.sales.project.course.domain.repository;

import com.msdev.sales.project.course.domain.entity.Client;
import com.msdev.sales.project.course.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByClient(Client client);

    @Query(" select ord from Order ord left join fetch ord.items where ord.id = :id ")
    Optional<Order> findByIdFetchItems(Integer id);

}

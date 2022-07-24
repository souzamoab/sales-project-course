package com.msdev.sales.project.course;

import com.msdev.sales.project.course.domain.entity.Client;
import com.msdev.sales.project.course.domain.entity.Order;
import com.msdev.sales.project.course.domain.repository.ClientRepository;
import com.msdev.sales.project.course.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SalesProjectCourseApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientRepository clientRepository, @Autowired OrderRepository orderRepository) {
        return args -> {
            System.out.println("Saving");
            Client c1 = new Client("Moab");
            clientRepository.save(c1);

            boolean exists = clientRepository.existsByName("Moab");
            System.out.println("Have a client with name Moab? " + exists);

            Order order1 = new Order();
            order1.setClient(c1);
            order1.setOrderDate(LocalDate.now());
            order1.setTotal(BigDecimal.valueOf(100));

            orderRepository.save(order1);

            //Client c1Find = clientRepository.findClientFetchOrders(c1.getId());
            //System.out.println("Client 1 : " + c1Find);
            //System.out.println("Client 1 Orders : " + c1Find.getOrders());

            List<Order> orders = orderRepository.findByClient(c1);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SalesProjectCourseApplication.class, args);
    }

}

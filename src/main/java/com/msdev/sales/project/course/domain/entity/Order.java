package com.msdev.sales.project.course.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "total", length = 20, precision = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> items;

}
